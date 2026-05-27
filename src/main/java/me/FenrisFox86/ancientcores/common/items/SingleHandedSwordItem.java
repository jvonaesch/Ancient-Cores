package me.FenrisFox86.ancientcores.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SingleHandedSwordItem extends TieredItem implements IVanishable {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public SingleHandedSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builderIn) {
        super(tier, builderIn);
        this.attackDamage = (float)attackDamageIn + tier.getAttackDamageBonus();
        Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    @Nonnull
    public UseAction getUseAnimation(
            @Nonnull ItemStack stack) {
        return UseAction.NONE;
    }

    //Same as swordItem
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    //Same as swordItem
    public boolean canPlayerBreakBlockWhileHolding(
            BlockState state,
            World worldIn,
            BlockPos pos,
            PlayerEntity player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(
            @Nonnull ItemStack stack,
            @Nonnull BlockState state) {
        if (state.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return 0.5F;
        }
    }

    @Override
    public boolean hurtEnemy(
            @Nonnull ItemStack stack,
            @Nonnull LivingEntity target,
            @Nonnull LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, livingEntity -> {livingEntity.broadcastBreakEvent(Hand.MAIN_HAND);});
        return true;
    }

    public void hurtEnemy(
            @Nonnull ItemStack stack,
            @Nonnull LivingEntity target,
            @Nonnull LivingEntity attacker,
            @Nonnull Hand hand) {
        if (hand == Hand.OFF_HAND && attacker instanceof PlayerEntity) return;
        stack.hurtAndBreak(1, attacker, (entity) -> {
            attacker.broadcastBreakEvent(hand);
        });
    }

    public boolean canHarvestBlock(BlockState blockIn) {
        return blockIn.is(Blocks.COBWEB);
    }

    @Override
    @Nonnull
    public ActionResultType interactLivingEntity(
            @Nonnull ItemStack stack,
            @Nonnull PlayerEntity playerIn,
            @Nonnull LivingEntity target,
            @Nonnull Hand hand) {
        ItemStack mainStack = playerIn.getItemInHand(Hand.MAIN_HAND);
        if ((!mainStack.isStackable()) && !(mainStack.getItem() instanceof SingleHandedSwordItem))
            return ActionResultType.FAIL;

        if (hand == Hand.OFF_HAND) {
            playerIn.getItemInHand(hand).hurtAndBreak(1, playerIn, (entity) -> {
                playerIn.broadcastBreakEvent(hand);
            });
            stack.hurtEnemy(target, playerIn);
            target.hurt(DamageSource.playerAttack(playerIn), this.getAttackDamageBonus());
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(
            @Nonnull World worldIn,
            @Nonnull PlayerEntity playerIn,
            @Nonnull Hand handIn) {
        if (handIn == Hand.OFF_HAND) {
            return ActionResult.success(playerIn.getOffhandItem());
        }
        return ActionResult.fail(playerIn.getMainHandItem());
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(
            EquipmentSlotType equipmentSlot,
            ItemStack stack) {
        return equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot, stack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (stack == player.getMainHandItem()) {
            return false;
        }
        return false;
    }
}
