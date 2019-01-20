package baubles.api.cap;

import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

import baubles.api.IBauble;

public class BaublesCapabilities {
    /**
     * Access to the baubles capability.
     */
    @CapabilityInject(IBaublesItemHandler.class)
    public static final Capability<IBaublesItemHandler> CAPABILITY_BAUBLES = null;

    @CapabilityInject(IBauble.class)
    public static final Capability<IBauble> CAPABILITY_ITEM_BAUBLE = null;

    public static class CapabilityBaubles<T extends IBaublesItemHandler> implements IStorage<T> {

        @Override
        public INBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<T> capability, T instance, EnumFacing side, INBTBase nbt) {

        }
    }

    public static class CapabilityItemBaubleStorage implements IStorage<IBauble> {

        @Override
        public INBTBase writeNBT(Capability<IBauble> capability, IBauble instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IBauble> capability, IBauble instance, EnumFacing side, INBTBase nbt) {

        }
    }
}
