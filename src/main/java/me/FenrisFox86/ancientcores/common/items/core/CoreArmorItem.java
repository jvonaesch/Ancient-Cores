package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.AncientCores;
import me.FenrisFox86.ancientcores.common.items.TooltipUtil;
import me.FenrisFox86.ancientcores.core.init.ItemInit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber
public class CoreArmorItem extends ArmorItem implements ICoreItem {

    public final ICoreType core;

    public CoreArmorItem(ICoreType core, EquipmentSlotType type) {
        super(core.getArmorMaterial(), type, ItemInit.defaultProperties());
        this.core = core;
    }

    @Override
    public boolean isFireResistant() {
        return core.isFireResistant();
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        this.core.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
