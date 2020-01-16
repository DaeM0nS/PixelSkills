package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.ApricornEvent;
import com.pixelmonmod.pixelmon.api.events.BerryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Botanist {
    public Botanist () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onApricornPick (ApricornEvent.PickApricorn e) {
        Player player = (Player) e.player;

        if (config.isSkillEnabled("Botanist")) {
            if (config.isSkillTaskEnabled("Botanist", "Picking-Apricorns")) {
                experienceHandler.addPoints("Botanist", config.getEXPFromTask("Botanist", "Picking-Apricorns"), player);
                if (config.isSkillPerkEnabled("Botanist")) {
                    if (accounts.getLevel("Botanist", player) == config.getDefaultPerkLevel("Botanist") || accounts.getLevel("Botanist", player) == accounts.getNextPerkLevel("Botanist", player)) {
                        accounts.setNextPerkLevel("Botanist", player);
                        if (config.getDefaultPerkChance("Botanist") > 0) {
                            if (accounts.getPerkChance("Botanist", player) == 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Botanist")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some hidden Apricorns!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.apricorn.name() + " 2");
                                }
                            } else {
                                if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Botanist", player)) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some hidden Apricorns!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.apricorn.name() + " 2");
                                }
                            }
                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some hidden Apricorns!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.apricorn.name() + " 2");
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBerryPick (BerryEvent.PickBerry e) {
        Player player = (Player) e.player;

        if (config.isSkillEnabled("Botanist")) {
            if (config.isSkillTaskEnabled("Botanist", "Picking-Berries")) {
                experienceHandler.addPoints("Botanist", config.getEXPFromTask("Botanist", "Picking-Berries"), player);
                if (config.isSkillPerkEnabled("Botanist")) {
                    if (accounts.getLevel("Botanist", player) == config.getDefaultPerkLevel("Botanist") || accounts.getLevel("Botanist", player) == accounts.getNextPerkLevel("Botanist", player)) {
                        accounts.setNextPerkLevel("Botanist", player);
                        if (config.getDefaultPerkChance("Botanist") > 0) {
                            if (accounts.getPerkChance("Botanist", player) == 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Botanist")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some hidden Berries!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.berry.name() + " 2");
                                }
                            } else {
                                if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Botanist", player)) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some hidden Berries!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.berry.name() + " 2");
                                }
                            }

                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some hidden Berries!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.berry.name() + " 2");
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onApricornPlant (ApricornEvent.ApricornPlanted e) {
        Player player = (Player) e.player;

        if (config.isSkillEnabled("Botanist")) {
            if (config.isSkillTaskEnabled("Botanist", "Planting-Apricorns")) {
                experienceHandler.addPoints("Botanist", config.getEXPFromTask("Botanist", "Planting-Apricorns"), player);
                if (config.isSkillPerkEnabled("Botanist")) {
                    if (accounts.getLevel("Botanist", player) == config.getDefaultPerkLevel("Botanist") || accounts.getLevel("Botanist", player) == accounts.getNextPerkLevel("Botanist", player)) {
                        accounts.setNextPerkLevel("Botanist", player);
                        if (config.getDefaultPerkChance("Botanist") > 0) {
                            if (accounts.getPerkChance("Botanist", player) == 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Botanist")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found a little Apricorn budding off of that one!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.apricorn.name() + " 1");
                                }
                            } else {
                                if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Botanist", player)) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found a little Apricorn budding off of that one!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.apricorn.name() + " 1");
                                }
                            }

                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found a little Apricorn budding off of that one!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.apricorn.name() + " 1");
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBerryPlant (BerryEvent.BerryPlanted e) {
        Player player = (Player) e.player;

        if (config.isSkillEnabled("Botanist")) {
            if (config.isSkillTaskEnabled("Botanist", "Planting-Berries")) {
                experienceHandler.addPoints("Botanist", config.getEXPFromTask("Botanist", "Planting-Berries"), player);
                if (config.isSkillPerkEnabled("Botanist")) {
                    if (accounts.getLevel("Botanist", player) == config.getDefaultPerkLevel("Botanist") || accounts.getLevel("Botanist", player) == accounts.getNextPerkLevel("Botanist", player)) {
                        accounts.setNextPerkLevel("Botanist", player);
                        if (config.getDefaultPerkChance("Botanist") > 0) {
                            if (accounts.getPerkChance("Botanist", player) == 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Botanist")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found a little Berry budding off of that one!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.berry.name() + " 1");
                                }
                            } else {
                                if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Botanist", player)) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found a little Berry budding off of that one!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.berry.name() + " 1");
                                }
                            }

                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found a little Berry budding off of that one!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.berry.name() + " 1");
                        }
                    }
                }
            }
        }
    }
}
