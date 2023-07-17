package com.rabbitminers.extendedgears.datagen;

import com.mojang.datafixers.util.Function5;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelMaterialList;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsLegacyBlocks;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class ExtendedCogwheelsDeployingRecipeGen extends ExtendedCogwheelsProcessingRecipeGen {
    public ExtendedCogwheelsDeployingRecipeGen(DataGenerator generator) {
        super(generator);
    }

    private static String boolToSize(boolean isLarge) {
        return isLarge ? "large" : "small";
    }

    @Deprecated
    private <T extends Enum<T> & ICogwheelMaterial> GeneratedRecipe deployedCogwheel(BlockEntry<?> smallCogwheeel, T material,
            Function5<ProcessingRecipeBuilder, T, BlockEntry<?>, BlockEntry<?>, Boolean, ProcessingRecipeBuilder> transformer) {
        return create(smallCogwheeel.get().asItem() + "_cogwheel", b -> {
            b = whenTagsPopulated(b, material.getRecipeTags());
            return transformer.apply(b, material, smallCogwheeel, null, false);
        });
    }

    @Deprecated
    private <T extends Enum<T> & ICogwheelMaterial> GeneratedRecipe largeDeployedCogwheel(BlockEntry<?> smallCogwheel, BlockEntry<?> largeCogwheel,
            T material, Function5<ProcessingRecipeBuilder, T, BlockEntry<?>, BlockEntry<?>, Boolean, ProcessingRecipeBuilder> transformer) {
        return create(largeCogwheel.get().asItem() + "_cogwheel", b -> {
            b = whenTagsPopulated(b, material.getRecipeTags());
            return transformer.apply(b, material, smallCogwheel, largeCogwheel, true);
        });
    }

    @Deprecated
    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> deployedCogwheelMapper(CogwheelMaterialList<? extends Block, T> cogwheels,
           Class<T> materialType, Function5<ProcessingRecipeBuilder, T, BlockEntry<?>, BlockEntry<?>, Boolean, ProcessingRecipeBuilder> transformer) {
        Map<T, GeneratedRecipe> map = new EnumMap<T, GeneratedRecipe>(materialType);
        for (T material : materialType.getEnumConstants()) {
            map.put(material, deployedCogwheel(cogwheels.get(material), material, transformer));
        }
        return map;
    }

    @Deprecated
    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> largeDeployedCogwheelMapper(CogwheelMaterialList<? extends Block, T> smallCogwheels,
        CogwheelMaterialList<? extends Block, T> largeCogwheels, Class<T> materialType, Function5<ProcessingRecipeBuilder, T, BlockEntry<?>, BlockEntry<?>, Boolean, ProcessingRecipeBuilder> transformer) {
        Map<T, GeneratedRecipe> map = new EnumMap<T, GeneratedRecipe>(materialType);
        for (T material : materialType.getEnumConstants()) {
            map.put(material, largeDeployedCogwheel(smallCogwheels.get(material), largeCogwheels.get(material), material, transformer));
        }
        return map;
    }

    @Deprecated
    private <T extends Enum<T> & ICogwheelMaterial> CogwheelRecipePair<T> smallAndLargeDeployedRecipe(CogwheelMaterialList<? extends Block, T> smallCogwheels,
           CogwheelMaterialList<? extends Block, T> largeCogwheels, Class<T> materialType,  Function5<ProcessingRecipeBuilder, T, BlockEntry<?>, BlockEntry<?>, Boolean, ProcessingRecipeBuilder> transformer) {
        return new CogwheelRecipePair<>(deployedCogwheelMapper(smallCogwheels, materialType, transformer),
                largeDeployedCogwheelMapper(smallCogwheels, largeCogwheels, materialType, transformer));
    }


    private record CogwheelRecipePair<T extends Enum<T> & ICogwheelMaterial>(Map<T, GeneratedRecipe> small, Map<T, GeneratedRecipe> large) {

    }

    @ExpectPlatform
    public static <T extends ProcessingRecipe<?>> ProcessingRecipeBuilder<T> whenTagsPopulated(ProcessingRecipeBuilder<T> builder,
                                                                                               @Nullable TagKey<Item>... tagKeys) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RecipeProvider create(DataGenerator gen) {
        throw new AssertionError();
    }


    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.DEPLOYING;
    }
}
