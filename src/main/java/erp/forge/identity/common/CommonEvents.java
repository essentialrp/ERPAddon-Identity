package erp.forge.identity.common;

import erp.forge.core.player.ERPlayer;
import erp.forge.identity.ERPIdentityForge;
import erp.forge.identity.network.message.MessageSendGuiCreation;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonEvents {

    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void onPlayerLogIn(final PlayerEvent.PlayerLoggedInEvent event) {
        final EntityPlayerMP player = (EntityPlayerMP)event.player;
        ERPlayer erPlayer = new ERPlayer(player.getUniqueID());
        if(!erPlayer.accountExist()){
            ERPIdentityForge.instance.getPacketChannel().sendTo(new MessageSendGuiCreation(), player);
        }
    }


}
