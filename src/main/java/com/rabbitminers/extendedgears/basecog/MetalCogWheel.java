package com.rabbitminers.extendedgears.basecog;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.index.AddonTileEntities;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MetalCogWheel extends CogWheelBlock implements ICustomCogWheel {
    public PartialModel model;
    protected MetalCogWheel(boolean large, Properties properties, PartialModel model) {
        super(large, properties);
        this.model = model;
    }
    public static MetalCogWheel small(Properties properties, PartialModel model) {
        return new MetalCogWheel(false, properties, model);
    }

    public static MetalCogWheel large(Properties properties, PartialModel model) {
        return new MetalCogWheel(true, properties, model);
    }

    @Override
    public PartialModel getPartialModelType() {
        return this.model;
    }
    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return AddonTileEntities.BRACKETED_KINETIC.get();
    }
}
