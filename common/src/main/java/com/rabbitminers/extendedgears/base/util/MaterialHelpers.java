package com.rabbitminers.extendedgears.base.util;

import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MaterialHelpers {
    @Nullable
    public static ResourceLocation getModelKey(ItemStack stack, ResourceLocation current) {
        ResourceLocation custom = CogwheelMaterialManager.of(stack);
        if (custom != null && custom != current)
            return custom;
        if (!(stack.getItem() instanceof BlockItem blockItem))
            return null;
        BlockState state = blockItem.getBlock().defaultBlockState();
        if (!state.is(BlockTags.PLANKS))
            return null;
        ResourceLocation material = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (material == current) return null;
        return material;
    }
}
