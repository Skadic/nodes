package skadic.nodes.networking;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.ICapabilityNodeItem;

/**
 * Created by eti22 on 09.10.2016.
 */
public class SNodeItemMessageHandler implements IMessageHandler<SNodeItemMessage, IMessage> {


    @Override
    public IMessage onMessage(final SNodeItemMessage message, MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
        final World world = ctx.getServerHandler().playerEntity.worldObj;
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {

                BlockPos pos = message.pos;
                TileEntity te = world.getTileEntity(pos);

                if(te.hasCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null)){
                    ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
                    itemCap.setHasNode(message.hasNode);
                    itemCap.setExport(message.isExporting);
                    itemCap.setPriority(message.priority);
                }
            }
        });
        return null;
    }



    private EntityPlayer getPlayer(MessageContext ctx){
        return ctx.getServerHandler().playerEntity;
    }




}
