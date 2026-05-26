package me.FenrisFox86.ancientcores.core.init;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.containers.CrusherContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(
            ForgeRegistries.CONTAINERS, AncientCores.MOD_ID
    );

    public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONTAINER_TYPE = CONTAINER_TYPES
            .register("crusher", () -> IForgeContainerType.create(CrusherContainer::new));

    public static void ContainerTypeInit() {
        CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
