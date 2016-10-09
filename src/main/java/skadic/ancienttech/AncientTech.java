package skadic.ancienttech;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = AncientTech.MODID,name = AncientTech.MODNAME, version = AncientTech.VERSION)
public class AncientTech
{
    public static final String MODID = "ancienttech";
    public static final String MODNAME = "Ancient Technology";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static AncientTech instance = new AncientTech();

    @SidedProxy(clientSide = "skadic.ancienttech.ClientProxy", serverSide = "skadic.ancienttech.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs anciTab = new CreativeTabs("Ancient Technology") {
        @Override
        public Item getTabIconItem() {
            return Items.ARROW;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
