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
        description = "A Pixelmon skills plugin",
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
    private SkillsAccountManager accountManager;
    private static final Random random = new Random();
    private ConfigGetters configG;
    private AccountGetters accounts;
    private RewardsHandler rewards;
    private ExperienceHandler experienceHandler;


    @Listener
    public void onPreInit (GamePreInitializationEvent event) {
        experienceHandler = new ExperienceHandler(this);
        configG = new ConfigGetters(this);
        accounts = new AccountGetters(this);
        accountManager = new SkillsAccountManager(this);
        rewards = new RewardsHandler(this);
        ConfigManager.setup(configDir);

        Pixelmon.EVENT_BUS.register(new Breeder(this));
        Pixelmon.EVENT_BUS.register(new Catcher(this));
        Pixelmon.EVENT_BUS.register(new Fisherman(this));
        Pixelmon.EVENT_BUS.register(new BossConqueror(this));
        Pixelmon.EVENT_BUS.register(new Botanist(this));
        Pixelmon.EVENT_BUS.register(new TreasureHunter(this));
        Pixelmon.EVENT_BUS.register(new Archaeologist(this));
        Pixelmon.EVENT_BUS.register(new FierceBattler(this));
        Sponge.getEventManager().registerListeners(this, new Crafter(this));
        MinecraftForge.EVENT_BUS.register(new Miner(this));

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


    public SkillsAccountManager getAccountManager() {
        return accountManager;
    }

    public ConfigGetters getConfigG() {
        return configG;
    }

    public AccountGetters getAccountGs() {
        return accounts;
    }

    public RewardsHandler getRewardsHandler() {
        return rewards;
    }

    public ExperienceHandler getExperienceHandler() {
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
