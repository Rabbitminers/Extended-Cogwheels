package com.rabbitminers.extendedgears.datagen;

import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;

public class ExtendedCogwheelsRecipeTransformers {
    @Deprecated
    public interface RecipeTransformer extends TriFunction<ShapelessRecipeBuilder, ICogwheelMaterial, Boolean, ShapelessRecipeBuilder> {

    }

    @Deprecated
    public static ShapelessRecipeBuilder standardCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                               boolean isLarge) {
        return builder.requires(ExtendedCogwheelsRecipeProvider.I.shaft()).requires(material.getIngredient().ingredient(), isLarge ? 2 : 1);
    }

    @Deprecated
    public static ShapelessRecipeBuilder halfShaftCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                                boolean isLarge) {
        return builder.requires(ExtendedCogwheelsRecipeProvider.I.andesite()).requires(material.getIngredient().ingredient(), isLarge ? 2 : 1);
    }

    @Deprecated
    public static ShapelessRecipeBuilder shaftlessCogwheelTransformer(ShapelessRecipeBuilder builder, ICogwheelMaterial material,
                                                                boolean isLarge) {
        return builder.requires(material.getIngredient().ingredient(), isLarge ? 2 : 1)
                .requires(material.getSmallIngredient().ingredient());
    }

    @Deprecated
    public static <T extends ProcessingRecipe<?>> ProcessingRecipeBuilder<T> standardCogwheelTransformer(ProcessingRecipeBuilder<T> builder, ICogwheelMaterial material,
                                                                                                         BlockEntry<?> smallCogwheel, @Nullable BlockEntry<?> largeCogwheel, boolean isLarge) {
        return builder.require(isLarge && largeCogwheel != null ? smallCogwheel.get() : ExtendedCogwheelsRecipeProvider.I.shaft())
                .require(material.getIngredient().ingredient())
                .output(isLarge && largeCogwheel != null ? largeCogwheel.asStack() : smallCogwheel.asStack());
    }

    @Deprecated
    public static <T extends ProcessingRecipe<?>> ProcessingRecipeBuilder<T> halfShaftCogwheelTransformer(ProcessingRecipeBuilder<T> builder, ICogwheelMaterial material,
                                                                                                         BlockEntry<?> smallCogwheel, @Nullable BlockEntry<?> largeCogwheel, boolean isLarge) {
        return builder.require(isLarge && largeCogwheel != null ? smallCogwheel.get() : ExtendedCogwheelsRecipeProvider.I.andesite())
                .require(material.getIngredient().ingredient())
                .output(isLarge && largeCogwheel != null ? largeCogwheel.asStack() : smallCogwheel.asStack());
    }

    @Deprecated
    public static <T extends ProcessingRecipe<?>> ProcessingRecipeBuilder<T> shaftlessCogwheelTransformer(ProcessingRecipeBuilder<T> builder, ICogwheelMaterial material,
                                                                                                         BlockEntry<?> smallCogwheel, @Nullable BlockEntry<?> largeCogwheel, boolean isLarge) {
        if (isLarge && largeCogwheel != null) {
            return builder.require(smallCogwheel.get()).require(material.getIngredient().ingredient())
                    .output(largeCogwheel.asStack());
        } else {
            return builder.require(material.getIngredient().ingredient()).require(material.getSmallIngredient()
                    .ingredient()).output(smallCogwheel.asStack());
        }
    }

}
