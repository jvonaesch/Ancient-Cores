package me.FenrisFox86.ancientcores.common.events;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.core.util.tools.ToolLootModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = AncientCores.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LootModifierHandlers {

    @SubscribeEvent
    public static void registerModifierSerializers(
            @Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new ToolLootModifier.Serializer().setRegistryName(
                new ResourceLocation(AncientCores.MOD_ID,"tool_modifier")));
    }
}