package me.FenrisFox86.ancientcores.core.util.tools;

import me.FenrisFox86.ancientcores.core.init.ItemInit;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {
    RUBY_ARMOR("ruby", 33, new int[] {3, 6, 8, 3},
            ModItemTier.RUBY.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            2.0f,0.0f,
            () -> Ingredient.of(ItemInit.RUBY.get())),
    SAPPHIRE_ARMOR("sapphire", 33, new int[] {3, 6, 8, 3},
            ModItemTier.SAPPHIRE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            3.0f,0.1f,
            () -> Ingredient.of(ItemInit.SAPPHIRE.get())),
    ESSENCE_ARMOR("essence", 40, new int[] {4, 7, 9, 4},
            ModItemTier.ESSENCE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_GOLD,
            5.0f,0.2f,
            () -> Ingredient.of(ItemInit.ESSENCE.get())),

    // slightly more enchantable than iron, lower durability, slightly more knockback-resistance
    BRONZE_ARMOR("bronze", 10, new int[] {2, 5, 6, 2},
            ModItemTier.BRONZE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0f,1.0f,
            () -> Ingredient.of(ItemInit.BRONZE_INGOT.get())),

    // More enchantable than iron, lower durability, low toughness value
    SILVER_ARMOR("silver", 11, new int[] {2, 5, 6, 2},
            ModItemTier.SILVER.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON,
            0.5f,0.0f, () ->
            Ingredient.of(ItemInit.SILVER_INGOT.get())),

    RUBY_CORE_ARMOR("ruby_core", 27, new int[] {2, 5, 7, 3},
            ModItemTier.RUBY_CORE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON,
            1.0f,0.0f,
            () -> Ingredient.of(ItemInit.RUBY.get())),
    DYNAMO_CORE_ARMOR("dynamo_core", 45, new int[] {4, 7, 9, 4},
            ModItemTier.DYNAMO_CORE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON,
            4.0f,0.4f,
            () -> Ingredient.of(ItemInit.DYNAMO_CORE.get())),
    MAGMA_CORE_ARMOR("magma_core", 45, new int[] {4, 7, 9, 4},
            ModItemTier.MAGMA_CORE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON,
            4.0f,0.4f,
            () -> Ingredient.of(ItemInit.MAGMA_CORE.get()));

    private final int[] baseDurability = {13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] armorVal;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durabilityMultiplier, int[] armorVal, int enchantability,
                     SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armorVal = armorVal;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return this.baseDurability[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.armorVal[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    @Nonnull
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    @Nonnull
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
