package com.lypaka.pixelskills;

import com.google.inject.Inject;
import com.lypaka.pixelskills.Commands.PixelSkillsCmd;
import com.lypaka.pixelskills.Skills.*;
import com.lypaka.pixelskills.Config.ConfigManager;
import com.lypaka.pixelskills.Config.SkillsAccountManager;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.lypaka.pixelskills.Utils.RewardsHandler;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;
import java.util.Random;

@Plugin(
        id = "pixelskills",
        name = "PixelSkills",
        version = "2.0.0",
        description = "A Pixelmon Generations skills plugin",
        authors = {
                "Lypaka"
        }
)
public class PixelSkills {
    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configDir;

    @Inject
    private PluginContainer container;

    @Inject
    private Logger logger;

    public static PixelSkills INSTANCE;
    private static SkillsAccountManager accountManager;
    private static final Random random = new Random();
    private static ConfigGetters configG;
    private static AccountGetters accounts;
    private static RewardsHandler rewards;
    private static ExperienceHandler experienceHandler;


    @Listener
    public void onPreInit (GamePreInitializationEvent event) {
        configG = new ConfigGetters();
        accountManager = new SkillsAccountManager();
        accounts = new AccountGetters();
        rewards = new RewardsHandler();
        experienceHandler = new ExperienceHandler();
        ConfigManager.setup(configDir);

        Pixelmon.EVENT_BUS.register(new Breeder());
        Pixelmon.EVENT_BUS.register(new Catcher());
        Pixelmon.EVENT_BUS.register(new Fisherman());
        Pixelmon.EVENT_BUS.register(new BossConqueror());
        Pixelmon.EVENT_BUS.register(new Botanist());
        Pixelmon.EVENT_BUS.register(new TreasureHunter());
        Pixelmon.EVENT_BUS.register(new Archaeologist());
        Pixelmon.EVENT_BUS.register(new FierceBattler());
        Sponge.getEventManager().registerListeners(this, new Crafter());
        MinecraftForge.EVENT_BUS.register(new Miner());

        Sponge.getCommandManager().register(this, PixelSkillsCmd.create(), "pixelskills");
    }

    @Listener
    public void onJoin (ClientConnectionEvent.Join event, @First Player player) {
        accountManager.createAccount(player.getUniqueId());
    }

    @Listener
    public void onReload(GameReloadEvent e) {
        ConfigManager.load();
        System.out.println("PixelSkills config has reloaded!");
    }


    public static SkillsAccountManager getAccountManager() {
        return accountManager;
    }

    public static ConfigGetters getConfigG() {
        return configG;
    }

    public static AccountGetters getAccountGs() {
        return accounts;
    }

    public static RewardsHandler getRewardsHandler() {
        return rewards;
    }

    public static ExperienceHandler getExperienceHandler() {
        return experienceHandler;
    }

    public PixelSkills() {
        INSTANCE = this;
    }

    public static Logger getLogger() {
        return INSTANCE.logger;
    }

    public static PluginContainer getContainer() {
        return INSTANCE.container;
    }

    public static final Random getRandom() {
        return random;
    }

    public Path getConfigDir() {
        return configDir;
    }

}
