package me.FenrisFox86.ancientcores.core.init;

import me.FenrisFox86.ancientcores.AncientCores;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class SoundEventInit {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS,
            AncientCores.MOD_ID);

    public static void SoundEventInit() {
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<SoundEvent>
            CRUSHER_CRUSH = register("blocks/crusher_crush");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(AncientCores.MOD_ID, name)));
    }
}
