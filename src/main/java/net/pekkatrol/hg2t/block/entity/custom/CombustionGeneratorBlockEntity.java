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
import net.pekkatrol.hg2t.networking.packet.EnergySyncC2SPacket;
import net.pekkatrol.hg2t.networking.packet.EnergySyncS2CPacket;
import net.pekkatrol.hg2t.recipe.*;
import net.pekkatrol.hg2t.screen.custom.CombustionGeneratorMenu;
import net.pekkatrol.hg2t.screen.custom.CombustionGeneratorScreen;
import net.pekkatrol.hg2t.screen.custom.PresentMenu;
import net.pekkatrol.hg2t.util.ModEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CombustionGeneratorBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int CAPACITY = 60000;
    private static final int ENERGY_PRODUCED = 30;

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(CAPACITY, 250) {
        @Override
        public void onEnergyChanged() {
            setChanged();
        }
    };

    // Wrapper output-only exposé aux blocs externes
    private final IEnergyStorage outputOnlyStorage = new IEnergyStorage() {
        @Override public int receiveEnergy(int maxReceive, boolean simulate) { return 0; }
        @Override public int extractEnergy(int maxExtract, boolean simulate) { return ENERGY_STORAGE.extractEnergy(maxExtract, simulate); }
        @Override public int getEnergyStored() { return ENERGY_STORAGE.getEnergyStored(); }
        @Override public int getMaxEnergyStored() { return ENERGY_STORAGE.getMaxEnergyStored(); }
        @Override public boolean canExtract() { return true; }
        @Override public boolean canReceive() { return false; }
    };

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.of(() -> outputOnlyStorage);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private int energyThisCraft = 0;

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasRecipe()) {
            if (hasCombustibleInFirstSlot()) {
                this.ENERGY_STORAGE.receiveEnergy(ENERGY_PRODUCED, false);
                this.energyThisCraft += ENERGY_PRODUCED;
            }
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);

            if (hasCraftingFinished()) {
                craftItem();
                this.energyThisCraft = 0;
                resetProgress();
            }
        } else {
            if (this.energyThisCraft > 0) {
                int current = this.ENERGY_STORAGE.getEnergyStored();
                this.ENERGY_STORAGE.setEnergyDirect(Math.max(0, current - this.energyThisCraft));
                this.energyThisCraft = 0;
                setChanged();
            }
            resetProgress();
        }

        if (!level.isClientSide()) {
            ModMessages.sentToClients(new EnergySyncS2CPacket(this.ENERGY_STORAGE.getEnergyStored(), getBlockPos()));
        }
    }

    public CombustionGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.COMBUSTION_GENERATOR_BE.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> CombustionGeneratorBlockEntity.this.progress;
                    case 1 -> CombustionGeneratorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int pValue) {
                switch (i) {
                    case 0 -> CombustionGeneratorBlockEntity.this.progress = pValue;
                    case 1 -> CombustionGeneratorBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> outputOnlyStorage);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("combustion_generator.progress", progress);
        pTag.putInt("combustion_generator.maxProgress", maxProgress);
        pTag.putInt("combustion_generator.energy", ENERGY_STORAGE.getEnergyStored());
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("combustion_generator.progress");
        maxProgress = pTag.getInt("combustion_generator.maxProgress");
        ENERGY_STORAGE.setEnergyDirect(pTag.getInt("combustion_generator.energy"));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.hgtotomato.present");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CombustionGeneratorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    private boolean hasCombustibleInFirstSlot() {
        return itemHandler.getStackInSlot(0).getItem() == ModItems.COMPACT_LOG.get();
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }

    private void craftItem() {
        Optional<RecipeHolder<CombustionGeneratorRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CombustionGeneratorRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        ItemStack output = recipe.get().value().output();
        return canInsertIntoOutputSlot(output) && canInsertItemAmountIntoOutputSlot(output.getCount());
    }

    private Optional<RecipeHolder<CombustionGeneratorRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.COMBUSTION_GENERATOR_TYPE.get(),
                        new CombustionGeneratorRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canInsertIntoOutputSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertItemAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 :
                itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();
        return maxCount >= currentCount + count;
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

    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergyDirect(energy);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == Direction.UP) {
                return LazyOptional.of(() -> new RangedWrapper(itemHandler, INPUT_SLOT, INPUT_SLOT + 1)).cast();
            } else {
                return LazyOptional.of(() -> new RangedWrapper(itemHandler, OUTPUT_SLOT, OUTPUT_SLOT + 1)).cast();
            }
        }

        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }
}