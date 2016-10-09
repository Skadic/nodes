package skadic.nodes.blocks;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import skadic.nodes.Nodes;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;

/**
 * Created by eti22 on 20.03.2016.
 */
public class BlockRegister {

    public static Block blockNodeController;

    public BlockRegister(){
        initBlocks();
    }

    private static void initBlocks(){
        blockNodeController = new BlockNodeNexus("blockNodeNexus");
        registerBlock(blockNodeController, "blockNodeNexus");
    }

    public static void registerRenders(){
        registerBlockRender(blockNodeController, 0);
    }

    private static void registerBlockRender(Block block, int meta){
        Item item = Item.getItemFromBlock(block);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Nodes.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }

    private static void registerBlock(Block block, String name){
        block.setUnlocalizedName(name);
        block.setRegistryName(name);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        GameRegistry.register(block);
    }

}
