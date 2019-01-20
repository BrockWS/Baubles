package baubles.common.network;

import java.util.Objects;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import baubles.common.Baubles;

public class PacketHandler {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Baubles.MODID.toLowerCase(), "network"),
            () -> "1",
            s -> Objects.equals(s, "1"),
            s -> Objects.equals(s, "1"));

    public static void init() {
        INSTANCE.registerMessage(
                0,
                PacketOpenBaublesInventory.class,
                PacketOpenBaublesInventory::toBytes,
                PacketOpenBaublesInventory::fromBytes,
                PacketOpenBaublesInventory::onMessage);
        INSTANCE.registerMessage(
                1,
                PacketOpenNormalInventory.class,
                PacketOpenNormalInventory::toBytes,
                PacketOpenNormalInventory::fromBytes,
                PacketOpenNormalInventory::onMessage);
        INSTANCE.registerMessage(
                2,
                PacketSync.class,
                PacketSync::toBytes,
                PacketSync::fromBytes,
                PacketSync::onMessage);
    }
}
