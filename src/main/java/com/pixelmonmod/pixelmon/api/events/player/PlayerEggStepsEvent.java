package com.pixelmonmod.pixelmon.api.events.player;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PlayerEggStepsEvent
extends Event {
    private EntityPlayerMP player;
    private int stepsRequired;

    public PlayerEggStepsEvent(EntityPlayerMP player, int stepsRequired) {
        this.player = player;
        this.stepsRequired = stepsRequired;
    }

    public EntityPlayerMP getPlayer() {
        return this.player;
    }

    public int getStepsRequired() {
        return this.stepsRequired;
    }

    public void setStepsRequired(int stepsRequired) {
        this.stepsRequired = stepsRequired;
    }
}