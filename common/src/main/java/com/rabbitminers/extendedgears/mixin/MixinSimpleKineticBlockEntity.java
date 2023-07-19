package com.rabbitminers.extendedgears.mixin;

import com.rabbitminers.extendedgears.cogwheels.CogwheelMaterials;
import com.rabbitminers.extendedgears.mixin_interface.IDynamicMaterialBlockEntity;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
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
    public BlockState material = Blocks.SPRUCE_PLANKS.defaultBlockState();

    public MixinSimpleKineticBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public BlockState getMaterial() {
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
        @Nullable BlockState material = getModelState(stack);
        if (material == null)
            return InteractionResult.PASS;
        this.material = material;
        notifyUpdate();
        level.levelEvent(2001, worldPosition, Block.getId(material));
        return InteractionResult.SUCCESS;
    }

    @Nullable
    public BlockState getModelState(ItemStack stack) {
        Optional<BlockState> custom = CogwheelMaterials.of(stack);
        if (custom.isPresent() && custom.get() != this.material)
            return custom.get();
        if (!(stack.getItem() instanceof BlockItem blockItem))
            return null;
        BlockState material = blockItem.getBlock().defaultBlockState();
        if (!material.is(BlockTags.PLANKS))
            return null;
        if (material == this.material) return null;
        return material;
    }

    @Override
    public void applyMaterial(BlockState material) {
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

        BlockState prevMaterial = material;
        if (!compound.contains("Material"))
            return;

        material = NbtUtils.readBlockState(compound.getCompound("Material"));
        if (material.isAir())
            material = Blocks.SPRUCE_PLANKS.defaultBlockState();

        if (clientPacket && prevMaterial != material)
            redraw();
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        if (this.isEncasedCogwheel())
            compound.put("Material", NbtUtils.writeBlockState(material));
    }
}
