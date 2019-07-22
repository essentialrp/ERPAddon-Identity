package erp.forge.identity.network;


import erp.forge.identity.ERPIdentityForge;
import erp.forge.identity.network.message.MessageCloseGui;
import erp.forge.identity.network.message.MessageCreation;
import erp.forge.identity.network.message.MessageSendGuiCreation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE;
    private static int nextId = 1;

    public static void init() {
        registerMessage(MessageCreation.class, MessageCreation.class, Side.SERVER);
        registerMessage(MessageSendGuiCreation.Handler.class, MessageSendGuiCreation.class, Side.CLIENT);
        registerMessage(MessageCloseGui.Handler.class, MessageCloseGui.class, Side.CLIENT);


    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        INSTANCE.registerMessage(messageHandler, requestMessageType, nextId++, side);

    }

    static {
        INSTANCE = ERPIdentityForge.instance.getPacketChannel();
    }
}