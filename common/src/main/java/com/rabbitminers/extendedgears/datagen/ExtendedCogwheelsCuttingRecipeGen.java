package com.rabbitminers.extendedgears.datagen;

import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelMaterialList;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsLegacyBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllRecipeTypes;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.data.DataGenerator;
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

    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> cogwheelCuttingRecipe(Class<T> materialType,
           CogwheelMaterialList<? extends Block, T> input, CogwheelMaterialList<? extends Block, T> output) {
        Map<T, GeneratedRecipe> map = new EnumMap<T, GeneratedRecipe>(materialType);
        for (T material : materialType.getEnumConstants())
            map.put(material, cogwheelCuttingRecipe(input.get(material), output.get(material)));
        return map;
    }

    /*
    final Map<WoodenCogwheel, GeneratedRecipe>
        WOODEN_COGWHEELS_TO_HALF_SHAFT = cogwheelCuttingRecipe(WoodenCogwheel.class,
            ExtendedCogwheelsBlocks.WOODEN_COGWHEELS, ExtendedCogwheelsBlocks.HALF_SHAFT_WOODEN_COGWHEELS),
        LARGE_WOODEN_COGWHEELS_TO_HALF_SHAFT = cogwheelCuttingRecipe(WoodenCogwheel.class,
            ExtendedCogwheelsBlocks.LARGE_WOODEN_COGWHEELS, ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_WOODEN_COGWHEELS),
        WOODEN_HALF_SHAFT_TO_SHAFTLESS = cogwheelCuttingRecipe(WoodenCogwheel.class,
            ExtendedCogwheelsBlocks.HALF_SHAFT_WOODEN_COGWHEELS, ExtendedCogwheelsBlocks.SHAFTLESS_WOODEN_COGWHEELS),
        LARGE_WOODEN_HALF_SHAFT_TO_SHAFTLESS = cogwheelCuttingRecipe(WoodenCogwheel.class,
            ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_WOODEN_COGWHEELS, ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_WOODEN_COGWHEELS);

    final Map<MetalCogwheel, GeneratedRecipe>
        METAL_COGWHEELS_TO_HALF_SHAFT = cogwheelCuttingRecipe(MetalCogwheel.class,
            ExtendedCogwheelsBlocks.METAL_COGWHEELS, ExtendedCogwheelsBlocks.HALF_SHAFT_METAL_COGWHEELS),
        LARGE_METAL_COGWHEELS_TO_HALF_SHAFT = cogwheelCuttingRecipe(MetalCogwheel.class,
            ExtendedCogwheelsBlocks.LARGE_METAL_COGWHEELS, ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_METAL_COGWHEELS),
        METAL_HALF_SHAFT_TO_SHAFTLESS = cogwheelCuttingRecipe(MetalCogwheel.class,
            ExtendedCogwheelsBlocks.HALF_SHAFT_METAL_COGWHEELS, ExtendedCogwheelsBlocks.SHAFTLESS_METAL_COGWHEELS),
        LARGE_METAL_HALF_SHAFT_TO_SHAFTLESS = cogwheelCuttingRecipe(MetalCogwheel.class,
            ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_METAL_COGWHEELS, ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_METAL_COGWHEELS);
     */

    // Keep spruce recipes
    GeneratedRecipe
        SPRUCE_COGWHEELS_TO_HALF_SHAFT = cogwheelCuttingRecipe(AllBlocks.COGWHEEL,
            ExtendedCogwheelsLegacyBlocks.SPRUCE_HALF_SHAFT_COGWHEEL),
        LARGE_SPRUCE_COGWHEELS_TO_HALF_SHAFT = cogwheelCuttingRecipe(AllBlocks.LARGE_COGWHEEL,
            ExtendedCogwheelsLegacyBlocks.LARGE_SPRUCE_HALF_SHAFT_COGWHEEL),
        SPRUCE_HALF_SHAFT_TO_SHAFTLESS = cogwheelCuttingRecipe(ExtendedCogwheelsLegacyBlocks.SPRUCE_HALF_SHAFT_COGWHEEL,
            ExtendedCogwheelsLegacyBlocks.SPRUCE_SHAFTLESS_COGWHEEL),
        LARGE_SPRUCE_HALF_SHAFT_TO_SHAFTLESS = cogwheelCuttingRecipe(ExtendedCogwheelsLegacyBlocks.LARGE_SPRUCE_HALF_SHAFT_COGWHEEL,
            ExtendedCogwheelsLegacyBlocks.LARGE_SPRUCE_SHAFTLESS_COGWHEEL);

    public ExtendedCogwheelsCuttingRecipeGen(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
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
