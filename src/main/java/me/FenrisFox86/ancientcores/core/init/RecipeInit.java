package me.FenrisFox86.ancientcores.core.init;

import me.FenrisFox86.ancientcores.common.recipes.*;
import me.FenrisFox86.ancientcores.common.recipes.CrushingRecipe;
import me.FenrisFox86.ancientcores.common.recipes.RecipeType;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.HashMap;
import java.util.Map;

public class RecipeInit {

    public static final IRecipeType<CrushingRecipe> CRUSHING_RECIPE = new RecipeType<CrushingRecipe>("crushing");

    public static final Map<IRecipeSerializer<?>, RecipeType<?>> RECIPE_MAP = new HashMap<IRecipeSerializer<?>, RecipeType<?>>() {};

    public static void registerRecipes(RegistryEvent.Register<IRecipeSerializer<?>> event) {

        RECIPE_MAP.put(CrushingRecipe.SERIALIZER, (RecipeType<?>) CRUSHING_RECIPE);

        registerRecipe(event, CRUSHING_RECIPE, CrushingRecipe.SERIALIZER);
    }

    private static void registerRecipe(
            RegistryEvent.Register<IRecipeSerializer<?>> event, IRecipeType<?> type, IRecipeSerializer<?> serializer)  {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
        event.getRegistry().register(serializer);
    }

    public static Map<ResourceLocation, IRecipe<?>> getRecipes(IRecipeType<?> type, RecipeManager manager) {
        final Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes = ObfuscationReflectionHelper
                .getPrivateValue(RecipeManager.class, manager, "field_199522_d");
        return recipes.get(type);
    }
}
