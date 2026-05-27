package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.core.util.tools.ModArmorMaterial;
import me.FenrisFox86.ancientcores.core.util.tools.ModItemTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
import java.util.Objects;

import static me.FenrisFox86.ancientcores.common.items.TooltipUtil.appendCoreToolHoverText;

@Mod.EventBusSubscriber
public class DynamoCore extends Item implements ICoreItem {

    public static final ICoreType core = CoreType.DYNAMO;

    public DynamoCore() {
        super(new Properties().tab(AncientCores.MOD_TAB).durability(256));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(
            @Nonnull ItemStack stack,
            World worldIn,
            @Nonnull List<ITextComponent> tooltip,
            @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        appendCoreToolHoverText(tooltip, core.getName());
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(
            @Nonnull World worldIn,
            @Nonnull PlayerEntity playerIn,
            @Nonnull Hand handIn) {
        return core.useCoreItem(worldIn, playerIn, handIn, this);
        // TODO: use item
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        return core.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean isFireResistant() {
        return true;
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

    /*@SubscribeEvent
    public static void OnLivingJump(LivingEvent.LivingJumpEvent event) {
        // TODO: replace with attribute modifier
        LivingEntity living = event.getEntityLiving();
        Item boots = living.getItemBySlot(EquipmentSlotType.FEET).getItem();

        if (boots instanceof CoreArmorItem && ((CoreArmorItem)boots).core instanceof DynamoCore) {
            Vector3d vector = living.getDeltaMovement().multiply(1, 2, 1);
            living.setDeltaMovement(vector);

        }
    }

    @SubscribeEvent
    public static void OnLivingFall(LivingFallEvent event) {
        // TODO: replace with attribute modifier
        LivingEntity living = event.getEntityLiving();
        Item boots = living.getItemBySlot(EquipmentSlotType.FEET).getItem();

        if (boots instanceof CoreArmorItem && ((CoreArmorItem)boots).core instanceof DynamoCore) {
            event.setCanceled(true);
        }
    }*/
}
