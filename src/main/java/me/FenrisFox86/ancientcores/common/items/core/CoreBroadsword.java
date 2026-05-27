package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.items.BroadswordItem;
import me.FenrisFox86.ancientcores.core.util.TooltipUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class CoreBroadsword extends BroadswordItem implements ICoreItem {

    public final ICoreType core;

    public CoreBroadsword(ICoreType core, int attackDamageIn, float attackSpeedIn) {
        super(
                core.getItemTier(),
                attackDamageIn,
                attackSpeedIn,
                new Properties().tab(AncientCores.MOD_TAB));
        this.core = core;
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return core.isFoil();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(
            @Nonnull ItemStack stack,
            World worldIn,
            @Nonnull List<ITextComponent> tooltip,
            @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        TooltipUtil.appendCoreToolHoverText(tooltip, core.getName());
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(
            @Nonnull World worldIn,
            @Nonnull PlayerEntity player,
            @Nonnull Hand handIn) {
        ActionResult<ItemStack> superResult = super.use(worldIn, player, handIn);
        if (superResult.getResult() == ActionResultType.PASS) {
            return core.use(worldIn, player, handIn);
        }
        return superResult;
    }

    @Override
    public boolean onLeftClickEntity(
            ItemStack stack,
            PlayerEntity player,
            Entity entity) {
        return this.core.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void inventoryTick(
            @Nonnull ItemStack stack,
            @Nonnull World worldIn,
            @Nonnull Entity entityIn,
            int itemSlot,
            boolean isSelected) {
        this.core.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public boolean isFireResistant() {
        return core.isFireResistant();
    }

    @Override
    public void releaseUsing(
            @Nonnull ItemStack stack,
            @Nonnull World world,
            @Nonnull LivingEntity living,
            int timeLeft) {
        super.releaseUsing(stack, world, living, timeLeft);
        if (timeLeft > 0) core.use(world, living, Hand.MAIN_HAND);
    }

    @Override
    public ICoreType getCoreType() {
        return core;
    }
}

