package com.lypaka.pixelskills.Config;

import com.lypaka.pixelskills.PixelSkills;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SkillsAccountManager {
    private Logger logger;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private ConfigurationNode accountConfig;
    public PixelSkills plugin;
    public ConfigurationNode config;
    private SkillsAccountManager accountManager;
    private File accountsFile;

    public SkillsAccountManager () {
        setAccountManager(PixelSkills.INSTANCE.getAccountManager());
        logger = PixelSkills.getLogger();
        setupAccountConfig();
    }

    private void setupAccountConfig() {
        accountsFile = new File(PixelSkills.INSTANCE.getConfigDir().toFile(), "accounts.conf");
        loader = HoconConfigurationLoader.builder().setFile(accountsFile).build();

        try {
            accountConfig = loader.load();
            if (!accountsFile.exists()) {
                accountConfig.getNode("placeholder").setValue(true);
                loader.save(accountConfig);
            }
        } catch (IOException e) {
            logger.warn("Error setting up account configuration!");
        }
    }

    public void createAccount(UUID uuid) {
        if (!hasAccount(uuid)) {
            accountConfig.getNode(uuid.toString(), "Skills", "Breeder", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Breeder", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Breeder", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Breeder", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Breeder", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Breeder", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Catcher", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Catcher", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Catcher", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Catcher", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Catcher", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Catcher", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Crafter", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Crafter", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Crafter", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Crafter", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Crafter", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Crafter", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Fierce Battler", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Fierce Battler", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Fierce Battler", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Fierce-Battler", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Fierce Battler", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Fierce-Battler", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Fisherman", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Fisherman", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Fisherman", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Fisherman", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Fisherman", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Fisherman", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Shiny Hunter", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Shiny Hunter", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Shiny Hunter", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Shiny-Hunter", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Shiny Hunter", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Shiny-Hunter", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Legendary Master", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Legendary Master", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Legendary Master", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Legendary-Master", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Legendary Master", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Legendary-Master", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Scientist", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Scientist", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Scientist", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Scientist", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Scientist", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Scientist", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Botanist", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Botanist", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Botanist", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Botanist", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Botanist", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Botanist", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Miner", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Miner", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Miner", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Miner", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Miner", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Miner", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Archaeologist", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Archaeologist", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Archaeologist", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Archaeologist", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Archaeologist", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Archaeologist", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Blacksmith", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Blacksmith", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Blacksmith", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Blacksmith", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Blacksmith", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Blacksmith", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Treasure Hunter", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Treasure Hunter", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Treasure Hunter", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Treasure-Hunter", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Treasure Hunter", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Treasure-Hunter", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Boss Conqueror", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Boss Conqueror", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Boss Conqueror", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Boss-Conqueror", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Boss Conqueror", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Boss-Conqueror", "Perks", "Level-settings", "Starts-on").getInt());

            accountConfig.getNode(uuid.toString(), "Skills", "Poke Exterminator", "Level").setValue(1);
            accountConfig.getNode(uuid.toString(), "Skills", "Poke Exterminator", "EXP").setValue(0);
            accountConfig.getNode(uuid.toString(), "Skills", "Poke Exterminator", "EXP-to-Levelup").setValue(ConfigManager.getConfigNode("Skills", "Poke-Exterminator", "Basic-settings", "Amount-Needed-For-Level-2").getInt());
            accountConfig.getNode(uuid.toString(), "Skills", "Poke Exterminator", "next-perk-increase-level").setValue(ConfigManager.getConfigNode("Skills", "Poke-Exterminator", "Perks", "Level-settings", "Starts-on").getInt());
            try {
                loader.save(accountConfig);
            } catch (IOException e) {
                logger.warn("Error creating new account!");

            }
        }
    }

    private boolean hasAccount(UUID uuid) {
        return accountConfig.getNode(uuid.toString().toString()).getValue() != null;
    }

    public void saveConfig() {
        try {
            loader.save(accountConfig);
        } catch (IOException e) {
            logger.warn("Error saving the accounts configuration!");

        }

    }

    public ConfigurationNode getAccountsConfig() {
        return accountConfig;

    }

	public SkillsAccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(SkillsAccountManager accountManager) {
		this.accountManager = accountManager;
	}




}
