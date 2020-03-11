package com.lypaka.pixelskills.Skills;

import java.io.IOException;
import java.util.UUID;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Config.ConfigManager;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.lypaka.pixelskills.Utils.ConfigGetters;
import com.lypaka.pixelskills.Utils.ExperienceHandler;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.api.events.EggHatchEvent;
import com.pixelmonmod.pixelmon.api.events.PokedexEvent;
import com.pixelmonmod.pixelmon.api.events.player.PlayerEggStepsEvent;
import com.pixelmonmod.pixelmon.api.pokemon.IHatchEggs;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.comm.ChatHandler;
import com.pixelmonmod.pixelmon.comm.EnumUpdateType;
import com.pixelmonmod.pixelmon.config.PixelmonConfig;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.pokedex.EnumPokedexRegisterStatus;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class Breeder {
    public Breeder () {
        accounts = PixelSkills.getAccountGs();
        config = PixelSkills.getConfigG();
        experienceHandler = PixelSkills.getExperienceHandler();
    }

    private AccountGetters accounts;
    private ConfigGetters config;
    private ExperienceHandler experienceHandler;

    @SubscribeEvent
    public void onEggGet (BreedEvent.MakeEgg e) throws IOException {
        UUID uuid = e.owner;
        Player player = Sponge.getServer().getPlayer(uuid).get();

        if (config.isSkillEnabled("Breeder")) {
            if (config.isSkillTaskEnabled("Breeder", "Making-eggs")) {
                experienceHandler.addPoints("Breeder", config.getEXPFromTask("Breeder", "Making-eggs"), player);
                if (config.isSkillPerkEnabled("Breeder")) {
                    if (accounts.getLevel("Breeder", player) == config.getDefaultPerkLevel("Breeder") || accounts.getLevel("Breeder", player) == accounts.getNextPerkLevel("Breeder", player)) {
                        accounts.setNextPerkLevel("Breeder", player);
                        if (config.getDefaultPerkChance("Breeder") > 0) {
                            if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Breeder")) {
                                if (e.getEgg().getStats().ivs.hp != 31) {
                                    e.getEgg().getStats().ivs.set(StatsType.HP, ((e.getEgg().getStats().ivs.hp / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().getStats().ivs.hp);
                                    //e.getEgg().updateStats();
                                }
                                if (e.getEgg().getStats().ivs.attack != 31) {
                                    e.getEgg().getStats().ivs.set(StatsType.Attack, ((e.getEgg().getStats().ivs.attack / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().getStats().ivs.attack);
                                    //e.getEgg().updateStats();
                                }
                                if (e.getEgg().getStats().ivs.defence != 31) {
                                    e.getEgg().getStats().ivs.set(StatsType.Defence, ((e.getEgg().getStats().ivs.defence / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().getStats().ivs.defence);
                                    //e.getEgg().updateStats();
                                }
                                if (e.getEgg().getStats().ivs.specialAttack != 31) {
                                    e.getEgg().getStats().ivs.set(StatsType.SpecialAttack, ((e.getEgg().getStats().ivs.specialAttack / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().getStats().ivs.specialAttack);
                                    //e.getEgg().updateStats();
                                }
                                if (e.getEgg().getStats().ivs.specialDefence != 31) {
                                    e.getEgg().getStats().ivs.set(StatsType.SpecialDefence, ((e.getEgg().getStats().ivs.specialDefence / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().getStats().ivs.specialDefence);
                                    //e.getEgg().updateStats();
                                }
                                if (e.getEgg().getStats().ivs.speed != 31) {
                                    e.getEgg().getStats().ivs.set(StatsType.Speed, ((e.getEgg().getStats().ivs.speed / 31) * (accounts.getLevel("Breeder", player) / 2)) + e.getEgg().getStats().ivs.speed);
                                    //e.getEgg().updateStats();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEggHatch (EggHatchEvent e) {
        if (ConfigManager.getConfigNode("Skills", "Breeder", "isEnabled").getValue().equals(true)) {
            Player player = (Player) e.pokemon.getOwnerPlayer();
            
            if (config.isSkillEnabled("Breeder")) {
                if (config.isSkillTaskEnabled("Breeder", "Hatching-eggs")) {
                    experienceHandler.addPoints("Breeder", config.getEXPFromTask("Breeder", "Making-eggs"), player);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START && event.side == Side.SERVER) {
            PlayerPartyStorage storage = Pixelmon.storageManager.getParty((EntityPlayerMP)event.player);
            ++storage.transientData.eggTick;
            if (storage.transientData.eggTick >= 20) {
                storage.transientData.eggTick = 0;
                this.calculateWalkedSteps((EntityPlayerMP)event.player);
            }
        }
    }
    
    public int calculateWalkedSteps(EntityPlayerMP playerMP) {
        PlayerPartyStorage p = Pixelmon.storageManager.getParty(playerMP);
    	int posX = playerMP.getPosition().getX();
        int posZ = playerMP.getPosition().getZ();
        int changeX = p.transientData.lastEggX - posX;
        int changeZ = p.transientData.lastEggZ - posZ;
        p.transientData.lastEggX = posX;
        p.transientData.lastEggZ = posZ;
        if (changeX == -posX && changeZ == -posZ) {
            return 0;
        }
        int stepsTaken = Math.abs(changeX) + Math.abs(changeZ);
        if (stepsTaken > 20 || stepsTaken == 0) {
            return 0;
        }
        int hatchBonus = 1;
        for(Pokemon poke : p.getTeam()) {
        	if(IHatchEggs.BONUS_ABILITIES.contains(poke.getAbilityName()))
        		hatchBonus=2;
        }
        for ( Pokemon poke : p.getAll()) {
        	NBTTagCompound pokemonNBT = new NBTTagCompound();
        	poke.writeToNBT(pokemonNBT);
            if (pokemonNBT == null || !pokemonNBT.getBoolean("isEgg")) continue;
            int currentSteps = pokemonNBT.getInteger("steps");
            pokemonNBT.setInteger("steps", currentSteps += stepsTaken);
            int stepsNeeded = PixelmonConfig.stepsPerEggCycle;
            PlayerEggStepsEvent event = new PlayerEggStepsEvent(playerMP, stepsNeeded);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) {
                return 0;
            }
            stepsNeeded = event.getStepsRequired();
            if (currentSteps > stepsNeeded) {
                pokemonNBT.setInteger("steps", 0);
                int eggCycles = pokemonNBT.getInteger("eggCycles");
                pokemonNBT.setInteger("eggCycles", eggCycles -= Math.max(1, hatchBonus));
                if (eggCycles < 0) {
                    this.hatchEgg(playerMP, Pixelmon.pokemonFactory.create(pokemonNBT));
                }
            }
        }
        return stepsTaken;
    }
    
    public void hatchEgg(EntityPlayerMP player, Pokemon pokemon) {
        if (player != null) {
            pokemon.setOriginalTrainer(player);
        } else {
        	pokemon.setOriginalTrainer(pokemon.getOwnerPlayerUUID(), null);
        }
        pokemon.setEggCycles(null);
        if (pokemon.getStorage() instanceof PlayerPartyStorage) {
            PlayerPartyStorage party = (PlayerPartyStorage)pokemon.getStorage();
            if (party.pokedex.get(pokemon.getSpecies().getNationalPokedexInteger()) != EnumPokedexRegisterStatus.caught && !Pixelmon.EVENT_BUS.post(new PokedexEvent(player.getUniqueID(), pokemon, EnumPokedexRegisterStatus.caught, "egg"))) {
                party.pokedex.set(pokemon.getSpecies().getNationalPokedexInteger(), EnumPokedexRegisterStatus.caught);
                party.pokedex.update();
            }
        }
        if (player != null) {
            ChatHandler.sendFormattedChat(player, TextFormatting.GREEN, "pixelmon.egg.hatching", new Object[]{new TextComponentTranslation("pixelmon." + pokemon.getSpecies().name.toLowerCase() + ".name", new Object[0])});
        }
        pokemon.markDirty(EnumUpdateType.Egg, EnumUpdateType.OriginalTrainer);
        Pixelmon.EVENT_BUS.post(new EggHatchEvent(pokemon));
    }
    
    /**
     * This is the perk for the hatching eggs task
     */

    @SubscribeEvent
    public void onEggHatching (PlayerEggStepsEvent e) {
        Player player = (Player) e.getPlayer();

        if (config.isSkillEnabled("Breeder")) {
            if (config.isSkillTaskEnabled("Breeder", "Making-eggs")) {
                experienceHandler.addPoints("Breeder", config.getEXPFromTask("Breeder", "Making-eggs"), player);
                if (config.isSkillPerkEnabled("Breeder")) {
                    if (accounts.getLevel("Breeder", player) == config.getDefaultPerkLevel("Breeder") || accounts.getLevel("Breeder", player) == accounts.getNextPerkLevel("Breeder", player)) {
                    	if (config.getDefaultPerkChance("Breeder") > 0) {
                            if (PixelSkills.getRandom().nextInt(100) < config.getDefaultPerkChance("Breeder")) {
                                double mod = (accounts.getLevel("Breeder", player) * 0.01);
                                double newSteps = mod * e.getStepsRequired();
                                e.setStepsRequired((int) (e.getStepsRequired() - newSteps));
                            }
                        } else {
                            double mod = (accounts.getLevel("Breeder", player) * 0.01);
                            double newSteps = mod * e.getStepsRequired();
                            e.setStepsRequired((int) (e.getStepsRequired() - newSteps));
                        }
                    }
                }
            }
        }
    }
}
