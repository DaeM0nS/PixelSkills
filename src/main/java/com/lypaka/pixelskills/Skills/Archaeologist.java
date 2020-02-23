package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.PixelmonReceivedEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;

public class Archaeologist {
    public Archaeologist () {
        accounts = PixelSkills.getAccountGs();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config = PixelSkills.getConfigG();
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onFossilRevive (PixelmonReceivedEvent e) throws IOException {
        if (e.receiveType.toString().contains("Fossil")) {
            EntityPlayerMP forgePlayer = e.player;
            Player player = (Player) forgePlayer;
            if (config.isSkillEnabled("Archaeologist")) {
                if (config.isSkillTaskEnabled("Archaeologist", "Reviving-Fossils")) {
                    experienceHandler.addPoints("Archaeologist", config.getEXPFromTask("Archaeologist", "Reviving-Fossils"), player);
                    if (config.isSkillPerkEnabled("Archaeologist")) {
                        if (accounts.getLevel("Archaeologist", player) == config.getDefaultPerkLevel("Archaeologist") || accounts.getLevel("Archaeologist", player) == accounts.getNextPerkLevel("Archaeologist", player)) {
                            accounts.setNextPerkLevel("Archaeologist", player);
                            if (config.getDefaultPerkChance("Archaeologist") > 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Archaeologist")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                    if (e.pokemon.stats.IVs.HP != 31) {
                                        e.pokemon.stats.IVs.HP = ((e.pokemon.stats.IVs.HP / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.HP;
                                        e.pokemon.updateStats();
                                    }
                                    if (e.pokemon.stats.IVs.Attack != 31) {
                                        e.pokemon.stats.IVs.Attack = ((e.pokemon.stats.IVs.Attack / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.Attack;
                                        e.pokemon.updateStats();
                                    }
                                    if (e.pokemon.stats.IVs.Defence != 31) {
                                        e.pokemon.stats.IVs.Defence = ((e.pokemon.stats.IVs.Defence / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.Defence;
                                        e.pokemon.updateStats();
                                    }
                                    if (e.pokemon.stats.IVs.SpAtt != 31) {
                                        e.pokemon.stats.IVs.SpAtt = ((e.pokemon.stats.IVs.SpAtt / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.SpAtt;
                                        e.pokemon.updateStats();
                                    }
                                    if (e.pokemon.stats.IVs.SpDef != 31) {
                                        e.pokemon.stats.IVs.SpDef = ((e.pokemon.stats.IVs.SpDef / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.SpDef;
                                        e.pokemon.updateStats();
                                    }
                                    if (e.pokemon.stats.IVs.Speed != 31) {
                                        e.pokemon.stats.IVs.Speed = ((e.pokemon.stats.IVs.Speed / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.Speed;
                                        e.pokemon.updateStats();
                                    }
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " Your experience tells you this Pokemon will be a good one!"));
                                if (e.pokemon.stats.IVs.HP != 31) {
                                    e.pokemon.stats.IVs.HP = ((e.pokemon.stats.IVs.HP / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.HP;
                                    e.pokemon.updateStats();
                                }
                                if (e.pokemon.stats.IVs.Attack != 31) {
                                    e.pokemon.stats.IVs.Attack = ((e.pokemon.stats.IVs.Attack / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.Attack;
                                    e.pokemon.updateStats();
                                }
                                if (e.pokemon.stats.IVs.Defence != 31) {
                                    e.pokemon.stats.IVs.Defence = ((e.pokemon.stats.IVs.Defence / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.Defence;
                                    e.pokemon.updateStats();
                                }
                                if (e.pokemon.stats.IVs.SpAtt != 31) {
                                    e.pokemon.stats.IVs.SpAtt = ((e.pokemon.stats.IVs.SpAtt / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.SpAtt;
                                    e.pokemon.updateStats();
                                }
                                if (e.pokemon.stats.IVs.SpDef != 31) {
                                    e.pokemon.stats.IVs.SpDef = ((e.pokemon.stats.IVs.SpDef / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.SpDef;
                                    e.pokemon.updateStats();
                                }
                                if (e.pokemon.stats.IVs.Speed != 31) {
                                    e.pokemon.stats.IVs.Speed = ((e.pokemon.stats.IVs.Speed / 31) * (accounts.getLevel("Archaeologist", player) / 2)) + e.pokemon.stats.IVs.Speed;
                                    e.pokemon.updateStats();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
