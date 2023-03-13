package com.rabbitminers.extendedgears.datagen.fabric;

import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsCuttingRecipeGen;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsProcessingRecipeGen;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.utility.recipe.IRecipeTypeInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ExtendedCogwheelsCuttingRecipeGenImpl extends ExtendedCogwheelsProcessingRecipeGen {
    public ExtendedCogwheelsCuttingRecipeGenImpl(DataGenerator generator) {
        super(generator);
    }

    public static RecipeProvider create(DataGenerator gen) {
        ExtendedCogwheelsCuttingRecipeGen provider = new ExtendedCogwheelsCuttingRecipeGen(gen);
        return new FabricRecipeProvider((FabricDataGenerator) gen) {
            @Override
            protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
                provider.registerRecipes(exporter);
            }
        };
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.CUTTING;
    }
}

