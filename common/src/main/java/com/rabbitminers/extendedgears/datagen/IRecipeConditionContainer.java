package com.rabbitminers.extendedgears.datagen;

import com.google.gson.JsonObject;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public interface IRecipeConditionContainer {
    boolean isEmpty();

    void whenTagsFilled(@Nullable TagKey<Item>... key);

    void write(JsonObject object);
}
