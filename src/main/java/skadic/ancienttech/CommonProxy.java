package skadic.ancienttech;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import skadic.ancienttech.block.RegisterBlock;
import skadic.ancienttech.item.RegisterItem;

/**
 * Created by eti22 on 19.09.2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        RegisterItem.registerItems();
        RegisterBlock.registerBlocks();
    }

    public void init(FMLInitializationEvent event){

    }

    public void postInit(FMLPostInitializationEvent event){

    }



}
