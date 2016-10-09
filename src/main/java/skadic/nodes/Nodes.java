package skadic.nodes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import skadic.nodes.blocks.BlockRegister;
import skadic.nodes.capabilities.ICapabilityNodeItem;
import skadic.nodes.capabilities.CapabilityNodeItemDefault;
import skadic.nodes.capabilities.CapabilityNodeItemStorage;
import skadic.nodes.crafting.CraftingRegister;
import skadic.nodes.events.EventHandlerNodes;
import skadic.nodes.items.ItemRegister;
import skadic.nodes.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by eti22 on 20.03.2016.
 */
@Mod(modid = Nodes.MODID, name = Nodes.MODNAME, version = Nodes.VERSION)
public class Nodes {

    public static final String MODID = "nodes";
    public static final String MODNAME = "Nodes";
    public static final String VERSION = "1.0";
    public static final String CLIENT_PROXY_CLASS = "skadic.nodes.proxies.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "skadic.nodes.proxies.CommonProxy";
    public static SimpleNetworkWrapper SNW;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Nodes instance;


    public static CreativeTabs nodeTab = new CreativeTabs("Nodes") {
        @Override
        public Item getTabIconItem() {
            return ItemRegister.itemNode;
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
