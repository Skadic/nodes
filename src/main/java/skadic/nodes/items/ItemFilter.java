package skadic.nodes.items;

import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.ICapabilityNodeItem;

/**
 * Created by eti22 on 10.10.2016.
 */
public class ItemFilter extends ItemBasic{

    public ItemFilter(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if(world.getBlockState(pos).getBlock() instanceof BlockContainer) {
            TileEntity te = world.getTileEntity(pos);
            if (player.isSneaking()) {
                if (te.hasCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null)) {
                    ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);

                    if (itemCap.setHasFilter(true)) {
                        stack.stackSize--;
                        return EnumActionResult.SUCCESS;
                    }
                    return EnumActionResult.PASS;
                }
            }
        }
        return EnumActionResult.PASS;
    }
}
