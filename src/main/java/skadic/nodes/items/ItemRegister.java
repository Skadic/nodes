package skadic.nodes.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import skadic.nodes.Nodes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;

/**
 * Created by eti22 on 20.03.2016.
 */
public class ItemRegister {

    public static Item
    itemNode,
    itemNodeItem,
    itemNodeFluid,
    itemNodeEnergy,
    itemNodeWrench
            ;

    public ItemRegister(){
        initItems();
    }

    private static void initItems(){
        itemNode = new ItemBasic("itemNode");
        itemNodeItem = new ItemNodeItem("itemNodeItem");
        itemNodeFluid = new ItemNodeFluid("itemNodeFluid");
        itemNodeEnergy = new ItemNodeEnergy("itemNodeEnergy");
        itemNodeWrench = new ItemNodeWrench("itemNodeWrench");
    }

    public static void registerRenders(){
        registerItemRender(itemNode, 0);
        registerItemRender(itemNodeItem, 0);
        registerItemRender(itemNodeFluid, 0);
        registerItemRender(itemNodeEnergy, 0);
        registerItemRender(itemNodeWrench, 0);
        registerItemRender(itemNodeWrench, 1);
        registerItemRender(itemNodeWrench, 2);
    }

    private static void registerItemRender(Item item, int meta){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Nodes.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
