package net.pekkatrol.hg2t.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.pekkatrol.hg2t.block.entity.custom.CombustionGeneratorBlockEntity;

public class EnergySyncS2CPacket {

    private final int energy;
    private final BlockPos pos;

    public EnergySyncS2CPacket(int energy, BlockPos pos) {
        this.energy = energy;
        this.pos = pos;
    }

    public EnergySyncS2CPacket(FriendlyByteBuf buf) {
        this.energy = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            // Côté client
            Minecraft minecraft = Minecraft.getInstance();
            BlockEntity blockEntity = minecraft.level.getBlockEntity(pos);

            if (blockEntity instanceof CombustionGeneratorBlockEntity generator) {
                generator.setEnergyLevel(energy);
            }
        });

        context.setPacketHandled(true);
    }
}