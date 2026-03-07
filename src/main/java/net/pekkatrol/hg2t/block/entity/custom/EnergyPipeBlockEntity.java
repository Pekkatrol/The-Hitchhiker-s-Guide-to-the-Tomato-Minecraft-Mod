package net.pekkatrol.hg2t.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.pekkatrol.hg2t.block.custom.EnergyPipeBlock;
import net.pekkatrol.hg2t.block.entity.renderer.ModBlockEntities;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnergyPipeBlockEntity extends BlockEntity {

    private static final int TRANSFER_RATE = 1000;

    private final IEnergyStorage pipeStorage = new IEnergyStorage() {
        @Override public int receiveEnergy(int maxReceive, boolean simulate) { return 0; }
        @Override public int extractEnergy(int maxExtract, boolean simulate) { return 0; }
        @Override public int getEnergyStored() { return 0; }
        @Override public int getMaxEnergyStored() { return 0; }
        @Override public boolean canExtract() { return false; }
        @Override public boolean canReceive() { return false; }
    };

    private final LazyOptional<IEnergyStorage> lazyPipeStorage = LazyOptional.of(() -> pipeStorage);

    public EnergyPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_PIPE_BE.get(), pos, state);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) return lazyPipeStorage.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyPipeStorage.invalidate();
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;

        if (!isNetworkLeader(level, pos)) return;

        Set<BlockPos> network = new HashSet<>();
        collectNetwork(level, pos, network);

        List<IEnergyStorage> sources = new ArrayList<>();
        List<IEnergyStorage> destinations = new ArrayList<>();

        for (BlockPos pipePos : network) {
            BlockState pipeState = level.getBlockState(pipePos);
            for (Direction dir : Direction.values()) {
                if (!pipeState.getValue(getPropertyForDirection(dir))) continue;

                BlockEntity neighbor = level.getBlockEntity(pipePos.relative(dir));
                if (neighbor == null) continue;
                if (neighbor instanceof EnergyPipeBlockEntity) continue;

                IEnergyStorage storage = neighbor.getCapability(
                        ForgeCapabilities.ENERGY, dir.getOpposite()).orElse(null);
                if (storage == null) continue;

                if (storage.canExtract() && storage.getEnergyStored() > 0) {
                    sources.add(storage);
                } else if (storage.canReceive()) {
                    destinations.add(storage);
                }
            }
        }

        if (sources.isEmpty() || destinations.isEmpty()) return;

        int totalAvailable = 0;
        for (IEnergyStorage source : sources) {
            totalAvailable += Math.min(source.getEnergyStored(), TRANSFER_RATE);
        }

        if (totalAvailable <= 0) return;

        int perDestination = totalAvailable / destinations.size();
        if (perDestination <= 0) perDestination = 1;

        for (IEnergyStorage dest : destinations) {
            int toSend = Math.min(perDestination, totalAvailable);
            int accepted = dest.receiveEnergy(toSend, true);
            if (accepted <= 0) continue;

            int remaining = accepted;
            for (IEnergyStorage source : sources) {
                if (remaining <= 0) break;
                int extracted = source.extractEnergy(remaining, false);
                remaining -= extracted;
            }

            dest.receiveEnergy(accepted - remaining, false);
            totalAvailable -= (accepted - remaining);
        }
    }

    private boolean isNetworkLeader(Level level, BlockPos pos) {
        Set<BlockPos> network = new HashSet<>();
        collectNetwork(level, pos, network);
        BlockPos smallest = pos;
        for (BlockPos p : network) {
            if (p.compareTo(smallest) < 0) {
                smallest = p;
            }
        }
        return smallest.equals(pos);
    }

    private void collectNetwork(Level level, BlockPos pos, Set<BlockPos> visited) {
        if (visited.contains(pos)) return;
        visited.add(pos);

        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof EnergyPipeBlock)) return;

        for (Direction dir : Direction.values()) {
            if (!state.getValue(getPropertyForDirection(dir))) continue;

            BlockPos neighbor = pos.relative(dir);
            BlockEntity be = level.getBlockEntity(neighbor);
            if (be instanceof EnergyPipeBlockEntity) {
                collectNetwork(level, neighbor, visited);
            }
        }
    }

    private BooleanProperty getPropertyForDirection(Direction dir) {
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