package com.lypaka.pixelskills.Skills;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.api.events.BeatTrainerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.math.BigDecimal;
import java.util.Optional;

public class FierceBattler {
    public FierceBattler () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onTrainerDefeat (BeatTrainerEvent e) {
        Player player = (Player) e.player;

        if (config.isSkillEnabled("Fierce Battler")) {
            if (config.isSkillTaskEnabled("Fierce Battler", "Defeating-NPCTrainers")) {
                experienceHandler.addPoints("Fierce Battler", config.getEXPFromTask("Fierce Battler", "Defeating-NPCTrainers"), player);
                if (config.isSkillPerkEnabled("Fierce Battler")) {
                    if (accounts.getLevel("Fierce Battler", player) == config.getDefaultPerkLevel("Fierce Battler") || accounts.getLevel("Fierce Battler", player) == accounts.getNextPerkLevel("Fierce Battler", player)) {
                        accounts.setNextPerkLevel("Fierce Battler", player);
                        if (config.getDefaultPerkChance("Fierce Battler") > 0) {
                            if (accounts.getPerkChance("Fierce Battler", player) == 0) {
                                if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Fierce Battler")) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The Trainer paid a little extra money!"));
                                    EventContext eventContext = EventContext.builder().add(EventContextKeys.PLUGIN, PixelSkills.getContainer()).build();
                                    Optional<EconomyService> econ = Sponge.getServiceManager().provide(EconomyService.class);
                                    if (econ.isPresent()) {
                                        Optional<UniqueAccount> a = econ.get().getOrCreateAccount(player.getUniqueId());
                                        Currency defaultCur = econ.get().getDefaultCurrency();
                                        a.get().deposit(defaultCur, BigDecimal.valueOf((e.trainer.getWinMoney() + (e.trainer.getWinMoney() * 0.25))), Cause.of(eventContext, PixelSkills.getContainer()));
                                    }
                                }
                            } else {
                                if (PixelSkills.getRandom().nextInt(100) < accounts.getPerkChance("Fierce Battler", player)) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelSkills", TextColors.GOLD, "]", TextColors.WHITE, " The Trainer paid a little extra money!"));
                                    EventContext eventContext = EventContext.builder().add(EventContextKeys.PLUGIN, PixelSkills.getContainer()).build();
                                    Optional<EconomyService> econ = Sponge.getServiceManager().provide(EconomyService.class);
                                    if (econ.isPresent()) {
                                        Optional<UniqueAccount> a = econ.get().getOrCreateAccount(player.getUniqueId());
                                        Currency defaultCur = econ.get().getDefaultCurrency();
                                        a.get().deposit(defaultCur, BigDecimal.valueOf((e.trainer.getWinMoney() + (e.trainer.getWinMoney() * 0.25))), Cause.of(eventContext, PixelSkills.getContainer()));
                                    }
                                }
                            }
                        } else {
                            EventContext eventContext = EventContext.builder().add(EventContextKeys.PLUGIN, PixelSkills.getContainer()).build();
                            Optional<EconomyService> econ = Sponge.getServiceManager().provide(EconomyService.class);
                            if (econ.isPresent()) {
                                Optional<UniqueAccount> a = econ.get().getOrCreateAccount(player.getUniqueId());
                                Currency defaultCur = econ.get().getDefaultCurrency();
                                a.get().deposit(defaultCur, BigDecimal.valueOf((e.trainer.getWinMoney() + (e.trainer.getWinMoney() * 0.25))), Cause.of(eventContext, PixelSkills.getContainer()));
                            }
                        }
                    }
                }
            }
        }
    }
}
