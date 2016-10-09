package skadic.ancienttech.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import skadic.ancienttech.AncientTech;

/**
 * Created by eti22 on 19.09.2016.
 */
public class RegisterItem {

    private static final String MODID = AncientTech.MODID + ':';

    public static void registerItems(){

    }

    public static void registerItemRenderer(){

    }

    private static void registerItem(Item item, String name){
        item.setUnlocalizedName("name");
        GameRegistry.register(item);
    }

    private static void registerRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(MODID + item.getUnlocalizedName().substring(5), "ienventory"));
    }

    private static void registerRender(Item item, int meta){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(MODID + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
