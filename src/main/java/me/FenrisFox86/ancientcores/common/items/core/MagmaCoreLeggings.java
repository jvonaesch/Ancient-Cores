package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.items.TooltipUtil;
import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class MagmaCoreLeggings extends ArmorItem implements ICoreItem {

    private final CoreType coreType = CoreType.MAGMA;

    public static final Properties properties = new Properties()
            .tab(AncientCores.MOD_TAB);

    public static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new AttributeModifier(
            AncientCores.MOD_ID + ":magma_core_leggings:knockback_resistance_boost",
            1.0,
            AttributeModifier.Operation.ADDITION);

    public MagmaCoreLeggings() {
        super(
                ModArmorMaterial.MAGMA_CORE_ARMOR,
                EquipmentSlotType.LEGS,
                properties);
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(
            @Nonnull ItemStack stack, World worldIn,
            @Nonnull List<ITextComponent> tooltip,
            @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        TooltipUtil.appendCoreItemHoverText(tooltip, "magma_core", "magma_core_leggings");
    }
}

