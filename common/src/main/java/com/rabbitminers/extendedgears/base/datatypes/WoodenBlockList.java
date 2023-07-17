package com.rabbitminers.extendedgears.base.datatypes;

import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

@Deprecated
public class WoodenBlockList<T extends Block> extends CogwheelMaterialList<T, WoodenCogwheel> {
    public WoodenBlockList(Function<WoodenCogwheel, BlockEntry<? extends T>> filler) {
        super(WoodenCogwheel.class, filler);
    }
}
