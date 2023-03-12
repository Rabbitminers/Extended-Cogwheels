package com.rabbitminers.extendedgears.datagen.fabric;

import com.google.gson.JsonObject;
import com.rabbitminers.extendedgears.datagen.IRecipeConditionContainer;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RecipeConditionContainerFabric implements IRecipeConditionContainer {
    List<ConditionJsonProvider> recipeConditions;

    public RecipeConditionContainerFabric() {
        recipeConditions = new ArrayList<>();
    }

    public boolean isEmpty() {
        return recipeConditions.isEmpty();
    }

    public void whenTagsFilled(@Nullable TagKey<Item>... tagKeys) {
        if (tagKeys == null) return;
        recipeConditions.add(DefaultResourceConditions
                .and(DefaultResourceConditions.itemTagsPopulated(tagKeys)));
    }

    public void write(JsonObject object) {
        ConditionJsonProvider.write(object, recipeConditions.toArray(new ConditionJsonProvider[0]));
    }

}
