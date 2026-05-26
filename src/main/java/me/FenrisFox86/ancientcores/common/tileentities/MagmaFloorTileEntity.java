package me.FenrisFox86.ancientcores.common.tileentities;

import me.FenrisFox86.ancientcores.core.init.TileEntityTypeInit;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.*;

import javax.annotation.Nullable;

public class MagmaFloorTileEntity extends TileEntity implements ITickableTileEntity {

    public int decayStage;
    public int defaultDecayStage;
    public boolean isSource = true;

    public MagmaFloorTileEntity() {
        super(TileEntityTypeInit.MAGMA_FLOOR_TILE_ENTITY.get());
        this.defaultDecayStage = 100;
        this.decayStage = 100;
    }

    public MagmaFloorTileEntity(int defaultDecayStage) {
        super(TileEntityTypeInit.MAGMA_FLOOR_TILE_ENTITY.get());
        this.defaultDecayStage = defaultDecayStage;
        this.decayStage = defaultDecayStage;
    }

    public void resetDecay() {
        this.decayStage = defaultDecayStage;
    }

    public void tick() {
        decayStage--;
        if (decayStage < 1) {
            assert this.level != null;
            this.level.setBlock(this.worldPosition, this.isSource ? Blocks.LAVA.defaultBlockState() : Blocks.AIR.defaultBlockState(), 0);
        }
    }
}

