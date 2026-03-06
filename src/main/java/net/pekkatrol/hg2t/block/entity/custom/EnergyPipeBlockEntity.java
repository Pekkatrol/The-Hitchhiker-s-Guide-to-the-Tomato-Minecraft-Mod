package net.pekkatrol.hg2t.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import net.pekkatrol.hg2t.block.custom.EnergyPipeBlock;
import net.pekkatrol.hg2t.block.entity.renderer.ModBlockEntities;

public class EnergyPipeBlockEntity extends BlockEntity {

    private static final int TRANSFER_RATE = 250;

    public EnergyPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_PIPE_BE.get(), pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;

        IEnergyStorage source = null;
        Direction sourceDir = null;

        for (Direction dir : Direction.values()) {
            if (!state.getValue(getPropertyForDirection(dir))) continue;

            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor == null) continue;

            IEnergyStorage storage = neighbor.getCapability(ForgeCapabilities.ENERGY, dir.getOpposite()).orElse(null);
            if (storage != null && storage.canExtract() && storage.getEnergyStored() > 0) {
                source = storage;
                sourceDir = dir;
                break;
            }
        }

        if (source == null) return;

        for (Direction dir : Direction.values()) {
            if (dir == sourceDir) continue;
            if (!state.getValue(getPropertyForDirection(dir))) continue;

            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor == null) continue;

            IEnergyStorage dest = neighbor.getCapability(ForgeCapabilities.ENERGY, dir.getOpposite()).orElse(null);
            if (dest != null && dest.canReceive()) {
                int simulated = dest.receiveEnergy(TRANSFER_RATE, true);
                if (simulated > 0) {
                    int extracted = source.extractEnergy(simulated, false);
                    dest.receiveEnergy(extracted, false);
                    break;
                }
            }
        }
    }

    private net.minecraft.world.level.block.state.properties.BooleanProperty getPropertyForDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> EnergyPipeBlock.NORTH;
            case SOUTH -> EnergyPipeBlock.SOUTH;
            case EAST -> EnergyPipeBlock.EAST;
            case WEST -> EnergyPipeBlock.WEST;
            case UP -> EnergyPipeBlock.UP;
            case DOWN -> EnergyPipeBlock.DOWN;
        };
    }
}
