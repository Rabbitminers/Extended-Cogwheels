package com.rabbitminers.extendedgears.datagen.fabric;

import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsStandardRecipeGen;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ExtendedCogwheelsStandardRecipeGenImpl extends ExtendedCogwheelsStandardRecipeGen {
    protected ExtendedCogwheelsStandardRecipeGenImpl(DataGenerator pGenerator) {
        super(pGenerator);
    }

    public static RecipeProvider create(DataGenerator gen) {
        ExtendedCogwheelsStandardRecipeGenImpl provider = new ExtendedCogwheelsStandardRecipeGenImpl(gen);
        return new FabricRecipeProvider((FabricDataGenerator) gen) {
            @Override
            protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
                provider.registerRecipes(exporter);
            }
        };
    }
}
