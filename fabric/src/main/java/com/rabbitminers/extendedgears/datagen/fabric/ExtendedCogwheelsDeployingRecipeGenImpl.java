package com.rabbitminers.extendedgears.datagen.fabric;

import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsDeployingRecipeGen;
import com.rabbitminers.extendedgears.datagen.ExtendedCogwheelsProcessingRecipeGen;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ExtendedCogwheelsDeployingRecipeGenImpl extends ExtendedCogwheelsProcessingRecipeGen {
    public ExtendedCogwheelsDeployingRecipeGenImpl(DataGenerator generator) {
        super(generator);
    }

    public static RecipeProvider create(DataGenerator gen) {
        ExtendedCogwheelsDeployingRecipeGen provider = new ExtendedCogwheelsDeployingRecipeGen(gen);
        return new FabricRecipeProvider((FabricDataGenerator) gen) {
            @Override
            protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
                provider.registerRecipes(exporter);
            }
        };
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.DEPLOYING;
    }

    @SafeVarargs
    public static <T extends ProcessingRecipe<?>> ProcessingRecipeBuilder<T> whenTagsPopulated(ProcessingRecipeBuilder<T> builder, @Nullable TagKey<Item>... tagKeys) {
        return tagKeys == null ? builder : builder.withCondition(DefaultResourceConditions
                .and(DefaultResourceConditions.itemTagsPopulated(tagKeys)));
    }
}
