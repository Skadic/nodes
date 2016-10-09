package skadic.ancienttech;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import skadic.ancienttech.block.RegisterBlock;
import skadic.ancienttech.item.RegisterItem;

/**
 * Created by eti22 on 19.09.2016.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);

        RegisterBlock.registerBlockRenderer();
        RegisterItem.registerItemRenderer();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

}
