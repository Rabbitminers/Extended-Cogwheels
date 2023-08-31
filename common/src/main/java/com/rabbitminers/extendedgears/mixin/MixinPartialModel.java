package com.rabbitminers.extendedgears.mixin;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.mixin_interface.PartialModelStore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Consumer;

@Mixin(PartialModel.class)
public class MixinPartialModel implements PartialModelStore {
    @Shadow @Final private static List<PartialModel> ALL;

    @Inject(method = "onModelRegistry", at = @At("TAIL"))
    private static void storePartial(ResourceManager manager, Consumer<ResourceLocation> out, CallbackInfo ci) {
        for (PartialModel partialModel : ALL)
            models.put(partialModel.getLocation(), partialModel);
    }
}
