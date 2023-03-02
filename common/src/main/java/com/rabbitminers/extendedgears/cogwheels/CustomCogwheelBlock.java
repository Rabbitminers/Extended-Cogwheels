package com.rabbitminers.extendedgears.cogwheels;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;


public class CustomCogwheelBlock extends CogWheelBlock implements ICustomCogwheel {
    private final PartialModel partialModel;

    public CustomCogwheelBlock(boolean large, Properties properties, PartialModel model) {
        super(large, properties);
        this.partialModel = model;
    }

    public static CustomCogwheelBlock small(Properties properties, PartialModel model) {
        return new CustomCogwheelBlock(false, properties, model);
    }

    public static CustomCogwheelBlock large(Properties properties, PartialModel model) {
        return new CustomCogwheelBlock(true, properties, model);
    }

    @Override
    public PartialModel getPartialModelType() {
        return ICustomCogwheel.super.getPartialModelType();
    }

    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return super.getTileEntityType();
    }
}
