package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Miner {
    /**
     *
     * Miner and Archaeologist's mining task are handled in this class, as they fire off the same event
     *
     */
    public Miner () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onMine (BlockEvent.BreakEvent e) {
        EntityPlayer forgePlayer = e.getPlayer();
        Player player = (Player) forgePlayer;
        if (isPixelmonOre(e.getState().getBlock().toString())) {

            if (config.isSkillEnabled("Miner")) {
                if (config.isSkillTaskEnabled("Miner", "Mining-Pixelmon-ores")) {
                    experienceHandler.addPoints("Miner", config.getEXPFromTask("Miner", "Mining-Pixelmon-ores"), player);
                    if (config.isSkillPerkEnabled("Miner")) {
                        if (accounts.getLevel("Miner", player) == config.getDefaultPerkLevel("Miner") || accounts.getLevel("Miner", player) == accounts.getNextPerkLevel("Miner", player)) {
                            accounts.setNextPerkLevel("Miner", player);
                            if (config.getDefaultPerkChance("Miner") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Miner")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " That was a thick chunk of ore! You found two more pieces!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getState().getBlock().toString().replace("{", "").replace("{", "").replace("block", "") + " 2");
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " That was a thick chunk of ore! You found two more pieces!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getState().getBlock().toString().replace("{", "").replace("{", "").replace("block", "") + " 2");
                            }
                        }
                    }
                }
            }

        } else if (isVanillaOre(e.getState().getBlock().toString())) {

            if (config.isSkillEnabled("Miner")) {
                if (config.isSkillTaskEnabled("Miner", "Mining-vanilla-ores")) {
                    experienceHandler.addPoints("Miner", config.getEXPFromTask("Miner", "Mining-vanilla-ores"), player);
                    if (config.isSkillPerkEnabled("Miner")) {
                        if (accounts.getLevel("Miner", player) == config.getDefaultPerkLevel("Miner") || accounts.getLevel("Miner", player) == accounts.getNextPerkLevel("Miner", player)) {
                            accounts.setNextPerkLevel("Miner", player);
                            if (config.getDefaultPerkChance("Miner") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Miner")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " That was a thick chunk of ore! You found two more pieces!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getState().getBlock().toString().replace("{", "").replace("{", "").replace("block", "") + " 2");
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " That was a thick chunk of ore! You found two more pieces!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.getState().getBlock().toString().replace("{", "").replace("{", "").replace("block", "") + " 2");
                            }
                        }
                    }
                }
            }

        } else if (e.getState().getBlock().toString().contains("pixelmon:fossil") && !e.getState().getBlock().toString().contains("machine")) {

            if (config.isSkillEnabled("Archaeologist")) {
                if (config.isSkillTaskEnabled("Archaeologist", "Mining-Fossils")) {
                    experienceHandler.addPoints("Archaeologist", config.getEXPFromTask("Archaeologist", "Mining-Fossils"), player);
                    if (config.isSkillPerkEnabled("Archaeologist")) {
                        if (accounts.getLevel("Archaeologist", player) == accounts.getNextPerkLevel("Archaeologist", player)) {
                            accounts.setNextPerkLevel("Archaeologist", player);
                            if (config.getDefaultPerkChance("Archaeologist") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Archaeologist")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " That was a big fossil! You found one more fossil!"));
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:fossil 1");
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " That was a big fossil! You found one more fossil!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:fossil 1");
                            }
                        }
                    }
                }
            }
        }

    }

    private boolean isPixelmonOre(String name) {
        return name.contains("thunder_stone_ore") || name.contains("leaf_stone_ore") || name.contains("water_stone_ore") || name.contains("fire_stone_ore") || name.contains("dawn_dusk_ore") || name.contains("bauxite_ore") || name.contains("sun_stone_ore") ||
                name.contains("ruby_ore") || name.contains("sapphire_ore") || name.contains("amethyst_ore") || name.contains("crystal_ore") || name.contains("silicon_ore") || name.contains("icestoneore");
    }

    private boolean isVanillaOre(String name) {
        return name.contains("emerald_ore") || name.contains("lapis_ore") || name.contains("diamond_ore") || name.contains("redstone_ore") || name.contains("gold_ore") || name.contains("iron_ore") ||
                name.contains("coal_ore") || name.contains("quartz_ore");
    }
}
