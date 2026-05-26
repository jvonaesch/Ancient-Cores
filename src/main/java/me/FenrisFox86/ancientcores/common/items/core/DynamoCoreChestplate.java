package me.FenrisFox86.ancientcores.common.items.core;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class DynamoCoreChestplate extends ArmorItem {

    public static final Properties properties = new Properties()
            .tab(AncientCores.MOD_TAB);

    public static final AttributeModifier KNOCKBACK_MODIFIER = new AttributeModifier(
            AncientCores.MOD_ID + ":dynamo_core_chestplate:knockback_boost",
            5.0,
            AttributeModifier.Operation.ADDITION);

    public static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new AttributeModifier(
            AncientCores.MOD_ID + ":dynamo_core_chestplate:knockback_resistance_boost",
            2.0,
            AttributeModifier.Operation.ADDITION);

    public DynamoCoreChestplate() {
        super(
                ModArmorMaterial.DYNAMO_CORE_ARMOR,
                EquipmentSlotType.CHEST,
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
        tooltip.add(new TranslationTextComponent("tooltip.ancientcores.dynamo_core_chestplate"));
        tooltip.add(new TranslationTextComponent("tooltip.ancientcores.dynamo_core_passive"));
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        DynamoCore.dynamoItemTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public @Nonnull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(
            @Nonnull EquipmentSlotType slotType) {
        if (slotType != super.getSlot()) {return super.getDefaultAttributeModifiers(slotType);}
        Multimap<Attribute, AttributeModifier> modifiers =
                ArrayListMultimap.create(super.getDefaultAttributeModifiers(slotType));
        modifiers.put(Attributes.ATTACK_KNOCKBACK, DynamoCoreChestplate.KNOCKBACK_MODIFIER);
        modifiers.put(Attributes.KNOCKBACK_RESISTANCE, DynamoCoreChestplate.KNOCKBACK_RESISTANCE_MODIFIER);
        return modifiers;
    }
}
