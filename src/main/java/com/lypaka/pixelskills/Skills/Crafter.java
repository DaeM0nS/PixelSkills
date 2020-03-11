package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.config.PixelmonItemsPokeballs;
import com.pixelmonmod.pixelmon.config.PixelmonItemsTools;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.CraftItemEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Crafter {
    /**
     *
     * Crafter class handles Crafter, Blacksmith, and Scientist skills, as they fire off the same event
     *
     **/

    public Crafter () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @Listener
    public void onCraft (CraftItemEvent.Craft e, @Root Player player) {
        if (isCrafterItem(e.getCrafted().createStack())) {

            if (config.isSkillEnabled("Crafter")) {
                if (config.isSkillTaskEnabled("Crafter", "Crafting-Poke-Balls")) {
                    if (e.getCrafted().getQuantity() > 1) {
                        experienceHandler.addPoints("Crafter", (config.getEXPFromTask("Crafter", "Crafting-Poke-Balls") * e.getCrafted().getQuantity()), player);
                    } else {
                        experienceHandler.addPoints("Crafter", config.getEXPFromTask("Crafter", "Crafting-Poke-Balls"), player);
                    }
                    if (config.isSkillPerkEnabled("Crafter")) {
                        if (accounts.getLevel("Crafter", player) == config.getDefaultPerkLevel("Crafter") || accounts.getLevel("Crafter", player) == accounts.getNextPerkLevel("Crafter", player)) {
                            accounts.setNextPerkLevel("Crafter", player);
                            if (config.getDefaultPerkChance("Crafter") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Crafter")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                            }
                        }
                    }
                }
            }

        } else if (isScientistItem(e.getCrafted().createStack())) {

            if (config.isSkillEnabled("Scientist")) {
                if (config.isSkillTaskEnabled("Scientist", "Crafting-Pixelmon-healing-items")) {
                    if (e.getCrafted().getQuantity() > 1) {
                        experienceHandler.addPoints("Scientist", (config.getEXPFromTask("Scientist", "Crafting-Pixelmon-healing-items") * e.getCrafted().getQuantity()), player);
                    } else {
                        experienceHandler.addPoints("Scientist", config.getEXPFromTask("Scientist", "Crafting-Pixelmon-healing-items"), player);
                    }
                    if (config.isSkillPerkEnabled("Scientist")) {
                        if (accounts.getLevel("Scientist", player) == config.getDefaultPerkLevel("Scientist") || accounts.getLevel("Scientist", player) == accounts.getNextPerkLevel("Scientist", player)) {
                            accounts.setNextPerkLevel("Scientist", player);
                            if (config.getDefaultPerkChance("Scientist") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Scientist")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                            }
                        }
                    }
                }
            }
        } else if (isBlacksmithItem(e.getCrafted().createStack())) {

            if (config.isSkillEnabled("Blacksmith")) {
                if (config.isSkillTaskEnabled("Blacksmith", "Crafting-Pixelmon-tools")) {
                    if (e.getCrafted().getQuantity() > 1) {
                        experienceHandler.addPoints("Blacksmith", (config.getEXPFromTask("Blacksmith", "Crafting-Pixelmon-tools") * e.getCrafted().getQuantity()), player);
                    } else {
                        experienceHandler.addPoints("Blacksmith", config.getEXPFromTask("Blacksmith", "Crafting-Pixelmon-tools"), player);
                    }
                    if (config.isSkillPerkEnabled("Blacksmith")) {
                        if (accounts.getLevel("Blacksmith", player) == config.getDefaultPerkLevel("Blacksmith") || accounts.getLevel("Blacksmith", player) == accounts.getNextPerkLevel("Blacksmith", player)) {
                            accounts.setNextPerkLevel("Blacksmith", player);
                            if (config.getDefaultPerkChance("Blacksmith") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Blacksmith")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                            }
                        }
                    }

                } else if (config.isSkillTaskEnabled("Blacksmith", "Crafting-vanilla-tools")) {
                    if (e.getCrafted().getQuantity() > 1) {
                        experienceHandler.addPoints("Blacksmith", (config.getEXPFromTask("Blacksmith", "Crafting-vanilla-tools") * e.getCrafted().getQuantity()), player);
                    } else {
                        experienceHandler.addPoints("Blacksmith", config.getEXPFromTask("Blacksmith", "Crafting-vanilla-tools"), player);
                    }
                    if (config.isSkillPerkEnabled("Blacksmith")) {
                        if (accounts.getLevel("Blacksmith", player) == config.getDefaultPerkLevel("Blacksmith") || accounts.getLevel("Blacksmith", player) == accounts.getNextPerkLevel("Blacksmith", player)) {
                            accounts.setNextPerkLevel("Blacksmith", player);
                            if (config.getDefaultPerkChance("Blacksmith") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Blacksmith")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " You found some leftover material and made more!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getCrafted().getType().getName() + " 2");
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isScientistItem(ItemStack is) {
        return is.getType().matches(ItemStack.of((ItemType) PixelmonItems.potion)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.superPotion)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.hyperPotion))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.fullRestore)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.maxPotion)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.parlyzHeal))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.antidote)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.burnHeal)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.iceHeal))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.iceHeal)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.revive)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.maxRevive))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.elixir)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.maxElixir)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.ether))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItems.maxEther));
    }

    public boolean isCrafterItem (ItemStack is) {
        return is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.beastBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.cherishBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.diveBall))
                || /*is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.dreamBall)) || */is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.duskBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.fastBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.friendBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.greatBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.gsBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.healBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.heavyBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.levelBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.loveBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.lureBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.luxuryBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.moonBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.nestBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.netBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.parkBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.pokeBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.quickBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.repeatBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.premierBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.safariBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.sportBall)) || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.timerBall))
                || is.getType().matches(ItemStack.of((ItemType) PixelmonItemsPokeballs.ultraBall));
    }

    public boolean isBlacksmithItem (ItemStack is) {
        if (is.getType().getName().contains("thunder") || is.getType().getName().contains("water") || is.getType().getName().contains("fire") || is.getType().getName().contains("moon") || is.getType().getName().contains("sun") || is.getType().getName().contains("dawn")
            || is.getType().getName().contains("dusk") || is.getType().getName().contains("amethsyt") || is.getType().getName().contains("crystal") || is.getType().getName().contains("aether") || is.getType().getName().contains("magma") || is.getType().getName().contains("aqua")
            || is.getType().getName().contains("plasma") || is.getType().getName().contains("galactic") || is.getType().getName().contains("rocket") || is.getType().getName().contains("leaf") || is.getType().getName().contains("ice") || is.getType().getName().contains("flare")
            || is.getType().getName().contains("skull") || is.getType().getName().contains("aluminum") || is.getType().getName().contains("thunder")) {
                if (is.getType() instanceof PixelmonItemsTools) {
                    return true;
                } else {
                    System.out.println("DEBUG BLACKSMITH");
                }

        }
        return false;
    }
}
