package com.lypaka.pixelskills.Utils;

import com.lypaka.pixelskills.Config.SkillsAccountManager;
import com.lypaka.pixelskills.PixelSkills;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class PerksHandler {
    public PerksHandler(PixelSkills plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigG();
        this.accounts = plugin.getAccountGs();
        this.accountManager = plugin.getAccountManager();
    }
    private ConfigGetters config;
    private AccountGetters accounts;
    private PixelSkills plugin;
    private SkillsAccountManager accountManager;

    public void givePerks (String skill, String task, Player player) {
        if (skill.equals("Archaeologist")) {
            if (task.equals("Mining-Fossils")) {
                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " That was a big fossil! You found one more fossil!"));
                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "give " + player.getName() + " pixelmon:fossil 1");
            } else if (task.equals("Reviving-Fossils")) {

            }
        }
    }
}
