package com.rabbitminers.extendedgears.datagen;

import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import org.apache.commons.lang3.function.TriFunction;

public class ExtendedCogwheelsRecipeTransformers {
    public interface RecipeTransformer extends TriFunction<ShapelessRecipeBuilder, ICogwheelMaterial, Boolean, ShapelessRecipeBuilder> {

    }

    public static ShapelessRecipeBuilder standardCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                               boolean isLarge) {
        return builder.requires(ExtendedCogwheelsRecipeProvider.I.shaft()).requires(material.getIngredient().ingredient(), isLarge ? 2 : 1);
    }

    public static ShapelessRecipeBuilder halfShaftCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                                boolean isLarge) {
        return builder.requires(ExtendedCogwheelsRecipeProvider.I.andesite()).requires(material.getIngredient().ingredient(), isLarge ? 2 : 1);
    }

    public static ShapelessRecipeBuilder shaftlessCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                                boolean isLarge) {
        return builder.requires(material.getIngredient().ingredient(), isLarge ? 2 : 1)
                .requires(material.getSmallIngredient().ingredient());
    }
}
