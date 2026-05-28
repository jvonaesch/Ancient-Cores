package me.FenrisFox86.ancientcores.core.init;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.items.*;
import me.FenrisFox86.ancientcores.common.items.core.*;
import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import me.FenrisFox86.ancientcores.core.util.tools.ModFoods;
import me.FenrisFox86.ancientcores.core.util.tools.ModItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AncientCores.MOD_ID);
    public static Map<String, Item> ITEM_MAP = new HashMap<>();

    public static Item.Properties defaultProperties() {
        return new Item.Properties()
                .tab(AncientCores.MOD_TAB);
    }

    public static RegistryObject<Item> addItem(String name) {
        Item item = new Item(defaultProperties());
        ITEM_MAP.put(name, item);
        return ITEMS.register(name, () -> item);
    }

    public static RegistryObject<Item> addItem(String name, Item item) {
        ITEM_MAP.put(name, item);
        return ITEMS.register(name, () -> item);
    }

    public static Map<String, RegistryObject<Item>> addToolSet(String name, IItemTier tier) {
        Map<String, RegistryObject<Item>> MAP = new HashMap() {};
        MAP.put("SWORD", addItem(name + "_sword", new SwordItem(
                tier, 2, -1.0F, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("AXE", addItem(name + "_axe", new AxeItem(
                tier, 3, -2.0F, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("PICKAXE", addItem(name + "_pickaxe", new PickaxeItem(
                tier, 2, -2.0F, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("SHOVEL", addItem(name + "_shovel", new ShovelItem(
                tier, 1, -2.0F, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("HOE", addItem(name + "_hoe", new HoeItem(
                tier, 0, -2.0F, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP = completeToolSet(name, tier, MAP);

        return MAP;
    }

    public static Map<String, RegistryObject<Item>> completeToolSet(String name, IItemTier tier, @Nullable Map map) {
        Map<String, RegistryObject<Item>> MAP = map;
        if (map == null) { MAP = new HashMap() {}; }
        MAP.put("HAMMER", addItem(name+ "_hammer", new HammerItem(
                tier, 4, -1.5F, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("DAGGER", addItem(name+ "_dagger", new SingleHandedSwordItem(
                tier, 0, -1.0F, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("BROADSWORD", addItem(name+ "_broadsword", new BroadswordItem(
                tier, 6, -3.0F, new Item.Properties().tab(AncientCores.MOD_TAB))));

        return map;
    }

    public static Map<String, RegistryObject<Item>> addArmorSet(String name, IArmorMaterial material) {
        Map<String, RegistryObject<Item>> MAP = new HashMap() {};
        MAP.put("HELMET", addItem(name + "_helmet", new ArmorItem(
                material, EquipmentSlotType.HEAD, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("CHESTPLATE", addItem(name + "_chestplate", new ArmorItem(
                material, EquipmentSlotType.CHEST, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("LEGGINGS", addItem(name + "_leggings", new ArmorItem(
                material, EquipmentSlotType.LEGS, new Item.Properties().tab(AncientCores.MOD_TAB))));
        MAP.put("BOOTS", addItem(name + "_boots", new ArmorItem(
                material, EquipmentSlotType.FEET, new Item.Properties().tab(AncientCores.MOD_TAB))));
        return MAP;
    }

    public static void ItemInit() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item>

    RUBY = addItem("ruby"),
    SAPPHIRE = addItem("sapphire"),
    PYRITE = addItem("pyrite"),
    LEAD = addItem("lead"),
    TIN = addItem("tin"),
    CASSITERITE = addItem("cassiterite"),
    GALENITE = addItem("galenite"),

    CORE_VESSEL = addItem("core_vessel"),
    CORE_VESSEL_HELMET = addItem("core_vessel_helmet", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_CHESTPLATE = addItem("core_vessel_chestplate", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_LEGGINGS = addItem("core_vessel_leggings", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_BOOTS = addItem("core_vessel_boots", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_SWORD = addItem("core_vessel_sword", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_PICKAXE = addItem("core_vessel_pickaxe", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_AXE = addItem("core_vessel_axe", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_SHOVEL = addItem("core_vessel_shovel", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_HOE = addItem("core_vessel_hoe", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_HAMMER = addItem("core_vessel_hammer", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_DAGGER = addItem("core_vessel_dagger", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),
    CORE_VESSEL_BROADSWORD = addItem("core_vessel_broadsword", new DescriptionItemBase("tooltip.ancientcores.core_vessel_items")),

    DYNAMO_CORE = addItem("dynamo_core", new DynamoCore()),
    DYNAMO_HELMET = addItem("dynamo_core_helmet", new DynamoCoreHelmet()),
    DYNAMO_CHESTPLATE = addItem("dynamo_core_chestplate", new DynamoCoreChestplate()),
    DYNAMO_LEGGINGS = addItem("dynamo_core_leggings", new DynamoCoreLeggings()),
    DYNAMO_BOOTS = addItem("dynamo_core_boots", new DynamoCoreBoots()),
    DYNAMO_SWORD = addItem("dynamo_core_sword", new CoreSword(CoreType.DYNAMO, 2, -1.0F)),
    DYNAMO_AXE = addItem("dynamo_core_axe", new CoreAxe(CoreType.DYNAMO, 3, -2.0F)),
    DYNAMO_PICKAXE = addItem("dynamo_core_pickaxe", new CorePickaxe(CoreType.DYNAMO, 2, -2.0F)),
    DYNAMO_SHOVEL = addItem("dynamo_core_shovel", new CoreShovel(CoreType.DYNAMO, 1, -2.0F)),
    DYNAMO_HOE = addItem("dynamo_core_hoe", new CoreHoe(CoreType.DYNAMO, 0, -2.0F)),
    DYNAMO_HAMMER = addItem("dynamo_core_hammer", new CoreHammer(CoreType.DYNAMO, 4, -1.5F)),
    DYNAMO_DAGGER = addItem("dynamo_core_dagger", new CoreDagger(CoreType.DYNAMO, 0, -1.0F)),
    DYNAMO_BROADSWORD = addItem("dynamo_core_broadsword", new CoreBroadsword(CoreType.DYNAMO, 6, -3.0F)),

    MAGMA_CORE = addItem("magma_core", new MagmaCore()),
    MAGMA_HELMET = addItem("magma_core_helmet", new MagmaCoreHelmet()),
    MAGMA_CHESTPLATE = addItem("magma_core_chestplate", new MagmaCoreChestplate()),
    MAGMA_LEGGINGS = addItem("magma_core_leggings", new MagmaCoreLeggings()),
    MAGMA_BOOTS = addItem("magma_core_boots", new MagmaCoreBoots()),
    MAGMA_SWORD = addItem("magma_core_sword", new CoreSword(CoreType.MAGMA, 2, -1.0F)),
    MAGMA_AXE = addItem("magma_core_axe", new CoreAxe(CoreType.MAGMA, 3, -2.0F)),
    MAGMA_PICKAXE = addItem("magma_core_pickaxe", new CorePickaxe(CoreType.MAGMA, 2, -2.0F)),
    MAGMA_SHOVEL = addItem("magma_core_shovel", new CoreShovel(CoreType.MAGMA, 1, -2.0F)),
    MAGMA_HOE = addItem("magma_core_hoe", new CoreHoe(CoreType.MAGMA, 0, -2.0F)),
    MAGMA_HAMMER = addItem("magma_core_hammer", new CoreHammer(CoreType.MAGMA, 4, -1.5F)),
    MAGMA_DAGGER = addItem("magma_core_dagger", new CoreDagger(CoreType.MAGMA, 0, -1.0F)),
    MAGMA_BROADSWORD = addItem("magma_core_broadsword", new CoreBroadsword(CoreType.MAGMA, 6, -3.0F)),

    BRONZE_INGOT = addItem("bronze_ingot"),
    BRONZE_NUGGET = addItem("bronze_nugget"),
    VESSEL_BRONZE_INGOT = addItem("vessel_bronze_ingot"),
    SILVER_INGOT = addItem("silver_ingot"),
    SILVER_NUGGET = addItem("silver_nugget"),
    COPPER_INGOT = addItem("copper_ingot"),

    ESSENCE = addItem("essence"),
    ESSENCE_INGOT = addItem("essence_ingot"),

    IRON_ROD = addItem("iron_rod"),
    SILVER_ROD = addItem("silver_rod"),
    BRONZE_ROD = addItem("bronze_rod"),
    GOLD_ROD = addItem("gold_rod"),

    GRAYS_CHEESE = addItem("grays_cheese", new ItemBase((new Item.Properties()).tab(AncientCores.MOD_TAB)
            .rarity(Rarity.EPIC).food(ModFoods.RAYHANS_CHEESE)).foilEffect().description("tooltip.ancientcores.grays_cheese.lore")
            .description("tooltip.ancientcores.grays_cheese.desc"));

    public static final Map<String, RegistryObject<Item>>
        RUBY_TOOLSET = addToolSet("ruby", ModItemTier.RUBY),
        SAPPHIRE_TOOLSET = addToolSet("sapphire", ModItemTier.SAPPHIRE),
        BRONZE_TOOLSET = addToolSet("bronze", ModItemTier.BRONZE),
        SILVER_TOOLSET = addToolSet("silver", ModItemTier.SILVER),
        // ESSENCE_TOOLSET = addToolSet("essence", ModItemTier.ESSENCE),

        RUBY_ARMOR_SET = addArmorSet("ruby", ModArmorMaterial.RUBY_ARMOR),
        SAPPHIRE_ARMOR_SET = addArmorSet("sapphire", ModArmorMaterial.SAPPHIRE_ARMOR),
        BRONZE_ARMOR_SET = addArmorSet("bronze", ModArmorMaterial.BRONZE_ARMOR),
        SILVER_ARMOR_SET = addArmorSet("silver", ModArmorMaterial.SILVER_ARMOR),
        // ESSENCE_ARMOR_SET = addArmorSet("essence", ModArmorMaterial.ESSENCE_ARMOR),

        WOOD_TOOLSET = completeToolSet("wood", ItemTier.WOOD, null),
        STONE_TOOLSET = completeToolSet("stone", ItemTier.STONE, null),
        IRON_TOOLSET = completeToolSet("iron", ItemTier.IRON, null),
        GOLD_TOOLSET = completeToolSet("gold", ItemTier.GOLD, null),
        DIAMOND_TOOLSET = completeToolSet("diamond", ItemTier.DIAMOND, null),
        NETHERITE_TOOLSET = completeToolSet("netherite", ItemTier.NETHERITE, null);
}
