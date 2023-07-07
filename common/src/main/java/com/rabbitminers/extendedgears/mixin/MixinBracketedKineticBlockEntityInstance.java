package com.rabbitminers.extendedgears.mixin;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.Material;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.model.BlockModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rabbitminers.extendedgears.cogwheels.CogwheelModelKey;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.legacy.ICustomCogwheel;
import com.rabbitminers.extendedgears.mixin_interface.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityInstance;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.render.CachedBufferer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(BracketedKineticBlockEntityInstance.class)
public class MixinBracketedKineticBlockEntityInstance extends SingleRotatingInstance<BracketedKineticBlockEntity> {

    protected boolean large;
    protected CogwheelModelKey key;

    public MixinBracketedKineticBlockEntityInstance(MaterialManager materialManager, BracketedKineticBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(MaterialManager materialManager, BracketedKineticBlockEntity blockEntity, CallbackInfo ci) {
        if (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity &&
                !blockEntity.getBlockState().is(AllBlocks.SHAFT.get())) {
            this.large = blockEntity.getBlockState().getBlock() instanceof ICogWheel cogWheel && cogWheel.isLargeCog();
            this.key = new CogwheelModelKey(large, getRenderedBlockState(), dynamicMaterialBlockEntity.getMaterial());
        }
    }

    /**
     * @author Rabbitminers
     * @reason Remove Additional shaft
     */
    @Overwrite(remap = false)
    public void init() {
        super.init();
    }

    @Override
    public boolean shouldReset() {
        return super.shouldReset() || (blockEntity instanceof IDynamicMaterialBlockEntity dynamicMaterialBlockEntity
                && key.material() != dynamicMaterialBlockEntity.getMaterial());
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        if (key == null)
            return super.getModel();

        return getRotatingMaterial().model(key, () -> {
            BakedModel model = DynamicCogwheelRenderer.generateModel(key);
            BlockState state = key.state();
            AxisDirection axisDirection = state.getBlock() instanceof HalfShaftCogwheelBlock halfShaftCogwheelBlock
                    ? halfShaftCogwheelBlock.getAxisDirection(state)
                    : AxisDirection.POSITIVE;
            Direction dir = Direction.fromAxisAndDirection(state.getValue(RotatedPillarKineticBlock.AXIS), axisDirection);
            PoseStack transform = CachedBufferer.rotateToFaceVertical(dir).get();
            return new BlockModel(model, Blocks.AIR.defaultBlockState(), transform);
        });
    }

}
