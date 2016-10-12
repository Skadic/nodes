package skadic.nodes.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by eti22 on 09.10.2016.
 */
public class SNodeItemMessage implements IMessage{

    public boolean hasNode, isExporting;
    public int priority;
    public BlockPos pos;

    public SNodeItemMessage(){}

    public SNodeItemMessage(BlockPos pos, boolean hasNode, boolean isExporting, int priority){
        this.pos = pos;
        this.hasNode = hasNode;
        this.isExporting = isExporting;
        this.priority = priority;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        this.pos = new BlockPos(x, y, z);

        hasNode = buf.readBoolean();
        isExporting = buf.readBoolean();
        priority = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());

        buf.writeBoolean(hasNode);
        buf.writeBoolean(isExporting);
        buf.writeInt(priority);
    }
}
