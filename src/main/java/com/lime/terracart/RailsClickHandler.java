package com.lime.terracart;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RailsClickHandler {
    @SubscribeEvent(priority= EventPriority.HIGH, receiveCanceled=true)
    public EnumActionResult onEvent(PlayerInteractEvent.RightClickBlock event)
    {
        World worldIn = event.getEntity().world;
        BlockPos pos = event.getPos();

        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (!BlockRailBase.isRailBlock(iblockstate) || !(event.getEntity() instanceof EntityPlayer) || (event.getEntityPlayer().inventory.getCurrentItem() != ItemStack.EMPTY))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (!worldIn.isRemote)
            {
                BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = iblockstate.getBlock() instanceof BlockRailBase ? ((BlockRailBase)iblockstate.getBlock()).getRailDirection(worldIn, pos, iblockstate, null) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
                double d0 = 0.0D;

                if (blockrailbase$enumraildirection.isAscending())
                {
                    d0 = 0.5D;
                }

                EntityMinecart cart;
                if (Config.use_vanilla_cart){
                    cart = new EntityMinecartEmpty(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.0625D + d0, (double)pos.getZ() + 0.5D);
                } else {
                    cart = new EntityTerraCart(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.0625D + d0, (double)pos.getZ() + 0.5D);
                }

                worldIn.spawnEntity(cart);
                event.getEntityPlayer().startRiding(cart, true);
            }

            return EnumActionResult.SUCCESS;
        }
    }
}
