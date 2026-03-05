package net.pekkatrol.hg2t.util;

import net.minecraftforge.energy.EnergyStorage;

public abstract class ModEnergyStorage extends EnergyStorage {


    public ModEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            onEnergyChanged();
        }
        return energyExtracted;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = super.receiveEnergy(maxReceive, simulate);

        if (energyReceived != 0) {
            onEnergyChanged();
        }
        return energyReceived;
    }

    public int setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
        return energy;
    }

    public void setEnergyDirect(int energy) {
        this.energy = energy;
    }

    public abstract void onEnergyChanged();
}
