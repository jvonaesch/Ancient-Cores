package me.FenrisFox86.ancientcores;

import me.FenrisFox86.ancientcores.common.capabilities.DefaultModState;
import me.FenrisFox86.ancientcores.common.capabilities.ModStateStorage;
import me.FenrisFox86.ancientcores.common.capabilities.IModState;
import me.FenrisFox86.ancientcores.core.init.*;
import me.FenrisFox86.ancientcores.network.AbilityPacketHandler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("ancientcores")
public class AncientCores {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "ancientcores";

    public AncientCores() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ItemInit();
        BlockInit.BlockInit();
        EnchantmentInit.init();
        TileEntityTypeInit.TileEntityTypeInit();
        ContainerTypeInit.ContainerTypeInit();
        SoundEventInit.SoundEventInit();

        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        bus.addGenericListener(
                IRecipeSerializer.class,
                RecipeInit::registerRecipes);

        MinecraftForge.EVENT_BUS.register(this);
        AbilityPacketHandler.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing mod capabilities");
        CapabilityManager.INSTANCE.register(
                IModState.class,
                new ModStateStorage(),
                DefaultModState::new);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.debug("mod rendering setup");
    }

    public static final ItemGroup MOD_TAB = new ItemGroup("basic_material_tab") {
        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.RUBY.get());
        }
    };
}
