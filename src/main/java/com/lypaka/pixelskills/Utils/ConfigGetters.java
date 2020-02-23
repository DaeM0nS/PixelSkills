package com.lypaka.pixelskills.Utils;

import com.lypaka.pixelskills.Config.ConfigManager;

import org.spongepowered.api.entity.living.player.Player;

public class ConfigGetters {
    public ConfigGetters () {

    }

    //------------------------------------------------------------------------------Booleans----------------------------------------------------------------//

    public boolean isSkillEnabled (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Basic-settings", "Enabled").getBoolean();
    }

    public boolean isSkillPerkEnabled (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Perks", "Are-Enabled").getBoolean();
    }

    public boolean doesSkillPerkChanceIncrease (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Perks", "Chance-of-triggering", "Chance-gets-higher").getBoolean();
    }

    public boolean isSkillRewardsEnabled (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Are-Enabled").getBoolean();
    }

    public boolean doesSkillRewardChanceIncrease (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Chance-gets-higher").getBoolean();
    }

    public boolean areLevelLockedRewardsEnabled (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Enabled").getBoolean();
    }

    public boolean areLevelRewardModifiersEnabled (String skill, int number) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Level-locked-rewards", "Rewards", "Level-" + number, "Amount", "Modifiers", "Enabled").getBoolean();
    }

    public boolean areRewardModifiersEnabled (String skill, int number) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Reward-" + number, "Amount", "Modifiers", "Enabled").getBoolean();
    }

    public boolean isSkillTaskEnabled (String skill, String task) {
        return ConfigManager.getConfigNode("Skills", skill, "Tasks", task, "Is-Enabled").getBoolean();
    }

    //----------------------------------------------------------------------------------Integers-----------------------------------------------------------------------------------//

    public int getMaxLevel (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Basic-settings", "Max-Level").getInt();
    }

    int getEXPModifier(String skill, Player player) {
        if (player.hasPermission(skill.toLowerCase().replace(" ", "") + ".specialmodifier")) {
            return ConfigManager.getConfigNode("Skills", skill, "EXP", "EXP-Modifiers", "Permission").getInt();
        } else {
            return ConfigManager.getConfigNode("Skills", skill, "EXP", "EXP-Modifiers", "Default").getInt();
        }
    }

    public int getDefaultPerkChance (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Perks", "Chance-of-triggering").getInt();
    }

    public int getDefaultPerkLevel (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Perks", "Level-settings", "Starts-on").getInt();
    }

    public int getDefaultRewardChance (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Default-chance").getInt();
    }

    public int getDefaultRewardLevel (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Chance-of-triggering", "Starts-at-level").getInt();
    }

    public int getRewardOptions (String skill) {
        return ConfigManager.getConfigNode("Skills", skill, "Rewards", "Reward-Options", "Number-of-reward-options").getInt();
    }

    public int getEXPFromTask (String skill, String task) {
        return ConfigManager.getConfigNode("Skills", skill, "Tasks", task, "EXP-Gained-Per-Task").getInt();
    }

}
