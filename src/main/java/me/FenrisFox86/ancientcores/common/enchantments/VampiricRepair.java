package me.FenrisFox86.ancientcores.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import javax.annotation.Nonnull;

public class VampiricRepair extends Enchantment {

    public VampiricRepair(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean checkCompatibility(@Nonnull Enchantment other) {
        if (other instanceof DynamoRepair) {
            return false;
        }
        return super.checkCompatibility(other);
    }
}
