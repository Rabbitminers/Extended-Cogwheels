package com.rabbitminers.extendedgears.datagen;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.simibubi.create.Create;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.data.recipe.*;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
//import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public abstract class ExtendedCogwheelsProcessingRecipeGen extends ExtendedCogwheelsRecipeProvider {
    protected static final List<com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen> GENERATORS = new ArrayList<>();
    protected static final long BUCKET = FluidConstants.BUCKET;
    protected static final long BOTTLE = FluidConstants.BOTTLE;

    public static void registerAll(FabricDataGenerator gen) {
        GENERATORS.add(new CrushingRecipeGen(gen));
        GENERATORS.add(new MillingRecipeGen(gen));
        GENERATORS.add(new CuttingRecipeGen(gen));
        GENERATORS.add(new WashingRecipeGen(gen));
        GENERATORS.add(new PolishingRecipeGen(gen));
        GENERATORS.add(new DeployingRecipeGen(gen));
        GENERATORS.add(new MixingRecipeGen(gen));
        GENERATORS.add(new CompactingRecipeGen(gen));
        GENERATORS.add(new PressingRecipeGen(gen));
        GENERATORS.add(new FillingRecipeGen(gen));
        GENERATORS.add(new EmptyingRecipeGen(gen));
        GENERATORS.add(new HauntingRecipeGen(gen));
        GENERATORS.add(new ItemApplicationRecipeGen(gen));
        gen.addProvider(new DataProvider() {

            @Override
            public String getName() {
                return "Extended Cogwheels' Processing Recipes";
            }

            @Override
            public void run(HashCache dc) throws IOException {
                GENERATORS.forEach(g -> {
                    try {
                        g.run(dc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    public ExtendedCogwheelsProcessingRecipeGen(DataGenerator generator) {
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
    public String getName() {
        return "ExtendedCogwheels' Processing Recipes: " + getRecipeType().getId()
                .getPath();
    }

}
