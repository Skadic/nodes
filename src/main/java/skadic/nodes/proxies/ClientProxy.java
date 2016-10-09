package skadic.nodes.proxies;

import skadic.nodes.blocks.BlockRegister;
import skadic.nodes.items.ItemRegister;

/**
 * Created by eti22 on 20.03.2016.
 */
public class ClientProxy extends CommonProxy{
    @Override
    public void registerRenders(){
        ItemRegister.registerRenders();
        BlockRegister.registerRenders();
    }


}
