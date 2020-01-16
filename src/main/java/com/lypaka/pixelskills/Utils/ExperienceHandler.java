package com.lypaka.pixelskills.Utils;

import com.lypaka.pixelskills.Config.ConfigManager;
import com.lypaka.pixelskills.Config.SkillsAccountManager;
import com.lypaka.pixelskills.PixelSkills;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.math.BigDecimal;
import java.util.Optional;

public class ExperienceHandler {

    public ExperienceHandler (PixelSkills plugin) {
        this.accounts = plugin.getAccountGs();
        this.config = plugin.getConfigG();
        this.accountManager = plugin.getAccountManager();
        this.rewards = plugin.getRewardsHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private SkillsAccountManager accountManager;
    private RewardsHandler rewards;



    public void addPoints (String skill, int points, Player player) {
        if (accounts.getLevel(skill, player) < config.getMaxLevel(skill)) {
            if (accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "next-reward-level").isVirtual()) {
                accounts.setNextRewardsLevel(skill, player);
            }
            accounts.addEXP(skill, player, points);
            if (didLevelUp(skill, player)) {
                levelup(skill, player);
            }
        }
    }

    public boolean didLevelUp (String skill, Player player) {
        return accounts.getEXP(skill, player) >= accounts.getEXPtoLvl(skill, player);
    }

    public void levelup (String skill, Player player) {
        if (accounts.getEXP(skill, player) > accounts.getEXPtoLvl(skill, player)) {
            int exp = accounts.getEXP(skill, player) - accounts.getEXPtoLvl(skill, player);
            accounts.setEXP(skill, player, exp);
        } else {
            accounts.setEXP(skill, player, 0);
        }
        accounts.setLevel(skill, player, accounts.getLevel(skill, player) + 1);
        accounts.setNextEXPLvl(skill, player);
        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your " + skill + " skill has increased to level " + accounts.getLevel(skill, player)));
        //-------Perk chance------//
        if (accounts.getLevel(skill, player) == config.getDefaultPerkLevel(skill) || accounts.getLevel(skill, player) == accounts.getNextPerkLevel(skill, player)) {
            if (config.doesSkillPerkChanceIncrease(skill)) {
                accounts.setPerkChance(skill, player);
            }
        }

        //-------Rewards-------//
        if (config.isSkillRewardsEnabled(skill)) {
            if (accounts.getLevel(skill, player) == config.getDefaultRewardLevel(skill) || accounts.getLevel(skill, player) == accounts.getNextRewardLevel(skill, player)) {
                if (config.getDefaultRewardChance(skill) > 0) {
                    if (accounts.getRewardChance(skill, player) == 0) {
                        if (PixelSkills.getRandom().nextInt(100) < config.getDefaultRewardChance(skill)) {
                            rewards.giveRewards(skill, player);
                        }
                    } else {
                        if (PixelSkills.getRandom().nextInt(100) < accounts.getRewardChance(skill, player)) {
                            rewards.giveRewards(skill, player);
                        }
                    }

                } else {
                    rewards.giveRewards(skill, player);
                }
                accounts.setNextRewardsLevel(skill, player);
                if (config.doesSkillRewardChanceIncrease(skill)) {
                    accounts.setRewardChance(skill, player);
                }
            }
        }
        //-------Level-locked Rewards-------//
        if (config.areLevelLockedRewardsEnabled(skill)) {
            if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Levels").getString().contains(", ")) {
                String[] lvls = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Levels").getString().split(", ");
                for (int l = 0; l <= lvls.length - 1; l++) {
                    if (accounts.getLevel(skill, player) == Integer.parseInt(lvls[l])) {
                        switch (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Type").getString()) {
                            case "item":
                                if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().contains(", ")) {
                                    String[] list = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().split(", ");
                                    for (int p = 0; p <= list.length - 1; p++) {
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + list[p] + " " + rewards.getLLRewardQuantity(skill, Integer.parseInt(lvls[l]), player));
                                    }
                                } else {
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString() + " " + rewards.getLLRewardQuantity(skill, Integer.parseInt(lvls[l]), player));
                                }
                                break;
                            case "Pokemon":
                                if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().contains(", ")) {
                                    String list[] = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().split(", ");
                                    for (int p = 0; p <= list.length - 1; p++) {
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokegive " + player.getName() + " " + list[p]);
                                    }
                                } else {
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokegive " + player.getName() + " " + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString());
                                }
                                break;
                            case "money":
                                EventContext eventContext = EventContext.builder().add(EventContextKeys.PLUGIN, PixelSkills.getContainer()).build();
                                Optional<EconomyService> econ = Sponge.getServiceManager().provide(EconomyService.class);
                                if (econ.isPresent()) {
                                    Optional<UniqueAccount> a = econ.get().getOrCreateAccount(player.getUniqueId());
                                    Currency defaultCur = econ.get().getDefaultCurrency();
                                    a.get().deposit(defaultCur, BigDecimal.valueOf(ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getInt()), Cause.of(eventContext, PixelSkills.getContainer()));
                                }
                                break;
                            case "command":
                                if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().contains(", ")) {
                                    String list[] = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().split(", ");
                                    for (int p = 0; p <= list.length - 1; p++) {
                                        if (list[p].contains("%player%")) {
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), list[p].replace("%player%", player.getName()));
                                        } else {
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), list[p]);
                                        }
                                    }
                                } else {
                                    if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().contains("%player%")) {
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString().replace("%player%", player.getName()));
                                    } else {
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + lvls[l], "Prize").getString());
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

}
