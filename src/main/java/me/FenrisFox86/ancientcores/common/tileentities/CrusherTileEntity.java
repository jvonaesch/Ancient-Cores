package me.FenrisFox86.ancientcores.common.tileentities;

import me.FenrisFox86.ancientcores.common.containers.CrusherContainer;
import me.FenrisFox86.ancientcores.core.init.RecipeInit;
import me.FenrisFox86.ancientcores.core.init.TileEntityTypeInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CrusherTileEntity extends AbstractConverterTileEntity<CrusherTileEntity> {
    public CrusherTileEntity() {
        super(TileEntityTypeInit.CRUSHER_TILE_ENTITY_TYPE.get(), RecipeInit.CRUSHING_RECIPE);
        this.convertAnything = true;
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.ancientcores.crusher");
    }

    protected Container createMenu(int p_213906_1_, PlayerInventory playerInv) {
        return new CrusherContainer(p_213906_1_, playerInv, this, this.dataAccess);
    }
}