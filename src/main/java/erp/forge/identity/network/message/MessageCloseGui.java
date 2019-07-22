package erp.forge.identity.network.message;

import erp.forge.identity.client.gui.GuiCreation;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.voxelindustry.brokkgui.wrapper.impl.BrokkGuiManager;

public class MessageCloseGui implements IMessage {

    public MessageCloseGui(){
    }

    public void fromBytes(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }


    public static class Handler implements IMessageHandler<MessageCloseGui, IMessage> {
        public IMessage onMessage(final MessageCloseGui message, final MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                Minecraft.getMinecraft().displayGuiScreen(null);
                return;
            });
            return null;
        }
    }


}
