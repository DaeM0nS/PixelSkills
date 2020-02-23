package com.lypaka.pixelskills.Utils;

import com.lypaka.pixelskills.Config.ConfigManager;
import com.lypaka.pixelskills.Config.SkillsAccountManager;
import com.lypaka.pixelskills.PixelSkills;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class AccountGetters {
    public AccountGetters () {
        config = PixelSkills.getConfigG();
        accountManager = PixelSkills.getAccountManager();
    }

    private ConfigGetters config;
    private SkillsAccountManager accountManager;

    public int getLevel (String skill, Player player) {
        return accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "Level").getInt();
    }

    public int getEXP (String skill, Player player) {
        return accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP").getInt();
    }

    public int getEXPtoLvl (String skill, Player player) {
        return accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").getInt();
    }



    public int getNextPerkLevel (String skill, Player player) {
        return accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "next-perk-increase-level").getInt();
    }

    int getNextRewardLevel(String skill, Player player) {
        return accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "next-reward-level").getInt();
    }

    int getRewardChance(String skill, Player player) {
        return accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "chance-at-rewards").getInt();
    }


    void addEXP(String skill, Player player, int exp) {
        int points = exp * config.getEXPModifier(skill, player);
        accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP").setValue(getEXP(skill, player) + points);
        accountManager.saveConfig();
        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You gained " + points + " " + skill + " EXP points!"));
    }

    void setEXP(String skill, Player player, int exp) {
        accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP").setValue(exp);
        accountManager.saveConfig();
    }

    void setNextEXPLvl(String skill, Player player) {
        if (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Increase-by").getString().equals("add")) {
            if (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Number").getValue().equals("level")) {
                if (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Result").getString().equals("add")) {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() + getLevel(skill, player) + getEXP(skill, player);
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                } else {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() + (getLevel(skill, player) * getEXP(skill, player));
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                }
            } else {
                if (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Result").getString().equals("add")) {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() + ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Number").getInt() + getEXP(skill, player);
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                } else {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() + (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Number").getInt() * getEXP(skill, player));
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                }
            }
        } else {
            if (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Number").getValue().equals("level")) {
                if (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Result").getString().equals("add")) {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() * getLevel(skill, player) + getEXP(skill, player);
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                } else {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() * (getLevel(skill, player) * getEXP(skill, player));
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                }
            } else {
                if (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Result").getString().equals("add")) {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() * ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Number").getInt() + getEXP(skill, player);
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                } else {
                    int exp = ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Amount").getInt() * (ConfigManager.getConfigNode("Skills", skill, "EXP", "Increment", "Modifier", "Number").getInt() * getEXP(skill, player));
                    accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "EXP-to-Levelup").setValue(exp);
                    accountManager.saveConfig();
                }
            }
        }
    }

    void setLevel(String skill, Player player, int level) {
        accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "Level").setValue(level);
        accountManager.saveConfig();
    }

    void setNextRewardsLevel(String skill, Player player) {
        accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "next-reward-level").setValue(getLevel(skill, player) + ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Modifiers", "Level").getInt());
        accountManager.saveConfig();
    }

    public void setNextPerkLevel (String skill, Player player) {
        accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "next-perk-increase-level").setValue(getLevel(skill, player) + ConfigManager.getConfigNode("Skills", skill, "Perks", "Level-settings", "Interval").getInt());
        accountManager.saveConfig();
    }


    void setRewardChance(String skill, Player player) {
        if (getRewardChance(skill, player) == 0) {
            if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Modifiers", "Mode").getString().equals("add")) {
                int number = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Modifiers", "Number").getInt();
                accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "chance-at-rewards").setValue(getRewardChance(skill, player) + 1 + number);
                accountManager.saveConfig();
            } else {
                int number = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Modifiers", "Number").getInt();
                accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "chance-at-rewards").setValue((getRewardChance(skill, player) + 1) * number);
                accountManager.saveConfig();
            }
        } else {
            if (ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Modifiers", "Mode").getString().equals("add")) {
                int number = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Modifiers", "Number").getInt();
                accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "chance-at-rewards").setValue(getRewardChance(skill, player) + number);
                accountManager.saveConfig();
            } else {
                int number = ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Modifiers", "Number").getInt();
                accountManager.getAccountsConfig().getNode(player.getUniqueId().toString(), "Skills", skill, "chance-at-rewards").setValue(getRewardChance(skill, player) * number);
                accountManager.saveConfig();
            }
        }
    }




}
