package com.lime.terracart;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
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
        World worldIn = event.getEntityPlayer().getEntityWorld();
        BlockPos pos = event.getPos();

        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (!BlockRailBase.isRailBlock(iblockstate))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (!worldIn.isRemote)
            {
                BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = iblockstate.getBlock() instanceof BlockRailBase ? (BlockRailBase.EnumRailDirection)iblockstate.getValue(((BlockRailBase)iblockstate.getBlock()).getShapeProperty()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
                double d0 = 0.0D;

                if (blockrailbase$enumraildirection.isAscending())
                {
                    d0 = 0.5D;
                }

                EntityTerraCart cart = new EntityTerraCart(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.0625D + d0, (double)pos.getZ() + 0.5D);

                worldIn.spawnEntityInWorld(cart);
                event.getEntityPlayer().startRiding(cart, true);
            }

            return EnumActionResult.SUCCESS;
        }
    }
}
