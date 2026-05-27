package me.FenrisFox86.ancientcores.common.enchantments;

import me.FenrisFox86.ancientcores.common.items.core.CoreType;
import me.FenrisFox86.ancientcores.common.items.core.ICoreItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class MagmaWalker extends Enchantment {

    public MagmaWalker(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    protected boolean checkCompatibility(@Nonnull Enchantment other) {
        return super.checkCompatibility(other);
    }

    @Override
    public boolean canEnchant(@Nonnull ItemStack stack) {
        if (stack.getItem() instanceof ICoreItem && ((ICoreItem) stack.getItem()).getCoreType() == CoreType.MAGMA) {
            return false;
        }
        return super.canEnchant(stack);
    }
}
