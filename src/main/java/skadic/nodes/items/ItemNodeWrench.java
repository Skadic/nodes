package skadic.nodes.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.ICapabilityNodeItem;
import skadic.nodes.utils.TagRef;

import java.awt.*;
import java.util.List;

/**
 * Created by eti22 on 20.03.2016.
 */
public class ItemNodeWrench extends ItemBasic {

    public ItemNodeWrench(String name) {
        super(name);
        setMaxDamage(0);
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(item, 1, 0));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int metadata = stack.getMetadata();
        EnumWrenchMode wrenchMode = EnumWrenchMode.byMetadata(metadata);
        return super.getUnlocalizedName() + "." + wrenchMode.getName();
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String stackName = "Node Wrench: (" + EnumWrenchMode.byMetadata(stack.getMetadata()).getName() + ")";
        return stackName;
    }



    /*@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass) {

        switch(renderPass){
            case 0: return Color.WHITE.getRGB();
            case 1: {
                int metadata = stack.getMetadata();
                EnumWrenchMode wrenchMode = EnumWrenchMode.byMetadata(metadata);
                return wrenchMode.getRenderColor().getRGB();
            }
            default:{
                return Color.BLACK.getRGB();
            }
        }

    }*/

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float x, float y, float z, EnumHand hand) {

        if(world.getBlockState(pos).getBlock().hasTileEntity(world.getBlockState(pos))) {

            TileEntity te = world.getTileEntity(pos);
            NBTTagCompound NBTFluidNode = te.getTileData().getCompoundTag(TagRef.FLUID_NODE_TAG);
            NBTTagCompound NBTEnergyNode = te.getTileData().getCompoundTag(TagRef.ENERGY_NODE_TAG);

            if(player.isSneaking()) {
                if(stack.getMetadata() == 0 && te.hasCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null)) {
                    ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);

                    if (itemCap.hasNode()) {
                        itemCap.setHasNode(false);
                        if (!world.isRemote)
                            world.spawnEntityInWorld(new EntityItem(world,
                                    player.getPosition().getX(),
                                    player.getPosition().getY(),
                                    player.getPosition().getZ(),
                                    new ItemStack(ItemRegister.itemNodeItem, 1)));
                        return EnumActionResult.SUCCESS;
                    }
                }


                if (stack.getMetadata() == 1 && NBTFluidNode.getBoolean(TagRef.HAS_NODE)) {
                    NBTFluidNode.setBoolean(TagRef.HAS_NODE, false);
                    NBTFluidNode.setBoolean(TagRef.IS_EXPORTING, false);
                    if(!world.isRemote)
                        world.spawnEntityInWorld(new EntityItem(world,
                            player.getPosition().getX(),
                            player.getPosition().getY(),
                            player.getPosition().getZ(),
                            new ItemStack(ItemRegister.itemNodeFluid, 1)));
                    return EnumActionResult.SUCCESS;
                }


                if (stack.getMetadata() == 2 && NBTEnergyNode.getBoolean(TagRef.HAS_NODE)) {
                    NBTEnergyNode.setBoolean(TagRef.HAS_NODE, false);
                    NBTEnergyNode.setBoolean(TagRef.IS_EXPORTING, false);
                    if(!world.isRemote)
                        world.spawnEntityInWorld(new EntityItem(world,
                            player.getPosition().getX(),
                            player.getPosition().getY(),
                            player.getPosition().getZ(),
                            new ItemStack(ItemRegister.itemNodeEnergy, 1)));
                    return EnumActionResult.SUCCESS;
                }
                return EnumActionResult.PASS;
            }
                /*switch(stack.getMetadata()){
                    case 0: {
                        if (te.hasCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null)) {
                            ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
                            if(itemCap.hasNode()) {
                                itemCap.setExport(!itemCap.getExport());
                                return EnumActionResult.SUCCESS;
                            }
                            return EnumActionResult.PASS;
                        }
                    }

            }*/
        }else{
            if(player.isSneaking()) {

                int metadata = stack.getMetadata();

                if(metadata != 2)
                    stack.setItemDamage(metadata + 1);
                else
                    stack.setItemDamage(0);
                return EnumActionResult.SUCCESS;
            }
        }

        return EnumActionResult.PASS;
    }

    public enum EnumWrenchMode implements IStringSerializable{

        ITEM(0, "Item", Color.GREEN),
        FLUID(1, "Fluid", Color.BLUE),
        ENERGY(2, "Energy", Color.RED);

        public int getMetadata(){
            return this.meta;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static EnumWrenchMode byMetadata(int meta){
            if(meta < 0 || meta >= META_LOOKUP.length){
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        @Override
        public String getName() {
            return this.name;
        }

        public Color getRenderColor(){return renderColor;}

        private final int meta;
        private final String name;
        private final Color renderColor;
        private static final EnumWrenchMode[] META_LOOKUP = new EnumWrenchMode[values().length];

        EnumWrenchMode(int meta, String name, Color renderColor){
            this.meta = meta;
            this.name = name;
            this.renderColor = renderColor;
        }

        static{
            for(EnumWrenchMode value : values()){
                META_LOOKUP[value.getMetadata()] = value;
            }
        }
    }

}
