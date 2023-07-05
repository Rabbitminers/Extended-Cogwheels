package com.rabbitminers.extendedgears.datagen.forge;

import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsCuttingRecipeGen;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsDeployingRecipeGen;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsProcessingRecipeGen;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ExtendedCogwheelsCuttingRecipeGenImpl extends ExtendedCogwheelsProcessingRecipeGen {
    public ExtendedCogwheelsCuttingRecipeGenImpl(DataGenerator generator) {
        super(generator);
    }

    public static RecipeProvider create(DataGenerator gen) {
        ExtendedCogwheelsCuttingRecipeGen provider = new ExtendedCogwheelsCuttingRecipeGen(gen);
        return new RecipeProvider(gen) {
            @Override
            protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
                provider.registerRecipes(consumer);
            }
        };
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.CUTTING;
    }
}
