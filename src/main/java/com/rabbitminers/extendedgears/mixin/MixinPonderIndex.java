package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.index.AddonBlocks;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.foundation.ponder.content.PonderIndex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PonderIndex.class)
public class MixinPonderIndex {
    @Inject(at = @At("HEAD"), method = "registerTags", remap = false)
    private static void registerTags(CallbackInfo ci) {
        PonderRegistry.TAGS.forTag(PonderTag.KINETIC_RELAYS)
                .add(AddonBlocks.CRIMSON_COGWHEEL);
    }
}
