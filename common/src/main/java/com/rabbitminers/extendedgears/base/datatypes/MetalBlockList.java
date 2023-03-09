package com.rabbitminers.extendedgears.base.datatypes;

import com.rabbitminers.extendedgears.base.data.MetalCogwheel;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class MetalBlockList<T extends Block> extends CogwheelMaterialList<T, MetalCogwheel> {
    public MetalBlockList(Function<MetalCogwheel, BlockEntry<? extends T>> filler) {
        super(MetalCogwheel.class, filler);
    }
}
