package baubles.common.network;

import java.util.function.Supplier;

import net.minecraft.entity.player.EntityPlayerMP;

import net.minecraftforge.fml.network.NetworkEvent;

import baubles.common.Baubles;
import io.netty.buffer.ByteBuf;

public class PacketOpenBaublesInventory {

    public PacketOpenBaublesInventory() {

    }

    public void toBytes(ByteBuf buf) {

    }

    public static PacketOpenBaublesInventory fromBytes(ByteBuf buf) {
        return new PacketOpenBaublesInventory();
    }

    public static void onMessage(PacketOpenBaublesInventory message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            EntityPlayerMP player = ctx.get().getSender();
            if (player == null) {
                Baubles.log.error("Failed to open Baubles inventory! Player is null!");
                return;
            }
            //player.openContainer.onContainerClosed(player);
        });
    }
}
