package com.rabbitminers.extendedgears.mixin_interface;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;

public interface IDynamicMaterialBlockEntity {
    ResourceLocation getMaterial();

    InteractionResult applyMaterialIfValid(ItemStack stack);

    void applyMaterial(ResourceLocation material);
}
