package com.rabbitminers.extendedgears.datagen.forge;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rabbitminers.extendedgears.datagen.IRecipeConditionContainer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RecipeConditionContainerForge implements IRecipeConditionContainer {
    List<ICondition> recipeConditions;

    public RecipeConditionContainerForge() {
        recipeConditions = new ArrayList<>();
    }

    public boolean isEmpty() {
        return recipeConditions.isEmpty();
    }

    @SafeVarargs
    public final void whenTagsFilled(@Nullable TagKey<Item>... tagKeys) {
        if (tagKeys == null) return;
        ICondition[] conditions = Arrays.stream(tagKeys)
                .filter(Objects::nonNull)
                .map(tagKey -> new NotCondition(new TagEmptyCondition(tagKey.location())))
                .toArray(ICondition[]::new);
        recipeConditions.add(new AndCondition(conditions));
    }

    public void write(JsonObject object) {
        JsonArray conds = new JsonArray();
        recipeConditions.forEach(c -> conds.add(CraftingHelper.serialize(c)));
        object.add("conditions", conds);
    }
}
