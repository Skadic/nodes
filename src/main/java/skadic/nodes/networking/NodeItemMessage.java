package skadic.nodes.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by eti22 on 09.10.2016.
 */
public class NodeItemMessage implements IMessage{

    public boolean hasNode, isExporting;
    public BlockPos pos;

    public NodeItemMessage(){}

    public NodeItemMessage(BlockPos pos, boolean hasNode, boolean isExporting){
        this.pos = pos;
        this.hasNode = hasNode;
        this.isExporting = isExporting;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        this.pos = new BlockPos(x, y, z);

        hasNode = buf.readBoolean();
        isExporting = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());

        buf.writeBoolean(hasNode);
        buf.writeBoolean(isExporting);
    }
}
