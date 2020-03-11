package com.lypaka.pixelskills.Config;

import com.lypaka.pixelskills.PixelSkills;
import ninja.leaping.configurate.ConfigurationNode;

import java.util.UUID;

public class SkillsAccount {
    private PixelSkills plugin;
    private SkillsAccountManager accountManager;
    public UUID uuid;
    public ConfigurationNode skillsAccountsConfig;

    public SkillsAccount (PixelSkills plugin, SkillsAccountManager accountManager, UUID uuid) {
        this.setPlugin(plugin);
        this.setAccountManager(accountManager);
        this.uuid = uuid;
        skillsAccountsConfig = accountManager.getAccountsConfig();
    }

	public PixelSkills getPlugin() {
		return plugin;
	}

	public void setPlugin(PixelSkills plugin) {
		this.plugin = plugin;
	}

	public SkillsAccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(SkillsAccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
