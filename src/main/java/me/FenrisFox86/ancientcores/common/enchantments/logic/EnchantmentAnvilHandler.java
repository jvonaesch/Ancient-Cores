package me.FenrisFox86.ancientcores.common.enchantments.logic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.enchantment.EnchantmentHelper;

import java.util.Map;

import static me.FenrisFox86.ancientcores.core.init.EnchantmentInit.MAGMA_WALKER;

@Mod.EventBusSubscriber
public class EnchantmentAnvilHandler {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        // clearOutput(event);
        ItemStack baseStack = event.getLeft();
        ItemStack appliedStack = event.getRight();
        if (baseStack == null || baseStack.isEmpty() || appliedStack == null || appliedStack.isEmpty()) return;

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(appliedStack);
        for (Enchantment enchantment : enchantments.keySet()) {
            if (!enchantment.canEnchant(baseStack)) clearOutput(event);
        }
    }

    public static void clearOutput(AnvilUpdateEvent event) {
        event.setOutput(ItemStack.EMPTY);
        event.setCost(0);
        event.setMaterialCost(0);
        event.setCanceled(true);
    }
}


