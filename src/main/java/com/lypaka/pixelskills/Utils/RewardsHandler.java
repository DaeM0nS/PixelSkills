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

public class RewardsHandler {
    public RewardsHandler(PixelSkills plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigG();
        this.accounts = plugin.getAccountGs();
        this.accountManager = plugin.getAccountManager();
    }
    private ConfigGetters config;
    private AccountGetters accounts;
    private PixelSkills plugin;
    private SkillsAccountManager accountManager;

    public void giveRewards (String skill, Player player) {
        if (config.getRewardOptions(skill) > 1) {
            int rng = PixelSkills.getRandom().nextInt(config.getRewardOptions(skill));
            switch (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Type").getString()) {
                case "item":
                    if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().contains(", ")) {
                        String[] list = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().split(", ");
                        for (int p = 0; p <= list.length - 1; p++) {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + list[p] + " " + getRewardQuantity(skill, rng, player));
                        }
                    } else {
                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString() + " " + getRewardQuantity(skill, rng, player));
                    }
                    break;
                case "Pokemon":
                    if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().contains(", ")) {
                        String list[] = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().split(", ");
                        for (int p = 0; p <= list.length - 1; p++) {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokegive " + player.getName() + " " + list[p]);
                        }
                    } else {
                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokegive " + player.getName() + " " + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString());
                    }
                    break;
                case "money":
                    EventContext eventContext = EventContext.builder().add(EventContextKeys.PLUGIN, PixelSkills.getContainer()).build();
                    Optional<EconomyService> econ = Sponge.getServiceManager().provide(EconomyService.class);
                    if (econ.isPresent()) {
                        Optional<UniqueAccount> a = econ.get().getOrCreateAccount(player.getUniqueId());
                        Currency defaultCur = econ.get().getDefaultCurrency();
                        a.get().deposit(defaultCur, BigDecimal.valueOf(ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getInt()), Cause.of(eventContext, PixelSkills.getContainer()));
                    }
                    break;
                case "command":
                    if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().contains(", ")) {
                        String list[] = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().split(", ");
                        for (int p = 0; p <= list.length - 1; p++) {
                            if (list[p].contains("%player%")) {
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), list[p].replace("%player%", player.getName()));
                            } else {
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), list[p]);
                            }
                        }
                    } else {
                        if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().contains("%player%")) {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString().replace("%player%", player.getName()));
                        } else {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + rng, "Prize").getString());
                        }
                    }
                    break;
            }
        } else {
            switch (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Type").getString()) {
                case "item":
                    if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().contains(", ")) {
                        String[] list = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().split(", ");
                        for (int p = 0; p <= list.length - 1; p++) {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + list[p] + " " + getRewardQuantity(skill, 1, player));
                        }
                    } else {
                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString() + " " + getRewardQuantity(skill, 1, player));
                    }
                    break;
                case "Pokemon":
                    if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().contains(", ")) {
                        String list[] = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().split(", ");
                        for (int p = 0; p <= list.length - 1; p++) {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokegive " + player.getName() + " " + list[p]);
                        }
                    } else {
                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokegive " + player.getName() + " " + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString());
                    }
                    break;
                case "money":
                    EventContext eventContext = EventContext.builder().add(EventContextKeys.PLUGIN, PixelSkills.getContainer()).build();
                    Optional<EconomyService> econ = Sponge.getServiceManager().provide(EconomyService.class);
                    if (econ.isPresent()) {
                        Optional<UniqueAccount> a = econ.get().getOrCreateAccount(player.getUniqueId());
                        Currency defaultCur = econ.get().getDefaultCurrency();
                        a.get().deposit(defaultCur, BigDecimal.valueOf(ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getInt()), Cause.of(eventContext, PixelSkills.getContainer()));
                    }
                    break;
                case "command":
                    if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().contains(", ")) {
                        String list[] = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().split(", ");
                        for (int p = 0; p <= list.length - 1; p++) {
                            if (list[p].contains("%player%")) {
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), list[p].replace("%player%", player.getName()));
                            } else {
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), list[p]);
                            }
                        }
                    } else {
                        if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().contains("%player%")) {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString().replace("%player%", player.getName()));
                        } else {
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-1", "Prize").getString());
                        }
                    }
                    break;
            }
        }
        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You received rewards for leveling up!"));
    }

    private int getRewardQuantity (String skill, int reward, Player player) {
        if (config.areRewardModifiersEnabled(skill, reward)) {
            if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Modifiers", "Mode").getString().equals("multiply")) {
                if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Modifiers", "Number").getValue().equals("level")) {
                    return accounts.getLevel(skill, player) * ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Base-value").getInt();
                } else {
                    return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Modifiers", "Number").getInt() * ConfigManager.getConfigNode("Skills", skill, "Reward-Options", "Reward-" + reward, "Amount", "Base-value").getInt();
                }
            } else {
                if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Modifiers", "Number").getValue().equals("level")) {
                    return accounts.getLevel(skill, player) + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Base-value").getInt();
                } else {
                    return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Modifiers", "Number").getInt() + ConfigManager.getConfigNode("Skills", skill, "Reward-Options", "Reward-" + reward, "Amount", "Base-value").getInt();
                }
            }
        } else {
            return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + reward, "Amount", "Base-value").getInt();
        }
    }

    public int getLLRewardQuantity (String skill, int level, Player player) {
        if (config.areLevelRewardModifiersEnabled(skill, level)) {
            if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Modifiers", "Mode").getString().equals("multiply")) {
                if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Modifiers", "Number").getValue().equals("level")) {
                    return accounts.getLevel(skill, player) * ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Base-value").getInt();
                } else {
                    return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Modifiers", "Number").getInt() * ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Base-value").getInt();
                }
            } else {
                if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Modifiers", "Number").getValue().equals("level")) {
                    return accounts.getLevel(skill, player) + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Base-value").getInt();
                } else {
                    return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Modifiers", "Number").getInt() + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Base-value").getInt();
                }
            }
        } else {
            return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + level, "Amount", "Base-value").getInt();
        }
    }
}
