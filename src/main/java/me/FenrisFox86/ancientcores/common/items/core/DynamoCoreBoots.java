package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.core.util.TooltipUtil;
import me.FenrisFox86.ancientcores.core.init.ItemInit;
import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = AncientCores.MOD_ID)
public class DynamoCoreBoots extends ArmorItem implements ICoreItem {

    public static final ICoreType core = CoreType.DYNAMO;

    public static final AttributeModifier JUMP_MODIFIER = new AttributeModifier(
            AncientCores.MOD_ID + ":dynamo_core_boots:jump_boost",
            2.0,
            AttributeModifier.Operation.MULTIPLY_BASE);

    public DynamoCoreBoots() {
        super(
                ModArmorMaterial.DYNAMO_CORE_ARMOR,
                EquipmentSlotType.FEET,
                ItemInit.defaultProperties());
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
        TooltipUtil.appendCoreItemHoverText(tooltip, core.getName(), "dynamo_core_boots");
    }

    @Override
    public void inventoryTick(
            @Nonnull ItemStack stack,
            @Nonnull World worldIn,
            @Nonnull Entity entityIn,
            int itemSlot,
            boolean isSelected) {
        core.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @SubscribeEvent
    public static void OnLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity living = event.getEntityLiving();
        Item boots = living.getItemBySlot(EquipmentSlotType.FEET).getItem();

        if (boots instanceof DynamoCoreBoots) {
            Vector3d vector = living.getDeltaMovement().multiply(1, 2, 1);
            living.setDeltaMovement(vector);
        }
    }

    @SubscribeEvent
    public static void OnLivingFall(LivingFallEvent event) {
        LivingEntity living = event.getEntityLiving();
        Item boots = living.getItemBySlot(EquipmentSlotType.FEET).getItem();

        if (boots instanceof DynamoCoreBoots) {
            event.setCanceled(true);
        }
    }

    @Override
    public ICoreType getCoreType() {
        return core;
    }
}
