package com.rabbitminers.extendedgears.integration;

import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import dev.latvian.mods.kubejs.block.BlockBuilder;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CogwheelBlockJS extends CogWheelBlock {

    public final BlockBuilder blockBuilder;

    public CogwheelBlockJS(KubeJsCogwheelBuilder builder) {
        super(builder.isLarge, builder.createProperties());
        this.blockBuilder = builder;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return KubeJsCogwheels.tileEntities.get(blockBuilder.id).get();
    }

    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }
}
