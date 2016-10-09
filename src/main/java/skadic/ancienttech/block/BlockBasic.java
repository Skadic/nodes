package skadic.ancienttech.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by eti22 on 19.09.2016.
 */
public class BlockBasic extends Block{

    public BlockBasic() {
        super(Material.ROCK);

        setHardness(1.0F);
        setResistance(10.0F);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);


    }
}
