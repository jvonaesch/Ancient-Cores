package me.FenrisFox86.ancientcores.common.items.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface ICoreType {
    String getName();

    boolean isFoil();

    boolean isFireResistant();

    IArmorMaterial getArmorMaterial();

    IItemTier getItemTier();

    ActionResult<ItemStack> use(
            World worldIn,
            LivingEntity player,
            Hand handIn);

    boolean onLeftClickEntity(
            ItemStack stack,
            LivingEntity player,
            Entity entity);

    void inventoryTick(
            ItemStack stack,
            World worldIn,
            Entity entityIn,
            int itemSlot,
            boolean isSelected);
}
