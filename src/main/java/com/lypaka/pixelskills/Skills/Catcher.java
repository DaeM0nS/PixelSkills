package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Catcher {
    /**
    *
    * Catcher, Legendary Master, and Shiny Hunter are handled in this class, as they use the same event.
    *
    * */

    public Catcher (PixelSkills plugin) {
        this.config = plugin.getConfigG();
        this.accounts = plugin.getAccountGs();
        this.experienceHandler = plugin.getExperienceHandler();
    }

    private ConfigGetters config;
    private AccountGetters accounts;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onCatch (CaptureEvent.SuccessfulCapture e) {
        Player player = (Player) e.player;
        if (e.getPokemon().isShiny()) {

            if (config.isSkillEnabled("Shiny Hunter")) {
                if (config.isSkillTaskEnabled("Shiny Hunter", "Catching-shiny-Pokemon")) {
                    experienceHandler.addPoints("Shiny Hunter", config.getEXPFromTask("Shiny Hunter", "Catching-shiny-Pokemon"), player);
                    if (config.isSkillPerkEnabled("Shiny Hunter")) {
                        if (accounts.getLevel("Shiny Hunter", player) == config.getDefaultPerkLevel("Shiny Hunter") || accounts.getLevel("Shiny Hunter", player) == accounts.getNextPerkLevel("Shiny Hunter", player)) {
                            accounts.setNextPerkLevel("Shiny Hunter", player);
                            if (config.getDefaultPerkChance("Shiny Hunter") > 0) {
                                if (accounts.getPerkChance("Shiny Hunter", player) == 0) {
                                    if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Shiny Hunter")) {
                                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                        if (e.getPokemon().stats.IVs.Attack != 31) {
                                            e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Defence != 31) {
                                            e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                            e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpDef != 31) {
                                            e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Speed != 31) {
                                            e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.HP != 31) {
                                            e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                            e.getPokemon().updateStats();
                                        }
                                    }
                                } else {
                                    if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Shiny Hunter", player)) {
                                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                        if (e.getPokemon().stats.IVs.Attack != 31) {
                                            e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Defence != 31) {
                                            e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                            e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpDef != 31) {
                                            e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Speed != 31) {
                                            e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.HP != 31) {
                                            e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                            e.getPokemon().updateStats();
                                        }
                                    }
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                if (e.getPokemon().stats.IVs.Attack != 31) {
                                    e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.Defence != 31) {
                                    e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                    e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.SpDef != 31) {
                                    e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.Speed != 31) {
                                    e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.HP != 31) {
                                    e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Shiny Hunter", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                    e.getPokemon().updateStats();
                                }
                            }
                        }
                    }
                }
            }
        } else if (isLegendary(e.getPokemon())) {

            if (config.isSkillEnabled("Legendary Master")) {
                if (config.isSkillTaskEnabled("Legendary Master", "Catching-legendaries")) {
                    experienceHandler.addPoints("Legendary Master", config.getEXPFromTask("Legendary Master", "Catching-legendaries"), player);
                    if (config.isSkillPerkEnabled("Legendary Master")) {
                        if (accounts.getLevel("Legendary Master", player) == config.getDefaultPerkLevel("Legendary Master") || accounts.getLevel("Legendary Master", player) == accounts.getNextPerkLevel("Legendary Master", player)) {
                            accounts.setNextPerkLevel("Legendary Master", player);
                            if (config.getDefaultPerkChance("Legendary Master") > 0) {
                                if (accounts.getPerkChance("Legendary Master", player) == 0) {
                                    if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Legendary Master")) {
                                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                        if (e.getPokemon().stats.IVs.Attack != 31) {
                                            e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Defence != 31) {
                                            e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                            e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpDef != 31) {
                                            e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Speed != 31) {
                                            e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.HP != 31) {
                                            e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                            e.getPokemon().updateStats();
                                        }
                                    }
                                } else {
                                    if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Legendary Master", player)) {
                                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                        if (e.getPokemon().stats.IVs.Attack != 31) {
                                            e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Defence != 31) {
                                            e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                            e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpDef != 31) {
                                            e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Speed != 31) {
                                            e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.HP != 31) {
                                            e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                            e.getPokemon().updateStats();
                                        }
                                    }
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                if (e.getPokemon().stats.IVs.Attack != 31) {
                                    e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.Defence != 31) {
                                    e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                    e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.SpDef != 31) {
                                    e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.Speed != 31) {
                                    e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.HP != 31) {
                                    e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Legendary Master", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                    e.getPokemon().updateStats();
                                }
                            }
                        }
                    }
                }
            }

        } else if (!e.getPokemon().isShiny() && !isLegendary(e.getPokemon())) {

            if (config.isSkillEnabled("Catcher")) {
                if (config.isSkillTaskEnabled("Catcher", "Catching-normal-Pokemon")) {
                    experienceHandler.addPoints("Catcher", config.getEXPFromTask("Catcher", "Catching-normal-Pokemon"), player);
                    if (config.isSkillPerkEnabled("Catcher")) {
                        if (accounts.getLevel("Catcher", player) == config.getDefaultPerkLevel("Catcher") || accounts.getLevel("Catcher", player) == accounts.getNextPerkLevel("Catcher", player)) {
                            accounts.setNextPerkLevel("Catcher", player);
                            if (config.getDefaultPerkChance("Catcher") > 0) {
                                if (accounts.getPerkChance("Catcher", player) == 0) {
                                    if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Catcher")) {
                                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                        if (e.getPokemon().stats.IVs.Attack != 31) {
                                            e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Defence != 31) {
                                            e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                            e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpDef != 31) {
                                            e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Speed != 31) {
                                            e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.HP != 31) {
                                            e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                            e.getPokemon().updateStats();
                                        }
                                    }
                                } else {
                                    if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Catcher", player)) {
                                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                        if (e.getPokemon().stats.IVs.Attack != 31) {
                                            e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Defence != 31) {
                                            e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                            e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.SpDef != 31) {
                                            e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.Speed != 31) {
                                            e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                            e.getPokemon().updateStats();
                                        }
                                        if (e.getPokemon().stats.IVs.HP != 31) {
                                            e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                            e.getPokemon().updateStats();
                                        }
                                    }
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                if (e.getPokemon().stats.IVs.Attack != 31) {
                                    e.getPokemon().stats.IVs.Attack = ((e.getPokemon().stats.IVs.Attack / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Attack;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.Defence != 31) {
                                    e.getPokemon().stats.IVs.Defence = ((e.getPokemon().stats.IVs.Defence / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Defence;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.SpAtt != 31) {
                                    e.getPokemon().stats.IVs.SpAtt = ((e.getPokemon().stats.IVs.SpAtt / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.SpAtt;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.SpDef != 31) {
                                    e.getPokemon().stats.IVs.SpDef = ((e.getPokemon().stats.IVs.SpDef / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.SpDef;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.Speed != 31) {
                                    e.getPokemon().stats.IVs.Speed = ((e.getPokemon().stats.IVs.Speed / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.Speed;
                                    e.getPokemon().updateStats();
                                }
                                if (e.getPokemon().stats.IVs.HP != 31) {
                                    e.getPokemon().stats.IVs.HP = ((e.getPokemon().stats.IVs.HP / 31) * (accounts.getLevel("Catcher", player) / 2)) + e.getPokemon().stats.IVs.HP;
                                    e.getPokemon().updateStats();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isLegendary(EntityPixelmon pokemon) {
        if (pokemon.getPokemonName().contains("Mewtwo") || pokemon.getPokemonName().contains("Mew") || pokemon.getPokemonName().contains("HoOh") || pokemon.getPokemonName().contains("Ho-Oh") || pokemon.getPokemonName().contains("Lugia") ||
                pokemon.getPokemonName().contains("Entei") || pokemon.getPokemonName().contains("Suicune") || pokemon.getPokemonName().contains("Raikou") || pokemon.getPokemonName().contains("Celebi") || pokemon.getPokemonName().contains("Latios") ||
                pokemon.getPokemonName().contains("Latias") || pokemon.getPokemonName().contains("Latios") || pokemon.getPokemonName().contains("Groudon") || pokemon.getPokemonName().contains("Kyogre") || pokemon.getPokemonName().contains("Rayquaza") ||
                pokemon.getPokemonName().contains("Jirachi") || pokemon.getPokemonName().contains("Deoxys") || pokemon.getPokemonName().contains("Heatran") || pokemon.getPokemonName().contains("Moltres") || pokemon.getPokemonName().contains("Zapdos") ||
                pokemon.getPokemonName().contains("Articuno") || pokemon.getPokemonName().contains("Regirock") || pokemon.getPokemonName().contains("Regice") || pokemon.getPokemonName().contains("Registeel") || pokemon.getPokemonName().contains("Azelf") ||
                pokemon.getPokemonName().contains("Uxie") || pokemon.getPokemonName().contains("Mesprit") || pokemon.getPokemonName().contains("Dialga") || pokemon.getPokemonName().contains("Palkia") || pokemon.getPokemonName().contains("Giratina") ||
                pokemon.getPokemonName().contains("Cresselia") || pokemon.getPokemonName().contains("Regigigas") || pokemon.getPokemonName().contains("Darkrai") || pokemon.getPokemonName().contains("Shaymin") || pokemon.getPokemonName().contains("Arceus") ||
                pokemon.getPokemonName().contains("Manaphy") || pokemon.getPokemonName().contains("Victini") || pokemon.getPokemonName().contains("Keldeo") || pokemon.getPokemonName().contains("Terrakion") || pokemon.getPokemonName().contains("Virizion") ||
                pokemon.getPokemonName().contains("Colabion") || pokemon.getPokemonName().contains("Thundurus") || pokemon.getPokemonName().contains("Tornadus") || pokemon.getPokemonName().contains("Landorus") || pokemon.getPokemonName().contains("Reshiram") ||
                pokemon.getPokemonName().contains("Kyurem") || pokemon.getPokemonName().contains("Zekrom") || pokemon.getPokemonName().contains("Xerneas") || pokemon.getPokemonName().contains("Yveltal") || pokemon.getPokemonName().contains("Zygarde") ||
                pokemon.getPokemonName().contains("TypeNull") || pokemon.getPokemonName().contains("Type:Null") || pokemon.getPokemonName().contains("Silvally") || pokemon.getPokemonName().contains("TapuKoko") || pokemon.getPokemonName().contains("Tapu Koko") ||
                pokemon.getPokemonName().contains("TapuFini") || pokemon.getPokemonName().contains("Tapu Fini") || pokemon.getPokemonName().contains("TapuLele") || pokemon.getPokemonName().contains("Tapu Lele") || pokemon.getPokemonName().contains("TapuBulu") ||
                pokemon.getPokemonName().contains("Tapu Bulu") || pokemon.getPokemonName().contains("Cosmog") || pokemon.getPokemonName().contains("Cosmoem") || pokemon.getPokemonName().contains("Solgaleo") || pokemon.getPokemonName().contains("Lunala") ||
                pokemon.getPokemonName().contains("Necrozma") || pokemon.getPokemonName().contains("Meloetta") || pokemon.getPokemonName().contains("Genesect") || pokemon.getPokemonName().contains("Diancie") || pokemon.getPokemonName().contains("Hoopa") ||
                pokemon.getPokemonName().contains("Volcanion") || pokemon.getPokemonName().contains("Magearna") || pokemon.getPokemonName().contains("Marshadow") || pokemon.getPokemonName().contains("Zeraora") || pokemon.getPokemonName().contains("Zacian") ||
                pokemon.getPokemonName().contains("Zamazenta") || pokemon.getPokemonName().contains("Eternatus") || pokemon.getPokemonName().contains("Meltan") || pokemon.getPokemonName().contains("Melmetal")) {
            return true;
        }
        return false;
    }
}
