package me.FenrisFox86.ancientcores.client.screens;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.containers.CrusherContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrusherScreen extends AbstractConverterScreen<CrusherContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AncientCores.MOD_ID, "textures/gui/crusher.png");

    public CrusherScreen(CrusherContainer container, PlayerInventory playerInv, ITextComponent name) {
        super(container, playerInv, name, TEXTURE);
        this.inventoryLabelY ++;
    }

}