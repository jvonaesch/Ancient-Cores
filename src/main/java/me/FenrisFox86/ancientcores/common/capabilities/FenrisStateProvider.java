package me.FenrisFox86.ancientcores.common.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class FenrisStateProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IModState.class)
    public static Capability<IModState> CAPABILITY = null;

    IModState instance = new DefaultModState();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == CAPABILITY ? LazyOptional.of(() -> (T)instance) : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT)CAPABILITY.getStorage().writeNBT(CAPABILITY, instance, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        CAPABILITY.getStorage().readNBT(CAPABILITY, instance, null, nbt);
    }
}