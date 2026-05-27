package me.FenrisFox86.ancientcores.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import javax.annotation.Nonnull;

public class DarkContract extends Enchantment {

    public DarkContract(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean checkCompatibility(@Nonnull Enchantment other) {
        if (other instanceof Vampiric || other instanceof VampiricRepair) return false;
        return super.checkCompatibility(other);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
}
