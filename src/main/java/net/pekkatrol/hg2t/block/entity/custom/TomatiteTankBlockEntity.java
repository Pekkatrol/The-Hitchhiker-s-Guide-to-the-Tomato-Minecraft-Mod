package net.pekkatrol.hg2t.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import net.pekkatrol.hg2t.block.entity.renderer.ModBlockEntities;
import net.pekkatrol.hg2t.item.ModItems;
import net.pekkatrol.hg2t.networking.ModMessages;
import net.pekkatrol.hg2t.networking.packet.EnergySyncS2CPacket;
import net.pekkatrol.hg2t.recipe.CombustionGeneratorRecipe;
import net.pekkatrol.hg2t.recipe.CombustionGeneratorRecipeInput;
import net.pekkatrol.hg2t.recipe.ModRecipes;
import net.pekkatrol.hg2t.screen.custom.CombustionGeneratorMenu;
import net.pekkatrol.hg2t.screen.custom.TomatiteTankMenu;
import net.pekkatrol.hg2t.util.ModEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TomatiteTankBlockEntity extends BlockEntity implements MenuProvider {

    private static final int CAPACITY = 360000;
    private static final int MAX_TRANSFER = 250;

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(CAPACITY, MAX_TRANSFER) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            if (level != null && !level.isClientSide()) {
                ModMessages.sentToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
            }
        }
    };

    private final IEnergyStorage inputOnlyStorage = new IEnergyStorage() {
        @Override public int receiveEnergy(int maxReceive, boolean simulate) { return ENERGY_STORAGE.receiveEnergy(maxReceive, simulate); }
        @Override public int extractEnergy(int maxExtract, boolean simulate) { return 0; }
        @Override public int getEnergyStored() { return ENERGY_STORAGE.getEnergyStored(); }
        @Override public int getMaxEnergyStored() { return ENERGY_STORAGE.getMaxEnergyStored(); }
        @Override public boolean canExtract() { return false; }
        @Override public boolean canReceive() { return true; }
    };

    private final IEnergyStorage outputOnlyStorage = new IEnergyStorage() {
        @Override public int receiveEnergy(int maxReceive, boolean simulate) { return 0; }
        @Override public int extractEnergy(int maxExtract, boolean simulate) { return ENERGY_STORAGE.extractEnergy(maxExtract, simulate); }
        @Override public int getEnergyStored() { return ENERGY_STORAGE.getEnergyStored(); }
        @Override public int getMaxEnergyStored() { return ENERGY_STORAGE.getMaxEnergyStored(); }
        @Override public boolean canExtract() { return true; }
        @Override public boolean canReceive() { return false; }
    };

    private LazyOptional<IEnergyStorage> lazyInputHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyOutputHandler = LazyOptional.empty();

    protected final ContainerData data;

    public TomatiteTankBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TOMATITE_TANK_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return 0;
            }
            @Override
            public void set(int i, int pValue) {}
            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (!level.isClientSide()) {
            ModMessages.sentToClients(new EnergySyncS2CPacket(this.ENERGY_STORAGE.getEnergyStored(), blockPos));
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyInputHandler = LazyOptional.of(() -> inputOnlyStorage);
        lazyOutputHandler = LazyOptional.of(() -> outputOnlyStorage);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyInputHandler.invalidate();
        lazyOutputHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.putInt("tomatite_tank.energy", ENERGY_STORAGE.getEnergyStored());
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        ENERGY_STORAGE.setEnergyDirect(pTag.getInt("tomatite_tank.energy"));
    }

    @Nullable
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.hgtotomato.tomatite_tank");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new TomatiteTankMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergyDirect(energy);
    }

    private Direction getRelativeDirection(Direction absoluteDir) {
        if (absoluteDir == Direction.UP || absoluteDir == Direction.DOWN) return absoluteDir;

        Direction facing = this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        return switch (facing) {
            case NORTH -> absoluteDir;
            case SOUTH -> absoluteDir.getOpposite();
            case EAST -> absoluteDir.getCounterClockWise();
            case WEST -> absoluteDir.getClockWise();
            default -> absoluteDir;
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            Direction relative = getRelativeDirection(side == null ? Direction.NORTH : side);
            if (relative == Direction.WEST) return lazyInputHandler.cast();
            if (relative == Direction.EAST) return lazyOutputHandler.cast();
            return LazyOptional.empty();
        }
        return super.getCapability(cap, side);
    }
}