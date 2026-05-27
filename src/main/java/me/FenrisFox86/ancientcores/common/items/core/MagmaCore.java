package me.FenrisFox86.ancientcores.common.items.core;

import me.FenrisFox86.ancientcores.AncientCores;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;

import static me.FenrisFox86.ancientcores.common.items.TooltipUtil.appendCoreToolHoverText;

@Mod.EventBusSubscriber
public class MagmaCore extends Item implements ICoreItem {

    public static final ICoreType core = CoreType.MAGMA;

    public MagmaCore() {
        super(new Properties().tab(AncientCores.MOD_TAB));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(
            @Nonnull ItemStack stack,
            World worldIn,
            @Nonnull List<ITextComponent> tooltip,
            @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        appendCoreToolHoverText(tooltip, core.getName());
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(
            @Nonnull World worldIn,
            @Nonnull PlayerEntity playerIn,
            @Nonnull Hand handIn) {
        return core.use(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        return core.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @SubscribeEvent
    public static void onLivingBurn(LivingDamageEvent event) {
        LivingEntity living = event.getEntityLiving();
        for (ItemStack stack: living.getAllSlots()) {
            Item item = stack.getItem();
            //LoggerFactory.getLogger(MagmaCore.class).debug("living burn with item: " + item);
            if (!(item instanceof ICoreItem)) continue;
            if (!((ICoreItem)item).getCoreType().equals(CoreType.MAGMA)) continue;
            event.setAmount(0);
            event.setCanceled(true);
        } 
    }

    @Override
    public ICoreType getCoreType() {
        return core;
    }
}
