package com.lime.terracart;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.EnumActionResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DismountHandler {
    @SubscribeEvent(priority= EventPriority.HIGH, receiveCanceled=true)
    public EnumActionResult onEvent(EntityMountEvent e)
    {
        World w = e.getWorldObj();
        if (!w.isRemote && e.isDismounting() && e.getEntityBeingMounted() instanceof EntityMinecart) {
            e.getEntityBeingMounted().setDead();
        }

        return EnumActionResult.SUCCESS;
    }
}
