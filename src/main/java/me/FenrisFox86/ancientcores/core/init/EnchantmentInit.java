package me.FenrisFox86.ancientcores.core.init;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.enchantments.*;
import me.FenrisFox86.ancientcores.common.items.core.CoreType;
import me.FenrisFox86.ancientcores.common.items.core.ICoreItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber
public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(
            ForgeRegistries.ENCHANTMENTS,
            AncientCores.MOD_ID);

    public static void init() {
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final EnchantmentType AXE_TYPE = EnchantmentType.create("axe", item -> item instanceof AxeItem);
    public static final EnchantmentType ARMOR_FEET_NON_MAGMA = EnchantmentType.create(
            "boots_non_magma", item -> (
                    (item instanceof ArmorItem) && ((ArmorItem)item).getSlot() == EquipmentSlotType.FEET
                    && !(item instanceof ICoreItem && ((ICoreItem)item).getCoreType() == CoreType.MAGMA)));
    public static final EnchantmentType BREAKABLE_NON_DYNAMO = EnchantmentType.create(
            "breakable_non_dynamo", item -> (
                    item.canBeDepleted()
                    && !(item instanceof ICoreItem && ((ICoreItem)item).getCoreType() == CoreType.DYNAMO)));

    //Enchantments
    public static final RegistryObject<Enchantment> DYNAMO_REPAIR = ENCHANTMENTS.register("dynamo_repair", ()
            -> new DynamoRepair(
            Enchantment.Rarity.VERY_RARE,
            BREAKABLE_NON_DYNAMO,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND,
                    EquipmentSlotType.FEET,
                    EquipmentSlotType.LEGS,
                    EquipmentSlotType.CHEST,
                    EquipmentSlotType.HEAD,
                    EquipmentSlotType.OFFHAND
            }));

    public static final RegistryObject<Enchantment> VAMPIRIC_REPAIR = ENCHANTMENTS.register("vampiric_repair", ()
            -> new VampiricRepair(
            Enchantment.Rarity.VERY_RARE,
            EnchantmentType.BREAKABLE,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND,
                    EquipmentSlotType.FEET,
                    EquipmentSlotType.LEGS,
                    EquipmentSlotType.CHEST,
                    EquipmentSlotType.HEAD,
                    EquipmentSlotType.OFFHAND
            }));

    public static final RegistryObject<Enchantment> MAGMA_WALKER = ENCHANTMENTS.register("magma_walker", ()
            -> new MagmaWalker(
            Enchantment.Rarity.VERY_RARE,
            ARMOR_FEET_NON_MAGMA,
            new EquipmentSlotType[]{
                    EquipmentSlotType.FEET
            }));

    public static final RegistryObject<Enchantment> SPECTRAL = ENCHANTMENTS.register("spectral", ()
            -> new Spectral(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentType.WEAPON,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND
            }));

    public static final RegistryObject<Enchantment> SMELTING = ENCHANTMENTS.register("smelting", ()
            -> new Smelting(
            Enchantment.Rarity.RARE,
            EnchantmentType.DIGGER,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND
            }));

    public static final RegistryObject<Enchantment> CRUSHING = ENCHANTMENTS.register("crushing", ()
            -> new Crushing(
            Enchantment.Rarity.RARE,
            EnchantmentType.DIGGER,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND
            }));

    public static final RegistryObject<Enchantment> LUMBERJACK = ENCHANTMENTS.register("lumberjack", ()
            -> new Lumberjack(
            Enchantment.Rarity.UNCOMMON,
            AXE_TYPE,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND
            }));

    public static final RegistryObject<Enchantment> LEVITATION = ENCHANTMENTS.register("levitation", ()
            -> new Levitation(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentType.WEAPON,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND
            }));

    public static final RegistryObject<Enchantment> VAMPIRIC = ENCHANTMENTS.register("vampiric", ()
            -> new Vampiric(
            Enchantment.Rarity.RARE,
            EnchantmentType.WEAPON,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND
            }));

    public static final RegistryObject<Enchantment> DARK_CONTRACT = ENCHANTMENTS.register("dark_contract", ()
            -> new DarkContract(
            Enchantment.Rarity.RARE,
            EnchantmentType.WEAPON,
            new EquipmentSlotType[]{
                    EquipmentSlotType.MAINHAND
            }));
}
