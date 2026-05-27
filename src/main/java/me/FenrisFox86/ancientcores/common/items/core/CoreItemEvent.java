package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.AncientCores;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Central event handler for core-item related global effects.
 */
@Mod.EventBusSubscriber(modid = AncientCores.MOD_ID)
public class CoreItemEvent {

    @SubscribeEvent
    public static void onLivingBurn(LivingDamageEvent event) {
        if (!event.getSource().isFire()) return;
        
        LivingEntity living = event.getEntityLiving();
        for (ItemStack stack : living.getAllSlots()) {
            if (stack == null || stack.isEmpty()) continue;
            
            Item item = stack.getItem();
            // TODO: living burn
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
}

