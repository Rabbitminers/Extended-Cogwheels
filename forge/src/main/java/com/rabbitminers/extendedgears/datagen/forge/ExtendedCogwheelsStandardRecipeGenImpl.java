package com.rabbitminers.extendedgears.datagen.forge;

import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsStandardRecipeGen;
import com.rabbitminers.extendedgears.datagen.IRecipeConditionContainer;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ExtendedCogwheelsStandardRecipeGenImpl extends ExtendedCogwheelsStandardRecipeGen {
    protected ExtendedCogwheelsStandardRecipeGenImpl(DataGenerator pGenerator) {
        super(pGenerator);
    }

    public static IRecipeConditionContainer createContainer() {
        return new RecipeConditionContainerForge();
    }

    public static RecipeProvider create(DataGenerator gen) {
        ExtendedCogwheelsStandardRecipeGen provider = new ExtendedCogwheelsStandardRecipeGenImpl(gen);
        return new RecipeProvider(gen) {
            @Override
            protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
                provider.registerRecipes(consumer);
            }
        };
    }
}
