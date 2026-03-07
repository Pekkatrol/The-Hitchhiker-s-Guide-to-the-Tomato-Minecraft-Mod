package net.pekkatrol.hg2t.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import net.pekkatrol.hg2t.block.entity.custom.CombustionGeneratorBlockEntity;
import net.pekkatrol.hg2t.block.entity.custom.TomatiteTankBlockEntity;

public class EnergySyncC2SPacket {

    private final int energy;
    private final BlockPos pos;

    public EnergySyncC2SPacket(int energy, BlockPos pos) {
        this.energy = energy;
        this.pos = pos;
    }

    public EnergySyncC2SPacket(FriendlyByteBuf buf) {
        this.energy = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            BlockEntity blockEntity = player.serverLevel().getBlockEntity(pos);

            if (blockEntity instanceof CombustionGeneratorBlockEntity generator) {
                generator.setEnergyLevel(energy);
            } else if (blockEntity instanceof TomatiteTankBlockEntity tank) {
                tank.setEnergyLevel(energy);
            }
        });

        context.setPacketHandled(true);
    }
}