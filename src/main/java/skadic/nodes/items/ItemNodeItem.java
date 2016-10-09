package skadic.nodes.items;

import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.ICapabilityNodeItem;

/**
 * Created by eti22 on 20.03.2016.
 */
public class ItemNodeItem extends ItemNode{
    public ItemNodeItem(String name){
        super(name);
    }


    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        if(world.getBlockState(pos).getBlock() instanceof BlockContainer){

            TileEntity te = world.getTileEntity(pos);

            if(!te.hasCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null)){
                return EnumActionResult.PASS;
            }else{
                ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);

                if(!itemCap.hasNode()){
                    itemCap.setHasNode(true);
                    stack.stackSize--;
                    return EnumActionResult.SUCCESS;
                }

            }

            /*NBTTagCompound tileData = te.getTileData().getCompoundTag(TagRef.ITEM_NODE_TAG);
            NBTTagCompound nbt = new NBTTagCompound();

            if(!tileData.getBoolean(TagRef.HAS_NODE) || !tileData.hasKey(TagRef.HAS_NODE)){
                nbt.setBoolean(TagRef.HAS_NODE, true);
                stack.stackSize--;

                te.getTileData().setTag(TagRef.ITEM_NODE_TAG, nbt);
                return EnumActionResult.SUCCESS;
            }*/

        }else{

        }

        return EnumActionResult.PASS;
    }






}
