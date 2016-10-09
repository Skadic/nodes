package skadic.ancienttech.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.Sys;
import skadic.ancienttech.AncientTech;
import skadic.ancienttech.item.RegisterItem;

/**
 * Created by eti22 on 19.09.2016.
 */
public class RegisterBlock {

    private static String MODID = AncientTech.MODID + ":";

    public static Block basicBlock;

    public static void registerBlocks(){
        (basicBlock = new BlockBasic()).setCreativeTab(AncientTech.anciTab);

        registerBlock(basicBlock, "blockBasic");
    }

    public static void registerBlockRenderer(){
        registerRender(basicBlock);
    }


    private static void registerBlock(Block block, String name){
        block.setUnlocalizedName(name);
        block.setRegistryName(name);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        GameRegistry.register(block);
    }

    private static void registerRender(Block block){
        Item item = Item.getItemFromBlock(block);
        ModelResourceLocation resLoc = new ModelResourceLocation(block.getRegistryName(), "inventory");


        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, resLoc);
    }

    private static void registerRender(Block block, int meta){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(MODID + block.getUnlocalizedName().substring(5), "inventory"));
    }
}
