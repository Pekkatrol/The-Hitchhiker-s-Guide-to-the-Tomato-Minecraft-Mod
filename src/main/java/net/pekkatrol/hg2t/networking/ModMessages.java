package net.pekkatrol.hg2t.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.networking.packet.EnergySyncC2SPacket;
import net.pekkatrol.hg2t.networking.packet.EnergySyncS2CPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        INSTANCE = ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "messages"))
                .networkProtocolVersion(1)
                .simpleChannel()
                .messageBuilder(EnergySyncC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(EnergySyncC2SPacket::new)
                .encoder(EnergySyncC2SPacket::toBytes)
                .consumerMainThread(EnergySyncC2SPacket::handle)
                .add()
                .messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergySyncS2CPacket::new)
                .encoder(EnergySyncS2CPacket::toBytes)
                .consumerMainThread(EnergySyncS2CPacket::handle)
                .add()
                .build();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
    }

    public static <MSG> void sentToClients(MSG message) {
        INSTANCE.send(message, PacketDistributor.ALL.noArg());
    }
}
