package com.rabbitminers.extendedgears.datagen.fabric;

import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsStandardRecipeGen;
import com.rabbitminers.extendedgears.datagen.IRecipeConditionContainer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ExtendedCogwheelsStandardRecipeGenImpl extends ExtendedCogwheelsStandardRecipeGen {
    protected ExtendedCogwheelsStandardRecipeGenImpl(DataGenerator pGenerator) {
        super(pGenerator);
    }

    public static IRecipeConditionContainer createContainer() {
        return new RecipeConditionContainerFabric();
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
