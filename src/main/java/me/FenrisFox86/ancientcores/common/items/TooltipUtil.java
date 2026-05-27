package me.FenrisFox86.ancientcores.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.glfw.GLFW;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

/**
 * Small helper interface providing default tooltip behavior for core items.
 * Implementing classes can delegate their appendHoverText to these defaults.
 */
public class TooltipUtil {

    @OnlyIn(Dist.CLIENT)
    public static void appendCoreToolHoverText(List<ITextComponent> tooltip, String coreName) {
        if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("tooltip.ancientcores." + coreName + "_attack"));
            tooltip.add(new TranslationTextComponent("tooltip.ancientcores." + coreName + "_tool"));
            tooltip.add(new TranslationTextComponent("tooltip.ancientcores." + coreName + "_passive"));
            tooltip.add(new StringTextComponent(" "));
        } else {
            tooltip.add(Tooltips.HOLD_SHIFT);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void appendCorePassiveHoverText(List<ITextComponent> tooltip, String coreName) {
        if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("tooltip.ancientcores." + coreName + "_attack"));
            tooltip.add(new TranslationTextComponent("tooltip.ancientcores." + coreName + "_passive"));
            tooltip.add(new StringTextComponent(" "));
        } else {
            tooltip.add(Tooltips.HOLD_SHIFT);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void appendCoreItemHoverText(List<ITextComponent> tooltip, String coreName, String item_name) {
        if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("tooltip.ancientcores." + item_name));
            tooltip.add(new TranslationTextComponent("tooltip.ancientcores." + coreName + "_passive"));
            tooltip.add(new StringTextComponent(" "));
        } else {
            tooltip.add(Tooltips.HOLD_SHIFT);
        }
    }
}

