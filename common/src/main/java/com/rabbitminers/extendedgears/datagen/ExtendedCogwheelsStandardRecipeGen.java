package com.rabbitminers.extendedgears.datagen;

import com.google.gson.JsonObject;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.data.MetalCogwheel;
import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelMaterialList;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
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
    private static final String FROM_SMALL = CRAFTING + "from_small/";

    private ShapelessRecipeBuilder standardCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                               boolean isLarge) {
        return builder.requires(I.shaft()).requires(material.getIngredient().ingredient(), isLarge ? 2 : 1);
    }

    private ShapelessRecipeBuilder halfShaftCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                               boolean isLarge) {
        return builder.requires(I.andesite()).requires(material.getIngredient().ingredient(), isLarge ? 2 : 1);
    }

    private ShapelessRecipeBuilder shaftlessCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                               boolean isLarge) {
        return builder.requires(material.getIngredient().ingredient(), isLarge ? 2 : 1)
                .requires(material.getSmallIngredient().ingredient());
    }

    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> craftedCogwheelMapper(CogwheelMaterialList<? extends Block, T> cogwheels, Class<T> materialType, boolean isLarge,
                                                                                                  TriFunction<ShapelessRecipeBuilder, T, Boolean, ShapelessRecipeBuilder> recipeTransformer) {
        return Arrays.stream(materialType.getEnumConstants())
                .collect(Collectors.toMap(
                        material -> material,
                        material -> create(BASE + material.getIngredient().namespace().asId(), cogwheels.get(material))
                                .unlockedBy(I::andesite)
                                .whenTagsPopulated(material.getRecipeTags())
                                .viaShapeless(builder -> recipeTransformer.apply(builder, material, isLarge)),
                        (m1, m2) -> m1,
                        () -> new EnumMap<>(materialType)
                ));
    }

    final Map<MetalCogwheel, GeneratedRecipe>
        METAL_COGWHEELS = craftedCogwheelMapper(ExtendedCogwheelsBlocks.METAL_COGWHEELS, MetalCogwheel.class, false, this::standardCogwheelTransformer);
    ;

    final Map<WoodenCogwheel, GeneratedRecipe>
        WOODEN_COGWHEELS = craftedCogwheelMapper(ExtendedCogwheelsBlocks.WOODEN_COGWHEELS, WoodenCogwheel.class, false, this::standardCogwheelTransformer);
    ;

    private <T extends Block, E extends Enum<E> & ICogwheelMaterial> GeneratedRecipe cogwheelSmeltingRecipe(CogwheelMaterialList<T, E> materialList, E input, E output) {
        return create(materialList.get(output))
                .withSuffix("_from_" + input.asId())
                .viaCooking(materialList.get(input))
                .rewardXP(1f)
                .inBlastFurnace();
    }

    final GeneratedRecipe
        SMALL_IRON_TO_STEEL = create(MetalCogwheel.STEEL.getSmallCogwheel())
            .withSuffix("_from_" + MetalCogwheel.IRON.asId())
            .viaCooking(MetalCogwheel.IRON.getSmallCogwheel())
            .rewardXP(1f)
            .inBlastFurnace(),
        LARGE_IRON_TO_STEEL = create(MetalCogwheel.STEEL.getLargeCogwheel())
            .withSuffix("_from_" + MetalCogwheel.STEEL.asId())
            .viaCooking(MetalCogwheel.IRON.getLargeCogwheel())
            .rewardXP(1f)
            .inBlastFurnace()
    ;


    GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(String path, Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder(path, result);
    }

    GeneratedRecipeBuilder create(ResourceLocation result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(String path, ItemProviderEntry<? extends ItemLike> result) {
        return create(path, result::get);
    }

    GeneratedRecipeBuilder create(ItemProviderEntry<? extends ItemLike> result) {
        return create(result::get);
    }

    protected ExtendedCogwheelsStandardRecipeGen(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @ExpectPlatform
    public static RecipeProvider create(DataGenerator gen) {
        throw new AssertionError();
    }

    @Override
    public @NotNull String getName() {
        return "Extended Cogwheels Crafting Recipes";
    }

    class GeneratedRecipeBuilder {

        private final String path;
        private String suffix;
        private java.util.function.Supplier<? extends ItemLike> result;
        private ResourceLocation compatDatagenOutput;
        private java.util.function.Supplier<ItemPredicate> unlockedBy;
        List<ConditionJsonProvider> recipeConditions;
        private int amount;

        private GeneratedRecipeBuilder(String path) {
            this.path = path;
            this.suffix = "";
            this.recipeConditions = new ArrayList<>();
            this.amount = 1;
        }

        public GeneratedRecipeBuilder(String path, java.util.function.Supplier<? extends ItemLike> result) {
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

        GeneratedRecipeBuilder unlockedBy(java.util.function.Supplier<? extends ItemLike> item) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                    .of(item.get())
                    .build();
            return this;
        }

        GeneratedRecipeBuilder unlockedByTag(java.util.function.Supplier<TagKey<Item>> tag) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                    .of(tag.get())
                    .build();
            return this;
        }

        GeneratedRecipeBuilder whenModLoaded(String modid) {
            return withCondition(DefaultResourceConditions.allModsLoaded(modid));
        }

        GeneratedRecipeBuilder whenModMissing(String modid) {
            return withCondition(DefaultResourceConditions
                    .not(DefaultResourceConditions.allModsLoaded(modid)));
        }

        @SafeVarargs
        final GeneratedRecipeBuilder whenTagsPopulated(@Nullable TagKey<Item>... tagKey) {
            return tagKey == null ? this : withCondition(DefaultResourceConditions
                    .or(DefaultResourceConditions.itemTagsPopulated(tagKey)));
        }

        @SafeVarargs
        final GeneratedRecipeBuilder whenTagEmpty(@Nullable TagKey<Item>... tagKey) {
            return tagKey == null ? this : withCondition(DefaultResourceConditions
                    .or(DefaultResourceConditions
                            .not(DefaultResourceConditions.itemTagsPopulated(tagKey))));
        }

        GeneratedRecipeBuilder withCondition(ConditionJsonProvider condition) {
            recipeConditions.add(condition);
            return this;
        }


        GeneratedRecipeBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        GeneratedRecipe viaShaped(UnaryOperator<ShapedRecipeBuilder> builder) {
            return register(consumer -> {
                ShapedRecipeBuilder b = builder.apply(ShapedRecipeBuilder.shaped(result.get(), amount));
                if (unlockedBy != null)
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                b.save(consumer, createLocation("crafting"));
            });
        }

        GeneratedRecipe viaShapeless(UnaryOperator<ShapelessRecipeBuilder> builder) {
            return register(consumer -> {
                ShapelessRecipeBuilder b = builder.apply(ShapelessRecipeBuilder.shapeless(result.get(), amount));
                if (unlockedBy != null)
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                b.save(result -> consumer.accept(
                        recipeConditions.isEmpty() ? result
                                : new ConditionalRecipeResult(result, getRegistryName(), recipeConditions)
                ), createLocation("crafting"));
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

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCooking(java.util.function.Supplier<? extends ItemLike> item) {
            return unlockedBy(item).viaCookingIngredient(() -> Ingredient.of(item.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingTag(java.util.function.Supplier<TagKey<Item>> tag) {
            return unlockedByTag(tag).viaCookingIngredient(() -> Ingredient.of(tag.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingIngredient(java.util.function.Supplier<Ingredient> ingredient) {
            return new GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder(ingredient);
        }

        class GeneratedCookingRecipeBuilder {

            private final java.util.function.Supplier<Ingredient> ingredient;
            private float exp;
            private int cookingTime;

            private final SimpleCookingSerializer<?> FURNACE = RecipeSerializer.SMELTING_RECIPE,
                    SMOKER = RecipeSerializer.SMOKING_RECIPE, BLAST = RecipeSerializer.BLASTING_RECIPE,
                    CAMPFIRE = RecipeSerializer.CAMPFIRE_COOKING_RECIPE;

            GeneratedCookingRecipeBuilder(java.util.function.Supplier<Ingredient> ingredient) {
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

                    SimpleCookingRecipeBuilder b = builder.apply(SimpleCookingRecipeBuilder
                            .cooking(ingredient.get(), isOtherMod ? Items.DIRT : result.get(),
                                    exp, (int) (cookingTime * cookingTimeModifier), serializer));
                    if (unlockedBy != null)
                        b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                    b.save(result -> {
                            consumer.accept(result);
                    }, createSimpleLocation(RegisteredObjects.getKeyOrThrow(serializer)
                            .getPath()));
                });
            }
        }
    }

    private static record CogwheelRecipePair(GeneratedRecipe small, GeneratedRecipe large) {

    }

    private static class ConditionalRecipeResult implements FinishedRecipe {
        private final FinishedRecipe wrapped;
        private final ResourceLocation outputOverride;
        private final List<ConditionJsonProvider> conditions;

        public ConditionalRecipeResult(FinishedRecipe wrapped, ResourceLocation outputOverride,
                                         List<ConditionJsonProvider> conditions) {
            this.wrapped = wrapped;
            this.outputOverride = outputOverride;
            this.conditions = conditions;
        }

        @Override
        public ResourceLocation getId() {
            return wrapped.getId();
        }

        @Override
        public RecipeSerializer<?> getType() {
            return wrapped.getType();
        }

        @Override
        public JsonObject serializeAdvancement() {
            return wrapped.serializeAdvancement();
        }

        @Override
        public ResourceLocation getAdvancementId() {
            return wrapped.getAdvancementId();
        }

        @Override
        public void serializeRecipeData(@NotNull JsonObject object) {
            wrapped.serializeRecipeData(object);
            object.addProperty("result", outputOverride.toString());

            ConditionJsonProvider.write(object, conditions.toArray(new ConditionJsonProvider[0]));
        }
    }
}
