package com.rabbitminers.extendedgears.datagen;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.simibubi.create.Create;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.RegisteredObjects;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public abstract class ExtendedCogwheelsProcessingRecipeGen extends ExtendedCogwheelsRecipeProvider {
    public ExtendedCogwheelsProcessingRecipeGen(PackOutput generator) {
        super(generator);
    }

    /**
     * Create a processing recipe with a single itemstack ingredient, using its id
     * as the name of the recipe
     */
    protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(String namespace,
                                                                     Supplier<ItemLike> singleIngredient, UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        ProcessingRecipeSerializer<T> serializer = getSerializer();
        GeneratedRecipe generatedRecipe = c -> {
            ItemLike iItemProvider = singleIngredient.get();
            transform
                    .apply(new ProcessingRecipeBuilder<>(serializer.getFactory(),
                            new ResourceLocation(namespace, RegisteredObjects.getKeyOrThrow(iItemProvider.asItem())
                                    .getPath())).withItemIngredients(Ingredient.of(iItemProvider)))
                    .build(c);
        };
        all.add(generatedRecipe);
        return generatedRecipe;
    }

    /**
     * Create a processing recipe with a single itemstack ingredient, using its id
     * as the name of the recipe
     */
    <T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<ItemLike> singleIngredient,
                                                           UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(Create.ID, singleIngredient, transform);
    }

    protected <T extends ProcessingRecipe<?>> GeneratedRecipe createWithDeferredId(Supplier<ResourceLocation> name,
                                                                                   UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        ProcessingRecipeSerializer<T> serializer = getSerializer();
        GeneratedRecipe generatedRecipe =
                c -> transform.apply(new ProcessingRecipeBuilder<>(serializer.getFactory(), name.get()))
                        .build(c);
        all.add(generatedRecipe);
        return generatedRecipe;
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(ResourceLocation name,
                                                                     UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return createWithDeferredId(() -> name, transform);
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    <T extends ProcessingRecipe<?>> GeneratedRecipe create(String name,
                                                           UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(ExtendedCogwheels.asResource(name), transform);
    }

    protected abstract IRecipeTypeInfo getRecipeType();

    protected <T extends ProcessingRecipe<?>> ProcessingRecipeSerializer<T> getSerializer() {
        return getRecipeType().getSerializer();
    }

    protected Supplier<ResourceLocation> idWithSuffix(Supplier<ItemLike> item, String suffix) {
        return () -> {
            ResourceLocation registryName = RegisteredObjects.getKeyOrThrow(item.get()
                    .asItem());
            return ExtendedCogwheels.asResource(registryName.getPath() + suffix);
        };
    }

    @Override
    public @NotNull String getName() {
        return "ExtendedCogwheels' Processing Recipes: " + getRecipeType().getId()
                .getPath();
    }

}
