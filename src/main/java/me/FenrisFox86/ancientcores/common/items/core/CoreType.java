package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import me.FenrisFox86.ancientcores.core.util.tools.ModItemTier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public enum CoreType implements ICoreType {
    MAGMA("magma_core", ModItemTier.MAGMA_CORE, ModArmorMaterial.MAGMA_CORE_ARMOR) {
            @Override
            @Nonnull
            public ActionResult<ItemStack> useCoreItem(
                    World worldIn,
                    PlayerEntity playerIn,
                    Hand handIn,
                    ICoreItem coreItem) {
                return ActionResult.pass(playerIn.getItemInHand(handIn));
            }
    },
    DYNAMO("dynamo_core", ModItemTier.DYNAMO_CORE, ModArmorMaterial.DYNAMO_CORE_ARMOR) {
            @Override
            @Nonnull
            public ActionResult<ItemStack> useCoreItem(
                    World worldIn,
                    PlayerEntity playerIn,
                    Hand handIn,
                    ICoreItem coreItem) {
                return ActionResult.pass(playerIn.getItemInHand(handIn));
            }

            @Override
            public boolean onLeftClickEntity(
                    ItemStack stack,
                    PlayerEntity player,
                    Entity entity) {
                if(entity.isAlive()) {
                    ((LivingEntity) entity).addEffect(new EffectInstance(Effects.GLOWING, 100));
                    ((LivingEntity) entity).removeEffect(Effects.MOVEMENT_SPEED);
                    ((LivingEntity) entity).removeEffect(Effects.JUMP);
                    ((LivingEntity) entity).removeEffect(Effects.DIG_SPEED);
                }
                return false;
            }

            @Override
            public void inventoryTick(
                    @Nonnull ItemStack stack,
                    @Nonnull World worldIn,
                    @Nonnull Entity entityIn,
                    int itemSlot,
                    boolean isSelected) {
                if (worldIn.getDayTime() % 20 > 0) return;
                //if (!stack.getItem().canBeDepleted()) return;
                if (!entityIn.isSprinting()) return;
                if (!(entityIn instanceof LivingEntity)) return;
                stack.hurtAndBreak(-8, (LivingEntity) entityIn, p ->
                        p.broadcastBreakEvent(Objects.requireNonNull(stack.getEquipmentSlot())));
            }
    },;

    private final String core_name;
    private final IItemTier itemTier;
    private final IArmorMaterial armorMaterial;
    private final boolean boolIsFoil;
    private final boolean boolResistsFire;

    CoreType(
            String core_name,
            IItemTier itemTier,
            IArmorMaterial armorMaterial,
            boolean boolIsFoil,
            boolean boolResistsFire) {
        this.core_name = core_name;
        this.itemTier = itemTier;
        this.armorMaterial = armorMaterial;
        this.boolIsFoil = boolIsFoil;
        this.boolResistsFire = boolResistsFire;
    }

    CoreType(
            String core_name,
            IItemTier itemTier,
            IArmorMaterial armorMaterial) {
        this(core_name, itemTier, armorMaterial, true, true);
    }

    @Override
    public String getName() {
        return core_name;
    }

    @Override
    public boolean isFoil() {
        return boolIsFoil;
    }

    @Override
    public boolean isFireResistant() {
        return boolResistsFire;
    }

    @Override
    public IArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    @Override
    public IItemTier getItemTier() {
        return itemTier;
    }

    @Override
    public ActionResult<ItemStack> useCoreItem(
            World worldIn,
            PlayerEntity playerIn,
            Hand handIn,
            ICoreItem coreItem) {
        return ActionResult.pass(playerIn.getItemInHand(handIn));
    }

    @Override
    public boolean onLeftClickEntity(
            ItemStack stack,
            PlayerEntity player,
            net.minecraft.entity.Entity entity) {
        return false;
    }

    public void inventoryTick(
            ItemStack stack,
            World worldIn,
            net.minecraft.entity.Entity entityIn,
            int itemSlot,
            boolean isSelected) {
    }
}
