package me.FenrisFox86.ancientcores.common.items.core;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface ICoreType {
    String getName();

    boolean isFoil();

    boolean isFireResistant();

    IArmorMaterial getArmorMaterial();

    IItemTier getItemTier();

    ActionResult<ItemStack> use(
            net.minecraft.world.World worldIn,
            net.minecraft.entity.player.PlayerEntity playerIn,
            net.minecraft.util.Hand handIn);

    boolean onLeftClickEntity(
            ItemStack stack,
            PlayerEntity player,
            net.minecraft.entity.Entity entity);

    void inventoryTick(
            ItemStack stack,
            net.minecraft.world.World worldIn,
            net.minecraft.entity.Entity entityIn,
            int itemSlot,
            boolean isSelected);
}
