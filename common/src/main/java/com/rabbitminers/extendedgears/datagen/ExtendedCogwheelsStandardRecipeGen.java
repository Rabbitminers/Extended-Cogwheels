package com.rabbitminers.extendedgears.datagen;

import com.google.gson.JsonObject;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.CogwheelConstants;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelMaterialList;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.StandardRecipeGen;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ExtendedCogwheelsStandardRecipeGen extends ExtendedCogwheelsRecipeProvider {
    private static final String CRAFTING = "crafting/";
    private static final String BASE = CRAFTING + "base/";
    private static final String TO_STANDARD = CRAFTING + "to_standard/";
    private static final String FROM_SMALL = CRAFTING + "from_small/";

    private Couple<GeneratedRecipe> cogwheelRecipes(Couple<BlockEntry<?>> blocks) {
        GeneratedRecipe small = create(blocks.getFirst()).unlockedBy(I::andesite)
                .viaShapeless(b -> b.requires(I.shaft())
                        .requires(I.planks()));
        GeneratedRecipe large = create(blocks.getSecond()).unlockedBy(I::andesite)
                .viaShapeless(b -> b.requires(I.shaft())
                        .requires(I.planks())
                        .requires(I.planks()));
        return Couple.create(small, large);
    }

    private GeneratedRecipe smallToLargeRecipe(Couple<BlockEntry<?>> blocks) {
        return create("small_" + blocks.getSecond().getId().getPath() + "_to_large", blocks.getSecond())
                .unlockedBy(I::andesite).viaShapeless(b -> b.requires(blocks.getFirst().get()).requires(I.planks()));
    }

    GeneratedRecipe

    HALF_SHAFT = create(ExtendedCogwheelsBlocks.HALF_SHAFT_COGWHEEL).unlockedBy(I::andesite)
            .viaShapeless(b -> b.requires(I.andesite())
                    .requires(I.planks())),

    LARGE_HALF_SHAFT = create(ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_COGWHEEL)
            .unlockedBy(I::andesite).viaShapeless(b -> b.requires(I.andesite())
                    .requires(I.planks())
                    .requires(I.planks())),

    SHAFTLESS = create(ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL).unlockedBy(I::andesite)
            .viaShapeless(b -> b.requires(I.buttons())
                    .requires(I.planks())),

    LARGE_SHAFTLESS = create(ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_COGWHEEL)
            .unlockedBy(I::andesite).viaShapeless(b -> b.requires(I.buttons())
                    .requires(I.planks())
                    .requires(I.planks()));
    ;
    GeneratedRecipe

    SMALL_TO_LARGE_HALF_SHAFT = smallToLargeRecipe(CogwheelConstants.HALF_SHAFT_COGWHEELS),
    SMALL_TO_LARGE_SHAFTLESS = smallToLargeRecipe(CogwheelConstants.SHAFTLESS_COGWHEELS),
    SHAFTLESS_TO_STANDARD = create(AllBlocks.COGWHEEL).unlockedBy(I::andesite)
            .viaShapeless(b -> b.requires(ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL.get())
                    .requires(I.planks())),
    LARGE_SHAFTLESS_TO_STANDARD = create(AllBlocks.LARGE_COGWHEEL).unlockedBy(I::andesite)
            .viaShapeless(b -> b.requires(ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_COGWHEEL.get())
            .requires(I.planks())),
    PISTON_EXTENSION_POLE = create(AllBlocks.PISTON_EXTENSION_POLE).returns(8)
            .unlockedBy(I::andesite).viaShaped(b -> b.define('A', I.andesite())
                    .define('P', ItemTags.PLANKS)
                    .pattern("A")
                    .pattern("P")
                    .pattern("A"));

    @Deprecated
    private <T extends Block, E extends Enum<E> & ICogwheelMaterial> GeneratedRecipe cogwheelSmeltingRecipe(CogwheelMaterialList<T, E> inputMaterialSet, E input, E output) {
        return create(inputMaterialSet.get(output))
                .withSuffix("_from_" + input.asId())
                .viaCooking(inputMaterialSet.get(input))
                .rewardXP(1f)
                .inBlastFurnace();
    }

    GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(String path, Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder(path, result);
    }

    GeneratedRecipeBuilder create(ResourceLocation result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(ItemProviderEntry<? extends ItemLike> result) {
        return create(result::get);
    }

    GeneratedRecipeBuilder create(String path, ItemProviderEntry<? extends ItemLike> result) {
        return create(path, result::get);
    }

    public ExtendedCogwheelsStandardRecipeGen(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    public @NotNull String getName() {
        return "Extended Cogwheels Crafting Recipes";
    }


    class GeneratedRecipeBuilder {

        private final String path;
        private String suffix;
        private Supplier<? extends ItemLike> result;
        private ResourceLocation compatDatagenOutput;

        private Supplier<ItemPredicate> unlockedBy;
        private int amount;

        private GeneratedRecipeBuilder(String path) {
            this.path = path;
            this.suffix = "";
            this.amount = 1;
        }

        public GeneratedRecipeBuilder(String path, Supplier<? extends ItemLike> result) {
            this(path);
            this.result = result;
        }

        public GeneratedRecipeBuilder(String path, ResourceLocation result) {
            this(path);
            this.compatDatagenOutput = result;
        }

        GeneratedRecipeBuilder returns(int amount) {
            this.amount = amount;
            return this;
        }

        GeneratedRecipeBuilder unlockedBy(Supplier<? extends ItemLike> item) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                    .of(item.get())
                    .build();
            return this;
        }

        GeneratedRecipeBuilder unlockedByTag(Supplier<TagKey<Item>> tag) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                    .of(tag.get())
                    .build();
            return this;
        }

        GeneratedRecipeBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        GeneratedRecipe viaShaped(UnaryOperator<ShapedRecipeBuilder> builder) {
            return register(consumer -> {
                ShapedRecipeBuilder b = builder.apply(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result.get(), amount));
                if (unlockedBy != null)
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                b.save(consumer, createLocation("crafting"));
            });
        }

        GeneratedRecipe viaShapeless(UnaryOperator<ShapelessRecipeBuilder> builder) {
            return register(consumer -> {
                ShapelessRecipeBuilder b = builder.apply(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result.get(), amount));
                if (unlockedBy != null)
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                b.save(consumer, createLocation("crafting"));
            });
        }

        private ResourceLocation createSimpleLocation(String recipeType) {
            return ExtendedCogwheels.asResource(recipeType + "/" + getRegistryName().getPath() + suffix);
        }

        private ResourceLocation createLocation(String recipeType) {
            return ExtendedCogwheels.asResource(recipeType + "/" + path + "/" + getRegistryName().getPath() + suffix);
        }

        private ResourceLocation getRegistryName() {
            return compatDatagenOutput == null ? RegisteredObjects.getKeyOrThrow(result.get()
                    .asItem()) : compatDatagenOutput;
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCooking(Supplier<? extends ItemLike> item) {
            return unlockedBy(item).viaCookingIngredient(() -> Ingredient.of(item.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingTag(Supplier<TagKey<Item>> tag) {
            return unlockedByTag(tag).viaCookingIngredient(() -> Ingredient.of(tag.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingIngredient(Supplier<Ingredient> ingredient) {
            return new GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder(ingredient);
        }

        class GeneratedCookingRecipeBuilder {

            private final Supplier<Ingredient> ingredient;
            private float exp;
            private int cookingTime;

            private final SimpleCookingSerializer<?>
                    FURNACE = (SimpleCookingSerializer<?>) RecipeSerializer.SMELTING_RECIPE,
                    SMOKER = (SimpleCookingSerializer<?>) RecipeSerializer.SMOKING_RECIPE,
                    BLAST = (SimpleCookingSerializer<?>) RecipeSerializer.BLASTING_RECIPE,
                    CAMPFIRE = (SimpleCookingSerializer<?>) RecipeSerializer.CAMPFIRE_COOKING_RECIPE;

            GeneratedCookingRecipeBuilder(Supplier<Ingredient> ingredient) {
                this.ingredient = ingredient;
                cookingTime = 200;
                exp = 0;
            }

            GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder forDuration(int duration) {
                cookingTime = duration;
                return this;
            }

            GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder rewardXP(float xp) {
                exp = xp;
                return this;
            }

            GeneratedRecipe inFurnace() {
                return inFurnace(b -> b);
            }

            GeneratedRecipe inFurnace(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                return create(FURNACE, builder, 1);
            }

            GeneratedRecipe inSmoker() {
                return inSmoker(b -> b);
            }

            GeneratedRecipe inSmoker(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                create(FURNACE, builder, 1);
                create(CAMPFIRE, builder, 3);
                return create(SMOKER, builder, .5f);
            }

            GeneratedRecipe inBlastFurnace() {
                return inBlastFurnace(b -> b);
            }

            GeneratedRecipe inBlastFurnace(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                create(FURNACE, builder, 1);
                return create(BLAST, builder, .5f);
            }

            private GeneratedRecipe create(SimpleCookingSerializer<?> serializer,
                                           UnaryOperator<SimpleCookingRecipeBuilder> builder, float cookingTimeModifier) {
                return register(consumer -> {
                    boolean isOtherMod = compatDatagenOutput != null;

                    // fix|me removed serializer from the cooking time + refactored with whatever intellij said
                    // can't really test this 'cause we don't have any cooking recipes yet, just assume it's fine for now
                    SimpleCookingRecipeBuilder b = builder.apply(
                            SimpleCookingRecipeBuilder.campfireCooking(ingredient.get(), RecipeCategory.MISC, isOtherMod ? Items.DIRT : result.get(),
                                    exp, (int) (cookingTime * cookingTimeModifier)));
                    if (unlockedBy != null)
                        b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                    b.save(consumer, createSimpleLocation(RegisteredObjects.getKeyOrThrow(serializer)
                            .getPath()));
                });
            }
        }
    }
}
