package skadic.nodes.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import skadic.nodes.Nodes;
import skadic.nodes.tileentities.TileEntityNodeNexus;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

/**
 * Created by eti22 on 20.03.2016.
 */
public class BlockNodeNexus extends BlockContainer{

    String name;
    int radius = 5;
    private final AxisAlignedBB bounds = new AxisAlignedBB(0.125F,0.125F,0.125F,0.875F,0.875F,0.875F);

    public BlockNodeNexus(String name) {
        super(Material.ROCK);
        this.name = name;
        setCreativeTab(Nodes.nodeTab);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(player.isSneaking())return false;

        player.openGui(Nodes.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());

        return true;
    }

    public AxisAlignedBB getBounds() {
        return bounds;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityNodeNexus();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
