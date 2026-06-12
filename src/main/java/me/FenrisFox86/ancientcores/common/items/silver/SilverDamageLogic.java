package me.FenrisFox86.ancientcores.common.items.silver;

import me.FenrisFox86.ancientcores.AncientCores;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = AncientCores.MOD_ID)
public class SilverDamageLogic {

    private static final ITag.INamedTag<Item> SILVER_WEAPONS = ItemTags.bind(AncientCores.MOD_ID + ":silver_weapons");
    private static final float SILVER_BONUS_DAMAGE = 2.5F;

    @SubscribeEvent
    public static void onSilverDamage(LivingAttackEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (!(sourceEntity instanceof LivingEntity)) return;

        LivingEntity attacker = (LivingEntity) sourceEntity;
        LivingEntity target = event.getEntityLiving();

        if (attacker.level.isClientSide) return;
        if (attacker == target) return;
        if (!attacker.getMainHandItem().getItem().is(SILVER_WEAPONS)) return;

        LogManager.getLogger().debug("silver weapon hit: applying bonus damage to " + target.getName().getString());
        // LivingAttackEvent does not expose mutable base damage, so apply bonus as a second hit.
        target.hurt(DamageSource.MAGIC, SILVER_BONUS_DAMAGE);
    }
}
