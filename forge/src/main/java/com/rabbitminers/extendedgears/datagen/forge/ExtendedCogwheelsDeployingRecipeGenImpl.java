package com.rabbitminers.extendedgears.datagen.forge;

import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsDeployingRecipeGen;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsProcessingRecipeGen;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.utility.recipe.IRecipeTypeInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ExtendedCogwheelsDeployingRecipeGenImpl extends ExtendedCogwheelsProcessingRecipeGen {
    public ExtendedCogwheelsDeployingRecipeGenImpl(DataGenerator generator) {
        super(generator);
    }

    public static RecipeProvider create(DataGenerator gen) {
        ExtendedCogwheelsDeployingRecipeGen provider = new ExtendedCogwheelsDeployingRecipeGen(gen);
        return new RecipeProvider(gen) {
            @Override
            protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
                provider.registerRecipes(consumer);
            }
        };
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.DEPLOYING;
    }
}
