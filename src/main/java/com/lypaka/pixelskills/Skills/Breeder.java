package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Config.ConfigManager;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.api.events.player.PlayerEggHatchEvent;
import com.pixelmonmod.pixelmon.api.events.player.PlayerEggStepsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.io.IOException;
import java.util.UUID;

public class Breeder {
    public Breeder (PixelSkills plugin) {
        this.config = plugin.getConfigG();
        this.accounts = plugin.getAccountGs();
        this.experienceHandler = plugin.getExperienceHandler();
    }

    private ConfigGetters config;
    private AccountGetters accounts;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onEggGet (BreedEvent.MakeEgg e) throws IOException {
        UUID uuid = e.owner;
        Player player = Sponge.getServer().getPlayer(uuid).get();

        if (config.isSkillEnabled("Breeder")) {
            if (config.isSkillTaskEnabled("Breeder", "Making-eggs")) {
                experienceHandler.addPoints("Breeder", config.getEXPFromTask("Breeder", "Making-eggs"), player);
                if (config.isSkillPerkEnabled("Breeder")) {
                    if (accounts.getLevel("Breeder", player) == config.getDefaultPerkLevel("Breeder") || accounts.getLevel("Breeder", player) == accounts.getNextPerkLevel("Breeder", player)) {
                        accounts.setNextPerkLevel("Breeder", player);
                        if (config.getDefaultPerkChance("Breeder") > 0) {
                            if (accounts.getPerkChance("Breeder", player) == 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Breeder")) {
                                    if (e.getEgg().stats.IVs.HP != 31) {
                                        e.getEgg().stats.IVs.HP = ((e.getEgg().stats.IVs.HP / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.HP;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.Attack != 31) {
                                        e.getEgg().stats.IVs.Attack = ((e.getEgg().stats.IVs.Attack / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.Attack;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.Defence != 31) {
                                        e.getEgg().stats.IVs.Defence = ((e.getEgg().stats.IVs.Defence / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.Defence;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.SpAtt != 31) {
                                        e.getEgg().stats.IVs.SpAtt = ((e.getEgg().stats.IVs.SpAtt / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.SpAtt;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.SpDef != 31) {
                                        e.getEgg().stats.IVs.SpDef = ((e.getEgg().stats.IVs.SpDef / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.SpDef;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.Speed != 31) {
                                        e.getEgg().stats.IVs.Speed = ((e.getEgg().stats.IVs.Speed / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.Speed;
                                        e.getEgg().updateStats();
                                    }
                                }
                            } else {
                                if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Breeder", player)) {
                                    if (e.getEgg().stats.IVs.HP != 31) {
                                        e.getEgg().stats.IVs.HP = ((e.getEgg().stats.IVs.HP / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.HP;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.Attack != 31) {
                                        e.getEgg().stats.IVs.Attack = ((e.getEgg().stats.IVs.Attack / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.Attack;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.Defence != 31) {
                                        e.getEgg().stats.IVs.Defence = ((e.getEgg().stats.IVs.Defence / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.Defence;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.SpAtt != 31) {
                                        e.getEgg().stats.IVs.SpAtt = ((e.getEgg().stats.IVs.SpAtt / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.SpAtt;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.SpDef != 31) {
                                        e.getEgg().stats.IVs.SpDef = ((e.getEgg().stats.IVs.SpDef / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.SpDef;
                                        e.getEgg().updateStats();
                                    }
                                    if (e.getEgg().stats.IVs.Speed != 31) {
                                        e.getEgg().stats.IVs.Speed = ((e.getEgg().stats.IVs.Speed / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().stats.IVs.Speed;
                                        e.getEgg().updateStats();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEggHatch (PlayerEggHatchEvent e) {
        if (ConfigManager.getConfigNode("Skills", "Breeder", "isEnabled").getValue().equals(true)) {
            Player player = (Player) e.getPlayer();

            if (config.isSkillEnabled("Breeder")) {
                if (config.isSkillTaskEnabled("Breeder", "Hatching-eggs")) {
                    experienceHandler.addPoints("Breeder", config.getEXPFromTask("Breeder", "Making-eggs"), player);
                }
            }
        }
    }

    /**
     * This is the perk for the hatching eggs task
     */

    @SubscribeEvent
    public void onEggHatching (PlayerEggStepsEvent e) {
        Player player = (Player) e.getPlayer();

        if (config.isSkillEnabled("Breeder")) {
            if (config.isSkillTaskEnabled("Breeder", "Making-eggs")) {
                experienceHandler.addPoints("Breeder", config.getEXPFromTask("Breeder", "Making-eggs"), player);
                if (config.isSkillPerkEnabled("Breeder")) {
                    if (accounts.getLevel("Breeder", player) == config.getDefaultPerkLevel("Breeder") || accounts.getLevel("Breeder", player) == accounts.getNextPerkLevel("Breeder", player)) {
                        if (config.getDefaultPerkChance("Breeder") > 0) {
                            if (accounts.getPerkChance("Breeder", player) == 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Breeder")) {
                                    double mod = (accounts.getLevel("Breeder", player) * 0.01);
                                    double newSteps = mod * e.getStepsRequired();
                                    e.setStepsRequired((int) (e.getStepsRequired() - newSteps));
                                }
                            } else {
                                if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Breeder", player)) {
                                    double mod = (accounts.getLevel("Breeder", player) * 0.01);
                                    double newSteps = mod * e.getStepsRequired();
                                    e.setStepsRequired((int) (e.getStepsRequired() - newSteps));
                                }
                            }
                        } else {
                            double mod = (accounts.getLevel("Breeder", player) * 0.01);
                            double newSteps = mod * e.getStepsRequired();
                            e.setStepsRequired((int) (e.getStepsRequired() - newSteps));
                        }
                    }
                }
            }
        }
    }
}
