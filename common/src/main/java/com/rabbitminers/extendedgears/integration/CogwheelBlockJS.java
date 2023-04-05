package com.rabbitminers.extendedgears.integration;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
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
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return KubeJsCogwheels.tileEntities.get(blockBuilder.id).get();
    }

    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }
}
