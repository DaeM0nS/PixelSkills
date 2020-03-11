package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.forms.EnumMega;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class BossConqueror {
    /**
     *
     * Boss Conqueror and Poke Exterminator are handled in this class, as they fire off the same event
     *
     */

    public BossConqueror () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onPokeKill (BeatWildPixelmonEvent e) {
        Player player = (Player) e.player;
        EntityPixelmon pokemon = e.wpp.controlledPokemon.get(0).entity;


        if (config.isSkillEnabled("Boss-Conqueror")) {
            if (config.isSkillTaskEnabled("Boss-Conqueror", "Kill-Mega-bosses") && isMega(pokemon) && isBoss(pokemon)) {
                experienceHandler.addPoints("Boss-Conqueror", config.getEXPFromTask("Boss-Conqueror", "Kill-Mega-bosses"), player);
                if (config.isSkillPerkEnabled("Boss-Conqueror")) {
                    if (accounts.getLevel("Boss-Conqueror", player) == config.getDefaultPerkLevel("Boss-Conqueror") || accounts.getLevel("Boss-Conqueror", player) == accounts.getNextPerkLevel("Boss-Conqueror", player)) {
                        accounts.setNextPerkLevel("Boss-Conqueror", player);
                        if (config.getDefaultPerkChance("Boss-Conqueror") > 0) {
                            if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Boss-Conqueror")) {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The boss dropped 3 extra Ultra Balls!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:ultra_ball 3");
                            }

                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The boss dropped 3 extra Ultra Balls!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:ultra_ball 3");
                        }
                    }
                }

            } else if (config.isSkillTaskEnabled("Boss-Conqueror", "Kill-normal-bosses") && !isMega(e.wpp.controlledPokemon.get(0).entity) && isBoss(e.wpp.controlledPokemon.get(0).entity)) {
                experienceHandler.addPoints("Boss-Conqueror", config.getEXPFromTask("Boss-Conqueror", "Kill-normal-bosses"), player);
                if (config.isSkillPerkEnabled("Boss-Conqueror")) {
                    if (accounts.getLevel("Boss-Conqueror", player) == config.getDefaultPerkLevel("Boss-Conqueror") || accounts.getLevel("Boss-Conqueror", player) == accounts.getNextPerkLevel("Boss-Conqueror", player)) {
                        accounts.setNextPerkLevel("Boss-Conqueror", player);
                        if (config.getDefaultPerkChance("Boss-Conqueror") > 0) {
                            if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Boss-Conqueror")) {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The boss dropped 3 extra Ultra Balls!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:ultra_ball 3");
                            }
                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The boss dropped 3 extra Ultra Balls!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:ultra_ball 3");
                        }
                    }
                }
            }
        }

        if (config.isSkillTaskEnabled("Poke-Exterminator", "Kill-normal-Pokemon") && !isMega(e.wpp.controlledPokemon.get(0).entity) && !isBoss(e.wpp.controlledPokemon.get(0).entity)) {
            experienceHandler.addPoints("Poke-Exterminator", config.getEXPFromTask("Poke-Exterminator", "Kill-normal-Pokemon"), player);
            if (config.isSkillPerkEnabled("Poke-Exterminator")) {
                if (accounts.getLevel("Poke-Exterminator", player) == config.getDefaultPerkLevel("Poke-Exterminator") || accounts.getLevel("Poke-Exterminator", player) == accounts.getNextPerkLevel("Poke-Exterminator", player)) {
                    accounts.setNextPerkLevel("Poke-Exterminator", player);
                    if (config.getDefaultPerkChance("Poke-Exterminator") > 0) {
                        if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Poke-Exterminator")) {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The boss dropped 3 extra Ultra Balls!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:ultra_ball 3");
                        }

                    } else {
                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The boss dropped 3 extra Ultra Balls!"));
                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:ultra_ball 3");
                    }
                }
            }
        }
    }
/*
    private boolean isMega(EntityPixelmon pokemon) {
        return pokemon.isMega;
    }
    */
    private boolean isMega(EntityPixelmon pokemon) {
        return pokemon.getFormEnum() instanceof EnumMega;
    }

    private boolean isBoss(EntityPixelmon pokemon) {
        return pokemon.isBossPokemon();
    }
}
