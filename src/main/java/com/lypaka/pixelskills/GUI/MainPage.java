package com.lypaka.pixelskills.GUI;

import java.util.Objects;
import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.codehusky.huskyui.StateContainer;
import com.codehusky.huskyui.states.Page;
import com.codehusky.huskyui.states.action.ActionType;
import com.codehusky.huskyui.states.action.runnable.RunnableAction;
import com.codehusky.huskyui.states.element.ActionableElement;
import com.codehusky.huskyui.states.element.Element;
import com.google.common.collect.Lists;
import com.lypaka.pixelskills.PixelSkills;
import com.lypaka.pixelskills.Utils.AccountGetters;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.config.PixelmonItemsApricorns;
import com.pixelmonmod.pixelmon.config.PixelmonItemsFossils;
import com.pixelmonmod.pixelmon.config.PixelmonItemsHeld;
import com.pixelmonmod.pixelmon.config.PixelmonItemsPokeballs;
import com.pixelmonmod.pixelmon.config.PixelmonItemsTools;
import com.pixelmonmod.pixelmon.entities.pixelmon.EnumSpecialTexture;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.items.PixelmonItemBlock;
import com.pixelmonmod.pixelmon.util.helpers.SpriteHelper;

import net.minecraft.nbt.NBTTagCompound;

public class MainPage {

    private static AccountGetters accounts = PixelSkills.getAccountGs();
    private final static ItemType spriteItem = Sponge.getRegistry().getType(ItemType.class, "pixelmon:pixelmon_sprite").get();
    
    public static void openMainGUI(Player player) {
        StateContainer container = new StateContainer();
        Page.PageBuilder main = Page.builder()
                .setAutoPaging(false)
                .setInventoryDimension(InventoryDimension.of(9, 5))
                .setTitle(Text.of(TextColors.DARK_RED, "PixelSkills"))
                .setEmptyStack(EmptyPage.empty());
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}) {
            main.putElement(i, new Element(EmptyPage.empty()));
        }
        main.putElement(0, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Breeder").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItems.hourglassSilver)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Breeder"))
                        .build()
        ));
        main.putElement(2, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Catcher").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItemsPokeballs.pokeBall)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Catcher"))
                        .build()
        ));
        main.putElement(4, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Crafter").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) Objects.requireNonNull(PixelmonItemBlock.getByNameOrId("pixelmon:clothed_table")))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Crafter"))
                        .build()
        ));
        main.putElement(6, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Fierce Battler").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItems.trainerEditor)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Fierce Battler"))
                        .build()
        ));
        main.putElement(8, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Fisherman").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItems.goodRod)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Fisherman"))
                        .build()
        ));
        
        Pokemon pokemon = Pixelmon.pokemonFactory.create(EnumSpecies.Venusaur);
        pokemon.setShiny(true);
        NBTTagCompound partyNbtSlot = new NBTTagCompound();
    	pokemon.writeToNBT(partyNbtSlot);
        Optional<ItemStack> itemStackForNBT = getItemStackForNBT(partyNbtSlot);
        
        main.putElement(18, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Shiny Hunter").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType(itemStackForNBT.get().getType())
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Shiny Hunter"))
                        .build()
        ));
        
        pokemon = Pixelmon.pokemonFactory.create(EnumSpecies.Regirock);
        pokemon.setShiny(false);
        partyNbtSlot = new NBTTagCompound();
    	pokemon.writeToNBT(partyNbtSlot);
        itemStackForNBT = getItemStackForNBT(partyNbtSlot);

        main.putElement(20, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Legendary Master").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType(itemStackForNBT.get().getType())
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Legendary Master"))
                        .build()
        ));
        main.putElement(22, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Scientist").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItems.potion)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Scientist"))
                        .build()
        ));
        main.putElement(24, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Botanist").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItemsApricorns.apricornYellow)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Botanist"))
                        .build()
        ));
        main.putElement(26, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Miner").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItemsTools.thunderstonePickaxeItem)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Miner"))
                        .build()
        ));
        main.putElement(36, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Archaeologist").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItemsFossils.coverFossilCovered)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Archaeologist"))
                        .build()
        ));
        main.putElement(38, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Blacksmith").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItemsTools.hammerIron)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Blacksmith"))
                        .build()
        ));
        main.putElement(40, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Treasure Hunter").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) Objects.requireNonNull(PixelmonItemBlock.getByNameOrId("pixelmon:poke_chest")))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Treasure Hunter"))
                        .build()
        ));
        main.putElement(42, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Boss Conqueror").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType((ItemType) PixelmonItemsHeld.altarianite)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Boss Conqueror"))
                        .build()
        ));
        main.putElement(44, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openStatsPage(container, player, "Poke Exterminator").openState(player, "modifiers")),
                ItemStack.builder()
                        .itemType(ItemTypes.BARRIER)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Poke Exterminator"))
                        .build()
        ));
        container.setInitialState(main.build("main"));
        container.launchFor(player);
    }

    private static StateContainer openStatsPage(StateContainer container, Player player, String skill) {
        if (container.hasState("modifiers")) {
            container.removeState("modifiers");
        }
        Page.PageBuilder builder = Page.builder()
                .setAutoPaging(false)
                .setTitle(Text.of(TextColors.BLACK, skill))
                .setInventoryDimension(InventoryDimension.of(9, 1))
                .setEmptyStack(EmptyPage.empty());
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}) {
            builder.putElement(i, new Element(EmptyPage.empty()));
        }
        builder.putElement(1, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openMainGUI(player)),
                ItemStack.builder()
                        .itemType(ItemTypes.PAPER)
                        .add(Keys.DISPLAY_NAME, Text.of("Level"))
                        .add(Keys.ITEM_LORE, Lists.newArrayList(Text.of(getlevelfromSkill(skill, player))))
                        .build()

        ));
        builder.putElement(4, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openMainGUI(player)),
                ItemStack.builder()
                        .itemType(ItemTypes.PAPER)
                        .add(Keys.DISPLAY_NAME, Text.of("EXP"))
                        .add(Keys.ITEM_LORE, Lists.newArrayList(Text.of(getEXPfromSkill(skill, player))))
                        .build()

        ));
        builder.putElement(7, new ActionableElement(
                new RunnableAction(container, ActionType.NONE, "", c -> openMainGUI(player)),
                ItemStack.builder()
                        .itemType(ItemTypes.PAPER)
                        .add(Keys.DISPLAY_NAME, Text.of("EXP-to-Levelup"))
                        .add(Keys.ITEM_LORE, Lists.newArrayList(Text.of(getEXPTOLEVELUPfromSkill(skill, player))))
                        .build()

        ));
        container.addState(builder.build("modifiers"));
        return container;
    }



    private static int getlevelfromSkill(String skill, Player player) {
        return accounts.getLevel(skill, player);
    }

    private static int getEXPfromSkill(String skill, Player player) {
        return accounts.getEXP(skill, player);
    }

    private static int getEXPTOLEVELUPfromSkill(String skill, Player player) {
        return accounts.getEXPtoLvl(skill, player);
    }
    private static Optional<ItemStack> getItemStackForNBT(NBTTagCompound slotTag) {
        if (slotTag == null) {
            return Optional.empty();
        }
        String filePath = "pixelmon:sprites/";
        String pokemonID = slotTag.getString("Name");
        EnumSpecies enumPokemon = EnumSpecies.getFromNameAnyCase(pokemonID);
        boolean isShiny = slotTag.getBoolean("IsShiny");
        if (isShiny) {
            filePath = filePath + "shiny";
        }
        filePath = filePath + "pokemon/" + String.format("%03d", enumPokemon.getNationalPokedexInteger()) + SpriteHelper.getSpriteExtra(pokemonID, 0, Gender.Male, EnumSpecialTexture.None.id);
        NBTTagCompound spriteTag = new NBTTagCompound();
        spriteTag.setString("SpriteName", filePath);
        ItemStack spriteStack = ItemStack.of(spriteItem, 1);
        spriteStack = ItemStack.builder().fromContainer(spriteStack.toContainer().set(DataQuery.of("UnsafeData", "SpriteName"), filePath)).build();
        spriteStack.offer(Keys.DISPLAY_NAME, Text.of(pokemonID));
        return Optional.of(spriteStack);
    }

}
