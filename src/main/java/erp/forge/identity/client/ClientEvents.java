package erp.forge.identity.client;

import erp.forge.core.player.ERPlayerClient;
import erp.forge.identity.client.gui.GuiCreation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.voxelindustry.brokkgui.wrapper.impl.BrokkGuiManager;

public class ClientEvents {
    public static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTickEvent(final TickEvent.ClientTickEvent event) {

        if (event.phase.equals(TickEvent.Phase.END) && mc.world != null) {
            ERPlayerClient client = new ERPlayerClient(mc.player.getUniqueID());
            if(!client.accountExist()){
                GuiScreen creation = BrokkGuiManager.getBrokkGuiScreen(new GuiCreation());
                if(mc.currentScreen != null) {
                    if (mc.currentScreen.getClass() != creation.getClass()) {
                        Minecraft.getMinecraft().displayGuiScreen(BrokkGuiManager.getBrokkGuiScreen(new GuiCreation()));
                    }
                } else {
                    Minecraft.getMinecraft().displayGuiScreen(BrokkGuiManager.getBrokkGuiScreen(new GuiCreation()));
                }
            }
        }
    }
}
