package skadic.nodes.events;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import skadic.nodes.Nodes;
import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.CapabilityNodeItemStorage;
import skadic.nodes.capabilities.ICapabilityNodeItem;

/**
 * Created by eti22 on 08.10.2016.
 */
public class CapabilityHandler {

    public static final ResourceLocation ITEM_NODE_CAPABILITY = new ResourceLocation(Nodes.MODID, ICapabilityNodeItem.CAPABILITY_NODE_ITEM_NAME);

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent.TileEntity e){
        if(e.getTileEntity() instanceof IInventory) e.addCapability(ITEM_NODE_CAPABILITY, new CapabilityNodeItemProvider(e.getTileEntity()));
    }
}
