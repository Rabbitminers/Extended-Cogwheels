package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.base.util.MaterialHelpers;
import com.rabbitminers.extendedgears.cogwheels.DynamicCogwheelRenderer;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterial;
import com.rabbitminers.extendedgears.cogwheels.materials.CogwheelMaterialManager;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.utility.NBTHelper;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(SimpleKineticBlockEntity.class)
public class MixinSimpleKineticBlockEntity extends KineticBlockEntity implements IDynamicMaterialBlockEntity {
    public ResourceLocation material = RegisteredObjects.getKeyOrThrow(Blocks.SPRUCE_PLANKS);

    public MixinSimpleKineticBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public ResourceLocation getMaterial() {
        return this.material;
    }

    private boolean isEncasedCogwheel() {
        return this.getBlockState().getBlock() instanceof EncasedCogwheelBlock;
    }

    @Override
    public InteractionResult applyMaterialIfValid(ItemStack stack) {
        if (!this.isEncasedCogwheel())
            return InteractionResult.SUCCESS;
        if (level == null || (level.isClientSide() && !isVirtual()))
            return InteractionResult.SUCCESS;
        @Nullable
        ResourceLocation material = MaterialHelpers.getModelKey(stack, this.material);
        if (material == null)
            return InteractionResult.PASS;
        this.material = material;
        notifyUpdate();
        level.levelEvent(2001, worldPosition, Block.getId(getBlockState()));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void applyMaterial(ResourceLocation material) {
        this.material = material;
    }

    protected void redraw() {
        if (!isVirtual())
            requestModelDataUpdate();
        if (hasLevel()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 16);
            level.getChunkSource()
                    .getLightEngine()
                    .checkBlock(worldPosition);
        }
    }
    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);

        if (!this.isEncasedCogwheel())
            return;

        ResourceLocation prevMaterial = material;
        if (!compound.contains("Material"))
            return;

        material = NBTHelper.readResourceLocation(compound, "Material");
        if (material == null)
            material = RegisteredObjects.getKeyOrThrow(Blocks.SPRUCE_PLANKS);

        if (clientPacket && prevMaterial != material)
            redraw();
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        if (this.isEncasedCogwheel())
            NBTHelper.writeResourceLocation(compound, "Material", material);
    }
}
