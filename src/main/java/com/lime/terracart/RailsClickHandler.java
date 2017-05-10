package com.lime.terracart;

import com.lime.terracart.entities.EntityTerraCart;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import static net.minecraftforge.event.entity.player.PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK;

public class RailsClickHandler {
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(PlayerInteractEvent event)
    {
        if (
                !event.world.isRemote &&
                (event.action == RIGHT_CLICK_BLOCK) &&
                (event.entityPlayer.getHeldItem() == null) &&
                BlockRailBase.func_150051_a(event.world.getBlock(event.x, event.y, event.z))
        ) {
            EntityTerraCart cart = new EntityTerraCart(
                event.world,
                (double)((float)event.x + 0.5F),
                (double)((float)event.y + 0.5F),
                (double)((float)event.z + 0.5F)
            );
            event.world.spawnEntityInWorld(cart);
            event.entity.mountEntity(cart);
        }
    }
}
