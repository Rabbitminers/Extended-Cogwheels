package com.rabbitminers.extendedgears.mixin_interface;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public interface IDynamicMaterialBlockEntity {
    BlockState getMaterial();
    InteractionResult applyMaterialIfValid(ItemStack stack);
}
