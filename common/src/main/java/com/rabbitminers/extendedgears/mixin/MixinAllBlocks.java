package com.rabbitminers.extendedgears.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(AllBlocks.class)
public class MixinAllBlocks {
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/tterrag/registrate/builders/BlockBuilder;initialProperties(Lcom/tterrag/registrate/util/nullness/NonNullSupplier;)Lcom/tterrag/registrate/builders/BlockBuilder;"
            ),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=shaft"),
                    to = @At(value = "CONSTANT", args = "stringValue=andesite_encased_shaft")
            ),
            remap = false
    )
    private static <T extends CogWheelBlock, P> BlockBuilder<T, P> changeLayerType(BlockBuilder<T, P> instance, NonNullSupplier<T> block) {
        return instance.initialProperties(block).addLayer(() -> RenderType::translucent);
    }
}
