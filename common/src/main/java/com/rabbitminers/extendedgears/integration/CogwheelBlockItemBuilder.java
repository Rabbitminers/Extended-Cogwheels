package com.rabbitminers.extendedgears.integration;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import dev.latvian.mods.kubejs.block.BlockItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class CogwheelBlockItemBuilder extends BlockItemBuilder {

    public CogwheelBlockItemBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public Item createObject() {
        if (this.blockBuilder.get() instanceof CogWheelBlock cogwheelBlock)
            return new CogwheelBlockItem(cogwheelBlock, this.createItemProperties());
        return super.createObject();
    }
}
