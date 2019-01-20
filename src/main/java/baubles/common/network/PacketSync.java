package baubles.common.network;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import net.minecraftforge.fml.network.NetworkEvent;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import baubles.common.Baubles;

public class PacketSync {

    private int playerId;
    private byte slot;
    private ItemStack bauble;

    public PacketSync(int playerId, byte slot, ItemStack bauble) {
        this.playerId = playerId;
        this.slot = slot;
        this.bauble = bauble;
    }

    public PacketSync(EntityPlayer p, int slot, ItemStack bauble) {
        this.slot = (byte) slot;
        this.bauble = bauble;
        this.playerId = p.getEntityId();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(playerId);
        buffer.writeByte(slot);
        buffer.writeItemStack(bauble);
    }

    public static PacketSync fromBytes(PacketBuffer buffer) {
        int playerId = buffer.readInt();
        byte slot = buffer.readByte();
        ItemStack bauble = buffer.readItemStack();
        return new PacketSync(playerId, slot, bauble);
    }

    public static void onMessage(PacketSync message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            World world = Baubles.PROXY.getClientWorld();
            if (world == null)
                return;
            Entity p = world.getEntityByID(message.playerId);
            if (p instanceof EntityPlayer) {
                IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer) p);
                baubles.setStackInSlot(message.slot, message.bauble);
            }
        });
	    /*
			Minecraft.getMinecraft().addScheduledTask(new Runnable(){ public void run() {
				World world = Baubles.proxy.getClientWorld();
				if (world==null) return;
				Entity p = world.getEntityByID(message.playerId);
				if (p !=null && p instanceof EntityPlayer) {
					IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer) p);
					baubles.setStackInSlot(message.slot, message.bauble);
				}
			}});
			return null;*/
    }

}
