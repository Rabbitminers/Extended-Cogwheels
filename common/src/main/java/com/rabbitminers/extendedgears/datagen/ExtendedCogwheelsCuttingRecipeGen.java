package com.rabbitminers.extendedgears.datagen;

import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelMaterialList;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsLegacyBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.data.recipe.CuttingRecipeGen;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public class ExtendedCogwheelsCuttingRecipeGen extends ExtendedCogwheelsProcessingRecipeGen {
    private GeneratedRecipe cogwheelCuttingRecipe(BlockEntry<?> in, BlockEntry<?> out) {
        return cogwheelCuttingRecipe(in.get(), out.get());
    }

    private GeneratedRecipe cogwheelCuttingRecipe(Block in, Block out) {
        return create(() -> in, b -> b.duration(50)
                .output(out).output(0.2f, I.shaft()));
    }

    @Deprecated
    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> cogwheelCuttingRecipe(Class<T> materialType,
           CogwheelMaterialList<? extends Block, T> input, CogwheelMaterialList<? extends Block, T> output) {
        Map<T, GeneratedRecipe> map = new EnumMap<T, GeneratedRecipe>(materialType);
        for (T material : materialType.getEnumConstants())
            map.put(material, cogwheelCuttingRecipe(input.get(material), output.get(material)));
        return map;
    }
    
    GeneratedRecipe
        LARGE = cogwheelCuttingRecipe(AllBlocks.LARGE_COGWHEEL, ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_COGWHEEL),
        SMALL = cogwheelCuttingRecipe(AllBlocks.COGWHEEL, ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL);


    public ExtendedCogwheelsCuttingRecipeGen(PackOutput dataGenerator) {
        super(dataGenerator);
    }

    @ExpectPlatform
    public static RecipeProvider create(DataGenerator gen) {
        throw new AssertionError();
    }

    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.CUTTING;
    }

}
