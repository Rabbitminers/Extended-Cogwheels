package com.rabbitminers.extendedgears.datagen;

import com.google.gson.JsonObject;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public interface IRecipeConditionContainer {
    public boolean isEmpty();

    public void whenTagsFilled(@Nullable TagKey<Item>... key);

    public void write(JsonObject object);
}
