package com.rabbitminers.extendedgears.cogwheels.legacy;

import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTileEntities;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;


@Deprecated
public class CustomCogwheelBlock extends CogWheelBlock implements ICustomCogwheel {
    private final ICogwheelMaterial material;

    public CustomCogwheelBlock(boolean large, Properties properties, ICogwheelMaterial material) {
        super(large, properties);
        this.material = material;
    }

    public static CustomCogwheelBlock small(Properties properties, ICogwheelMaterial material) {
        return new CustomCogwheelBlock(false, properties, material);
    }

    public static CustomCogwheelBlock large(Properties properties, ICogwheelMaterial material) {
        return new CustomCogwheelBlock(true, properties, material);
    }

    @Override
    public ICogwheelMaterial getMaterial() {
        return this.material;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ExtendedCogwheelsTileEntities.CUSTOM_COGWHEEL_TILE_ENTITY.get();
    }
}
