package skadic.nodes.proxies;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import skadic.nodes.Nodes;
import skadic.nodes.blocks.BlockRegister;
import skadic.nodes.capabilities.CapabilityNodeItemDefault;
import skadic.nodes.capabilities.CapabilityNodeItemStorage;
import skadic.nodes.capabilities.ICapabilityNodeItem;
import skadic.nodes.crafting.CraftingRegister;
import skadic.nodes.events.CapabilityHandler;
import skadic.nodes.events.EventHandlerNodes;
import skadic.nodes.gui.GuiID;
import skadic.nodes.gui.GuiNodeNexus;
import skadic.nodes.items.ItemRegister;
import skadic.nodes.networking.NodePacketHandler;
import skadic.nodes.tileentities.TileEntityNodeNexus;

import static skadic.nodes.Nodes.SNW;
import static skadic.nodes.Nodes.instance;


public class CommonProxy implements IGuiHandler{


    public void preInit(FMLPreInitializationEvent e){
        registerCapabilities();

        SNW = NetworkRegistry.INSTANCE.newSimpleChannel(Nodes.MODID);
        NodePacketHandler.registerPackets();

        EventHandlerNodes handler = new EventHandlerNodes();
        MinecraftForge.EVENT_BUS.register(handler);

        CapabilityHandler capHandler = new CapabilityHandler();
        MinecraftForge.EVENT_BUS.register(capHandler);

        new ItemRegister();
        new BlockRegister();

        registerTileEntities();
    }

    public void init(FMLInitializationEvent e){
        new CraftingRegister();
        registerRenders();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, this);
    }

    public void postInit(FMLPostInitializationEvent e){

    }

    private void registerCapabilities(){
        CapabilityManager.INSTANCE.register(ICapabilityNodeItem.class, new CapabilityNodeItemStorage(), CapabilityNodeItemDefault.class);
    }


    public void registerRenders(){

    }

    //will return the corresponding container
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        switch(ID){

            default:
                return null;
        }
    }

    //will return the corresponding gui
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        switch(ID){
            case GuiID.NODE_NEXUS:
                return new GuiNodeNexus((TileEntityNodeNexus)world.getTileEntity(pos));
            default:
                return null;
        }
    }

    public void registerTileEntities(){
        registerTileEntity(TileEntityNodeNexus.class);
    }

    private void registerTileEntity(Class<? extends TileEntity> class1){
        GameRegistry.registerTileEntity(class1, Nodes.MODID + ":" + class1.getName());
    }





}
