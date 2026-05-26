package me.FenrisFox86.ancientcores.client.util;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.client.screens.CrusherScreen;
import me.FenrisFox86.ancientcores.core.init.ContainerTypeInit;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AncientCores.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(ContainerTypeInit.CRUSHER_CONTAINER_TYPE.get(), CrusherScreen::new);
    }
}
