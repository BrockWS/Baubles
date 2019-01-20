package baubles.common.network;


import java.util.function.Supplier;

import net.minecraft.entity.player.EntityPlayerMP;

import net.minecraftforge.fml.network.NetworkEvent;

import baubles.common.Baubles;
import io.netty.buffer.ByteBuf;

public class PacketOpenNormalInventory {

    public PacketOpenNormalInventory() {

    }

    public void toBytes(ByteBuf buffer) {
    }

    public static PacketOpenNormalInventory fromBytes(ByteBuf buffer) {
        return new PacketOpenNormalInventory();
    }

    public static void onMessage(PacketOpenNormalInventory message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            EntityPlayerMP player = ctx.get().getSender();
            if (player == null) {
                Baubles.log.error("Failed to open Normal inventory! Player is null!");
                return;
            }
            //player.openContainer.onContainerClosed(player);
            //player.displayGui(new );
        });
    }
}

