package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.common.enchantments.logic.MagmaWalkerLogic;
import me.FenrisFox86.ancientcores.common.items.TooltipUtil;
import me.FenrisFox86.ancientcores.core.init.BlockInit;
import me.FenrisFox86.ancientcores.core.init.ItemInit;
import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class MagmaCoreBoots extends ArmorItem implements ICoreItem {

    private final CoreType coreType = CoreType.MAGMA;

    public MagmaCoreBoots() {
        super(
                ModArmorMaterial.MAGMA_CORE_ARMOR,
                EquipmentSlotType.FEET,
                ItemInit.defaultProperties());
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        TooltipUtil.appendCoreItemHoverText(tooltip, "magma_core", "magma_core_boots");
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        MagmaWalkerLogic.replaceField(
                BlockInit.MAGMA_FLOOR.get().defaultBlockState(),
                entityIn.blockPosition().below(),
                worldIn,
                3,
                1);
        coreType.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}

