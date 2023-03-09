package com.rabbitminers.extendedgears.datagen;

import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.data.MetalCogwheel;
import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.utility.recipe.IRecipeTypeInfo;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtendedCogwheelsDeployingRecipeGen extends ExtendedCogwheelsProcessingRecipeGen {
    public ExtendedCogwheelsDeployingRecipeGen(DataGenerator generator) {
        super(generator);
    }

    private static String boolToSize(boolean isLarge) {
        return isLarge ? "large" : "small";
    }

    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> sequencedCogwheelGenerator(Class<T> materialType, boolean isLarge) {
        return Arrays.stream(materialType.getEnumConstants())
                .flatMap(material -> Arrays.stream(material.getIngredients())
                        .map(provider -> new AbstractMap.SimpleEntry<>(material,
                                create(boolToSize(isLarge) + "_" + material.asId() + "_" + provider.namespace().asId(),
                                        b -> b.require(isLarge ? material.getLargeCogwheel().get() : I.shaft())
                                                .require(provider.ingredient())
                                                .output(material.getCogwheel(isLarge).asStack())))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, () ->
                        new EnumMap<>(materialType)));
    }

    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> smallSequencedCogwheelGenerator(Class<T> materialType) {
        return sequencedCogwheelGenerator(materialType, false);
    }

    private <T extends Enum<T> & ICogwheelMaterial> Map<T, GeneratedRecipe> largeSequencedCogwheelGenerator(Class<T> materialType) {
        return sequencedCogwheelGenerator(materialType, true);
    }

    final Map<WoodenCogwheel, GeneratedRecipe>
            WOODEN_COGWHEELS = smallSequencedCogwheelGenerator(WoodenCogwheel.class),
            LARGE_WOODEN_COGWHEELS = largeSequencedCogwheelGenerator(WoodenCogwheel.class);


    final Map<MetalCogwheel, GeneratedRecipe>
            METAL_COGWHEELS = smallSequencedCogwheelGenerator(MetalCogwheel.class),
            LARGE_METAL_COGWHEELS = largeSequencedCogwheelGenerator(MetalCogwheel.class);

    @ExpectPlatform
    public static RecipeProvider create(DataGenerator gen) {
        throw new AssertionError();
    }


    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.DEPLOYING;
    }
}
