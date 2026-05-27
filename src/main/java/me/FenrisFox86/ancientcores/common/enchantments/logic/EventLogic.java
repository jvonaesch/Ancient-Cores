package me.FenrisFox86.ancientcores.common.enchantments.logic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static me.FenrisFox86.ancientcores.core.init.BlockInit.MAGMA_FLOOR;
import static me.FenrisFox86.ancientcores.core.init.EnchantmentInit.*;
import static net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel;

@Mod.EventBusSubscriber
public class EventLogic {

    //Effects
    @SubscribeEvent
    public static void OnUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity living = event.getEntityLiving();
        World world = event.getEntity().level;
        Iterable<ItemStack> equipment = living.getAllSlots();

        //Magma Walker
        for(ItemStack stack: equipment) {
            if (getItemEnchantmentLevel(MAGMA_WALKER.get(), stack) > 0)
                MagmaWalkerLogic.replaceField(
                        MAGMA_FLOOR.get().defaultBlockState(),
                        living.blockPosition().below(),
                        world, 2, 1);
        }

        //Dynamo
        if(world.getGameTime() % 20 == 0) {
            if(living.isSprinting()) {
                for(ItemStack stack: equipment) {
                    int level = getItemEnchantmentLevel(DYNAMO_REPAIR.get(), stack);
                    if(level > 0) stack.hurtAndBreak(
                            (int) -Math.pow(2, level-1),
                            living, p -> p.broadcastBreakEvent(
                                    Objects.requireNonNull(stack.getEquipmentSlot())));
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnHurtEntity(LivingHurtEvent event) {
        Entity entity = event.getSource().getEntity();
        if(entity instanceof LivingEntity) {

            LivingEntity living = (LivingEntity) entity;
            LivingEntity target = event.getEntityLiving();

            Hand hand = Hand.MAIN_HAND;
            ItemStack stack = living.getItemInHand(hand);

            //Spectral
            int level = getItemEnchantmentLevel(SPECTRAL.get(), stack);
            if(level > 0) {
                target.addEffect(new EffectInstance(Effects.GLOWING, level * 100));
            }

            //Vampiric Repair
            Iterable<ItemStack> equipment = living.getAllSlots();
            for (ItemStack equippedStack: equipment) {
                level = getItemEnchantmentLevel(VAMPIRIC_REPAIR.get(), equippedStack);
                if(level > 0) {
                    stack.hurtAndBreak((int) (-(event.getAmount()/3)*level), living, p ->
                            p.broadcastBreakEvent(Objects.requireNonNull(equippedStack.getEquipmentSlot())));
                }
            }

            //Levitation
            level = getItemEnchantmentLevel(LEVITATION.get(), stack);
            if(level > 0) {
                target.addEffect(new EffectInstance(Effects.LEVITATION, level * 100));
            }

            //Dark Contract
            level = getItemEnchantmentLevel(DARK_CONTRACT.get(), stack);
            if(level > 0) {
                target.hurt(DamageSource.GENERIC, level * 2.0f);
                living.hurt(DamageSource.DRY_OUT, level / 2.0f);
            }

            //Vampiric
            level = getItemEnchantmentLevel(VAMPIRIC.get(), stack);
            float regen = (event.getAmount()/5.0f)*level;
            if(level > 0) {
                if (living instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) living;
                    int saturation = player.getFoodData().getFoodLevel();
                    int hunger = Math.max(20 - saturation, 0);
                    float hunger_regen = Math.min(regen, hunger);
                    player.getFoodData().setFoodLevel((int) (player.getFoodData().getFoodLevel()+hunger_regen));
                    player.heal(Math.max(regen-hunger_regen, 0));
                } else {
                    living.heal(regen);
                }
            }
        }
    }
}
