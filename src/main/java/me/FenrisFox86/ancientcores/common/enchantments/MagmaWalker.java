package me.FenrisFox86.ancientcores.common.enchantments;

import me.FenrisFox86.ancientcores.common.items.core.CoreType;
import me.FenrisFox86.ancientcores.common.items.core.ICoreItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class MagmaWalker extends Enchantment {

    public MagmaWalker(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    protected boolean checkCompatibility(@Nonnull Enchantment other) {
        if (other instanceof FrostWalkerEnchantment) return false;
        return super.checkCompatibility(other);
    }
}
