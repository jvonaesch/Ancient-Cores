package me.FenrisFox86.ancientcores.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import javax.annotation.Nonnull;

public class Crushing extends Enchantment {

    public Crushing(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    protected boolean checkCompatibility(@Nonnull Enchantment other) {
        if (other instanceof Lumberjack) return false;
        return super.checkCompatibility(other);
    }
}
