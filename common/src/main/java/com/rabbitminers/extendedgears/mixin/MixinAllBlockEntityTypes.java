package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsBlocks;
import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.foundation.data.CreateBlockEntityBuilder;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(AllBlockEntityTypes.class)
public class MixinAllBlockEntityTypes {
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/foundation/data/CreateBlockEntityBuilder;validBlocks([Lcom/tterrag/registrate/util/nullness/NonNullSupplier;)Lcom/tterrag/registrate/builders/BlockEntityBuilder;"
            ),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=schematic_table"),
                    to = @At(value = "CONSTANT", args = "stringValue=motor")
            ),
            remap = false
    )
    private static <T extends Block, E extends BlockEntity> BlockEntityBuilder<E, ?> addCustomBogeysToTileEntity(
            CreateBlockEntityBuilder<E, ?> instance,
            NonNullSupplier<T>[] blocks
    ) {
        instance.validBlocks(
                ExtendedCogwheelsBlocks.SHAFTLESS_COGWHEEL,
                ExtendedCogwheelsBlocks.LARGE_SHAFTLESS_COGWHEEL,
                ExtendedCogwheelsBlocks.HALF_SHAFT_COGWHEEL,
                ExtendedCogwheelsBlocks.LARGE_HALF_SHAFT_COGWHEEL
        );

        return instance.validBlocks(blocks);
    }
}
