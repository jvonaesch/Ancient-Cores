package me.FenrisFox86.ancientcores.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class Vampiric extends Enchantment {

    public Vampiric(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) {
        if (ench instanceof DarkContract) {
            return false;
        }
        return super.checkCompatibility(ench);
    }
}
