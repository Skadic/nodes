package skadic.nodes.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by eti22 on 20.03.2016.
 */
public class ItemNodeEnergy extends ItemNode{
    public ItemNodeEnergy(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float x, float y, float z) {
        if(world.getBlockState(pos).getBlock().hasTileEntity(world.getBlockState(pos))){

            TileEntity te = world.getTileEntity(pos);
            NBTTagCompound tileData = te.getTileData().getCompoundTag("ItemNodeEnergy");
            NBTTagCompound nbt = new NBTTagCompound();

            if(!tileData.getBoolean("hasNode") || !tileData.hasKey("hasNode")){
                nbt.setBoolean("hasNode", true);
                stack.stackSize--;

                te.getTileData().setTag("ItemNodeEnergy", nbt);
                return EnumActionResult.SUCCESS;
            }

        }

        return EnumActionResult.FAIL;
    }
}
