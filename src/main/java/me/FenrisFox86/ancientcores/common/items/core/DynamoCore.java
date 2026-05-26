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

@Mod.EventBusSubscriber
public class DynamoCore extends CoreItem {

    public DynamoCore() {
        super("dynamo_core", new Properties().tab(AncientCores.MOD_TAB).durability(256));
        this.itemTier = ModItemTier.DYNAMO_CORE;
        this.armorMaterial = ModArmorMaterial.DYNAMO_CORE_ARMOR;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        CoreItem.appendHoverText(tooltip, name);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return this.useCoreItem(worldIn, playerIn, handIn, this);
    }

    public ActionResult<ItemStack> useCoreItem(World worldIn, PlayerEntity playerIn, Hand handIn, Item item) {
        if(!playerIn.getCooldowns().isOnCooldown(item)) {
            playerIn.addEffect(new EffectInstance(Effects.JUMP, 500, 1));
            playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 500, 4));
            playerIn.addEffect(new EffectInstance(Effects.DIG_SPEED, 500, 4));
            playerIn.getCooldowns().addCooldown(item,60);
            playerIn.getItemInHand(handIn).hurtAndBreak(16, playerIn, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
            return ActionResult.success(playerIn.getItemInHand(handIn));
        }
        return ActionResult.fail(playerIn.getItemInHand(handIn));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if(entity.isAlive()) {
            ((LivingEntity) entity).addEffect(new EffectInstance(Effects.GLOWING, 100));
            ((LivingEntity) entity).removeEffect(Effects.MOVEMENT_SPEED);
            ((LivingEntity) entity).removeEffect(Effects.JUMP);
            ((LivingEntity) entity).removeEffect(Effects.DIG_SPEED);
            //entity.setSecondsOnFire(3);
        }
        return false;
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(worldIn.getDayTime()%20 == 0) {
            if(stack.getItem() instanceof ICoreItem && entityIn instanceof PlayerEntity && ((ICoreItem)stack.getItem()).getCore() instanceof DynamoCore) {
                if(entityIn.isSprinting()) {
                    LivingEntity living = (LivingEntity) entityIn;
                    stack.hurtAndBreak(-8, living, p ->
                            p.broadcastBreakEvent(Objects.requireNonNull(stack.getEquipmentSlot())));
                }
            }
        }
    }

    public static void dynamoItemTick (
            @Nonnull ItemStack stack,
            @Nonnull World worldIn,
            @Nonnull Entity entityIn,
            int itemSlot,
            boolean isSelected)
    {
        if (worldIn.getDayTime() % 20 > 0) return;
        if (!entityIn.isSprinting()) return;
        if (!(entityIn instanceof LivingEntity)) return;
        stack.hurtAndBreak(-8, (LivingEntity) entityIn, p ->
                p.broadcastBreakEvent(Objects.requireNonNull(stack.getEquipmentSlot())));
    }


    @SubscribeEvent
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
    }
}
