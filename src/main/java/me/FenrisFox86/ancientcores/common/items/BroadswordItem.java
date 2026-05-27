package me.FenrisFox86.ancientcores.common.items;

import me.FenrisFox86.ancientcores.common.capabilities.ModPlayerReader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BroadswordItem extends SwordItem {

    private final int attackDamage;

    public BroadswordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
        this.attackDamage = attackDamageIn;
    }

    @Override
    @Nonnull
    public UseAction getUseAnimation(@Nonnull ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(@Nonnull ItemStack stack) {
        return 72000;
    }

    @Override
    @Nonnull
    public ActionResultType interactLivingEntity(
            @Nonnull ItemStack stack,
            @Nonnull PlayerEntity playerIn,
            @Nonnull LivingEntity target,
            @Nonnull Hand hand) {
        return ActionResultType.FAIL;
    }

    @Nonnull
    public ActionResult<ItemStack> use(
            @Nonnull World worldIn,
            @Nonnull PlayerEntity player,
            @Nonnull Hand handIn) {

        ItemStack stack = player.getItemInHand(handIn);
        if (!player.getItemInHand(Hand.OFF_HAND).isEmpty()) return ActionResult.fail(stack);
        if (!player.getCooldowns().isOnCooldown(this)) {
            player.startUsingItem(handIn);
            return ActionResult.fail(stack);
        }
        return ActionResult.fail(stack);
    }

    public void releaseUsing(
            @Nonnull ItemStack stack,
            @Nonnull World world,
            @Nonnull LivingEntity living,
            int timeLeft) {
        if (!living.getItemInHand(Hand.OFF_HAND).isEmpty()) return;
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)living;

            float max_charge = 40.0f;
            float total_charge = (getUseDuration(stack) - timeLeft);
            float charge = Math.min(total_charge, max_charge)/max_charge;

            double factor = charge*10;
            Vector3d vector = player.getViewVector(1f).normalize().multiply(factor, 1, factor);
            player.setDeltaMovement(vector);
            player.getCooldowns().addCooldown(stack.getItem(), 50);
            ModPlayerReader.setModFloat(player, "dashing", ((float)(int)charge)*20 + 0.5f);
            stack.hurtAndBreak(8, player, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
        }
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }
}
