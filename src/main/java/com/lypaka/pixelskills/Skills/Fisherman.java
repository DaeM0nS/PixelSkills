package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.FishingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Fisherman {

    public Fisherman () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onFish (FishingEvent.Catch e) {
        Player player = (Player) e.player;

        if (config.isSkillEnabled("Fisherman")) {
            if (config.isSkillTaskEnabled("Fisherman", "Successful-reel-ins")) {
                experienceHandler.addPoints("Fisherman", config.getEXPFromTask("Fisherman", "Successful-reel-ins"), player);
                if (config.isSkillPerkEnabled("Fisherman")) {
                    if (accounts.getLevel("Fisherman", player) == config.getDefaultPerkLevel("Fisherman") || accounts.getLevel("Fisherman", player) == accounts.getNextPerkLevel("Fisherman", player)) {
                        accounts.setNextPerkLevel("Fisherman", player);
                        if (config.getDefaultPerkChance("Fisherman") > 0) {
                            if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Fisherman")) {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " A Poke Ball got fished up with the Pokemon!"));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + "pixelmon:poke_ball 1");
                            }
                        } else {
                            player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " A Poke Ball got fished up with the Pokemon!"));
                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " " + "pixelmon:poke_ball 1");
                        }
                    }
                }
            }
        }
    }
}
