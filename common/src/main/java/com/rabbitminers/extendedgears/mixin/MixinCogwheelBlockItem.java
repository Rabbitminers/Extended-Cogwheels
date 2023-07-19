package com.rabbitminers.extendedgears.mixin;

import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(CogwheelBlockItem.class)
public class MixinCogwheelBlockItem extends BlockItem {
    public MixinCogwheelBlockItem(Block block, Properties properties) {
        super(block, properties);
    }
}
