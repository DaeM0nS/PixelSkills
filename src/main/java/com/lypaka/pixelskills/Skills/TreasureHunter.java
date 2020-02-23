package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.PokeLootClaimedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class TreasureHunter {
    public TreasureHunter () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onPokeLootOpen (PokeLootClaimedEvent e) {
        Player player = (Player) e.player;

        if (config.isSkillEnabled("Treasure-Hunter")) {
            if (config.isSkillTaskEnabled("Treasure-Hunter", "Opening-PokeLoot-chests")) {
                experienceHandler.addPoints("Treasure-Hunter", config.getEXPFromTask("Treasure-Hunter", "Opening-PokeLoot-chests"), player);
                if (config.isSkillPerkEnabled("Treasure-Hunter")) {
                    if (accounts.getLevel("Treasure-Hunter", player) == config.getDefaultPerkLevel("Treasure-Hunter") || accounts.getLevel("Treasure-Hunter", player) == accounts.getNextPerkLevel("Treasure-Hunter", player)) {
                        accounts.setNextPerkLevel("Treasure-Hunter", player);
                        if (config.getDefaultPerkChance("Treasure-Hunter") > 0) {
                            if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Treasure-Hunter")) {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The chest contained another chest!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.chest.toString() + " 1");
                            }
                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The chest contained another chest!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + e.chest.toString() + " 1");
                        }
                    }
                }
            }
        }
    }
}
