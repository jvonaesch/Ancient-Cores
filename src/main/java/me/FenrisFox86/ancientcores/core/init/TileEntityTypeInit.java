package me.FenrisFox86.ancientcores.core.init;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.tileentities.CrusherTileEntity;
import me.FenrisFox86.ancientcores.common.tileentities.MagmaFloorTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.TILE_ENTITIES, AncientCores.MOD_ID
    );

    public static final RegistryObject<TileEntityType<CrusherTileEntity>> CRUSHER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES
            .register("crusher", () -> TileEntityType.Builder.of(CrusherTileEntity::new, BlockInit.CRUSHER_BLOCK.get()).build(
                    null));

    public static final RegistryObject<TileEntityType<MagmaFloorTileEntity>> MAGMA_FLOOR_TILE_ENTITY = TILE_ENTITY_TYPES
            .register("magma_floor", () -> TileEntityType.Builder.of(MagmaFloorTileEntity::new, BlockInit.MAGMA_FLOOR.get()).build(
                    null));

    public static void TileEntityTypeInit() {
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
