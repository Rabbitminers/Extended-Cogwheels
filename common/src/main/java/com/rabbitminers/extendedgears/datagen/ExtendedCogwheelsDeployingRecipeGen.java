package com.rabbitminers.extendedgears.datagen;

import com.mojang.datafixers.util.Function5;
import com.rabbitminers.extendedgears.base.data.CogwheelConstants;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelMaterialList;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataGenerator.PackGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class ExtendedCogwheelsDeployingRecipeGen extends ExtendedCogwheelsProcessingRecipeGen {
    public ExtendedCogwheelsDeployingRecipeGen(PackOutput generator) {
        super(generator);
    }

    private Couple<GeneratedRecipe> smallAndLargeDeployedRecipe(Couple<BlockEntry<?>> blocks) {
        GeneratedRecipe small = create(blocks.getFirst().getId().getPath(), b ->
                b.require(I.shaft()).require(I.planks()).output(blocks.getFirst().get()));
        GeneratedRecipe large = create(blocks.getSecond().getId().getPath(), b ->
                b.require(blocks.getFirst().get()).require(I.planks()).output(blocks.getSecond().get()));
        return Couple.create(small, large);
    }

    Couple<GeneratedRecipe>
            HALF_SHAFT = smallAndLargeDeployedRecipe(CogwheelConstants.HALF_SHAFT_COGWHEELS),
            SHAFTLESSS = smallAndLargeDeployedRecipe(CogwheelConstants.SHAFTLESS_COGWHEELS);

    @Deprecated
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
