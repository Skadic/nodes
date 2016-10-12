package skadic.nodes.networking;

import net.minecraftforge.fml.relauncher.Side;
import skadic.nodes.Nodes;

public class NodePacketHandler {


    private static int id = 0;

    public static void registerPackets(){
        registerPacket(SNodeItemMessageHandler.class, SNodeItemMessage.class, Side.SERVER);
    }

    private static void registerPacket(Class handlerClass, Class messageClass, Side side){
        Nodes.SNW.registerMessage(handlerClass, messageClass, id++, side);
    }

}
