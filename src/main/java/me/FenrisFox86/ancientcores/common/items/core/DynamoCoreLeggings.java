package me.FenrisFox86.ancientcores.common.items.core;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.items.TooltipUtil;
import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;

public class DynamoCoreLeggings extends ArmorItem {

    public static final ICoreType coreType = CoreType.DYNAMO;

    public static final Properties properties = new Properties()
            .tab(AncientCores.MOD_TAB);
    public static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(
            AncientCores.MOD_ID + ":dynamo_core_legs:speed_boost",
            1.5,
            AttributeModifier.Operation.MULTIPLY_BASE);

    public DynamoCoreLeggings() {
        super(
                ModArmorMaterial.DYNAMO_CORE_ARMOR,
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
    public void appendHoverText(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        TooltipUtil.appendCoreItemHoverText(tooltip, coreType.getName(), "dynamo_core_leggings");
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        coreType.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public @Nonnull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(
            @Nonnull EquipmentSlotType slotType) {
        if (slotType != super.getSlot()) {return super.getDefaultAttributeModifiers(slotType);}
        Multimap<Attribute, AttributeModifier> modifiers =
                ArrayListMultimap.create(super.getDefaultAttributeModifiers(slotType));
        modifiers.put(Attributes.MOVEMENT_SPEED, DynamoCoreLeggings.SPEED_MODIFIER);
        return modifiers;
    }
}
