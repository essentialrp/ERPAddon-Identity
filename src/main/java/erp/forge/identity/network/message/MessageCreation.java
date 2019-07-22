package erp.forge.identity.network.message;

import erp.forge.core.ERPCoreForge;
import erp.forge.core.player.ERPlayer;
import erp.forge.core.player.EnumProfile;
import erp.forge.identity.ERPIdentityForge;
import erp.forge.identity.ModConfig;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageCreation implements IMessage, IMessageHandler<MessageCreation, IMessage> {

    public String firstname;
    public String lastname;
    public boolean isMale;
    public int age;

    public MessageCreation(){

    }

    public MessageCreation(String firstname, String lastname, boolean isMale, String age){
        if(firstname.length() <= 12){
            if(lastname.length() <= 12){
                if(age.length() <= 2 && this.isNumeric(age)){
                    this.firstname = firstname;
                    this.lastname = lastname;
                    this.isMale = isMale;
                    this.age = Integer.parseInt(age);
                } else {
                    ERPIdentityForge.logger.error("ERROR AGE");
                }
            } else {
                ERPIdentityForge.logger.error("ERROR LASTNAME");
            }
        } else {
            ERPIdentityForge.logger.error("ERROR FIRSTNAME");
        }
    }

    public void fromBytes(ByteBuf buf) {
        this.firstname = ByteBufUtils.readUTF8String(buf);
        this.lastname = ByteBufUtils.readUTF8String(buf);
        this.isMale = buf.readBoolean();
        this.age = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.firstname);
        ByteBufUtils.writeUTF8String(buf, this.lastname);
        buf.writeBoolean(this.isMale);
        buf.writeInt(this.age);
    }

    @Override
    public IMessage onMessage(MessageCreation message, MessageContext ctx) {
        if (ctx != null) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            MinecraftServer server = player.getServer();
            server.addScheduledTask(() -> {
                ERPlayer erPlayer = new ERPlayer(player.getUniqueID());
                erPlayer.loadAccount();
                erPlayer.setFirstname(message.firstname);
                erPlayer.setLastname(message.lastname);
                erPlayer.setIsMale(message.isMale);
                erPlayer.setAge(message.age);
                ERPIdentityForge.instance.getPacketChannel().sendTo(new MessageCloseGui(), player);
                player.sendMessage(new TextComponentString(ModConfig.INSTANCE.welcomeMessage.replace("%firstname%", message.firstname).replace("%lastname%", message.lastname)));
            });
        }
        return null;
    }


    /**
     * Just return true if string is numeric.
     * Just return false if string is not numeric.
     */
    public static boolean isNumeric(String strNum) {
        try {
            double integer = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
