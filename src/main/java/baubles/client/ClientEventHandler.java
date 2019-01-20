package baubles.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import baubles.api.BaubleType;
import baubles.api.cap.BaublesCapabilities;

public class ClientEventHandler {
    @SubscribeEvent
    public void registerItemModels(ModelRegistryEvent event) {
        //ModelLoader.setCustomModelResourceLocation(ItemRing.RING, 0, new ModelResourceLocation("baubles:ring", "inventory"));
    }

    @SubscribeEvent
    public void playerTick(PlayerTickEvent event) {
        if (event.side == LogicalSide.CLIENT && event.phase == Phase.START) {
            if (ClientProxy.KEY_BAUBLES.isPressed() && Minecraft.getInstance().isGameFocused()) {
                //PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory());
            }
        }
    }

    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        event.getItemStack().getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE).ifPresent(bauble -> {
            BaubleType bt = bauble.getBaubleType(event.getItemStack());
            event.getToolTip().add(new TextComponentTranslation(TextFormatting.GOLD + "name." + bt));
        });
    }
}
