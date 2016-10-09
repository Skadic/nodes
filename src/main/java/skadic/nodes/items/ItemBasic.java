package skadic.nodes.items;

import skadic.nodes.Nodes;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by eti22 on 20.03.2016.
 */
public class ItemBasic extends Item{

    String name;

    public ItemBasic(String name){
        super();
        this.name = name;
        setUnlocalizedName(name);
        setCreativeTab(Nodes.nodeTab);
        GameRegistry.registerItem(this, name);
    }

    public String getName() {
        return name;
    }
}
