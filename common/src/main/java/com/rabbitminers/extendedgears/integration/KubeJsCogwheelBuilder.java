package com.rabbitminers.extendedgears.integration;

import com.google.gson.JsonObject;
import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.utility.VoxelShaper;
import dev.latvian.mods.kubejs.block.BlockBuilder;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock.isDirectionPosotive;
import static com.rabbitminers.extendedgears.registry.ExtendedCogwheelsTags.ExtendedCogwheelsItemTags.COGWHEEL;

public class KubeJsCogwheelBuilder extends BlockBuilder {
    private final String parent;
    private final CogwheelType type;
    final boolean isLarge;

    public KubeJsCogwheelBuilder(ResourceLocation i, boolean isLarge, CogwheelType type) {
        super(i);
        this.tagItem(COGWHEEL.tag.location());
        this.isLarge = isLarge;
        this.type = type;
        this.parent = type.getParentModel(isLarge);
        this.opaque = false;
        this.itemBuilder = new CogwheelBlockItemBuilder(i);
        this.itemBuilder.blockBuilder = this;
    }

    @Override
    public Map<ResourceLocation, JsonObject> generateBlockModels(BlockBuilder builder) {
        Map<ResourceLocation, JsonObject> map = new HashMap<>();
        if (builder.modelJson != null) {
            map.put(builder.newID("models/block/", ""), builder.modelJson);
        } else {
            modelJson.addProperty("parent", parent);
            modelJson.add("textures", builder.textures);
            map.put(builder.newID("models/block/", ""), modelJson);
        }
        return map;
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        generator.blockState(this.id, bs -> {
            String mod = this.newID("block/", "").toString();
            var modelLocation = model.isEmpty() ? newID("block/", "").toString() : model;
            if (type != CogwheelType.HALF_SHAFT) {
                bs.variant("axis=x", v -> v.model(modelLocation).x(90).y(90));
                bs.variant("axis=y", v -> v.model(modelLocation));
                bs.variant("axis=z", v -> v.model(modelLocation).x(90).y(180));
            } else {
                bs.variant("axis=x,axis_direction=false", v -> v.model(modelLocation).x(90).y(270));
                bs.variant("axis=y,axis_direction=false", v -> v.model(modelLocation).x(180));
                bs.variant("axis=z,axis_direction=false", v -> v.model(modelLocation).x(90));
                bs.variant("axis=x,axis_direction=true", v -> v.model(modelLocation).x(90).y(90));
                bs.variant("axis=y,axis_direction=true", v -> v.model(modelLocation));
                bs.variant("axis=z,axis_direction=true", v -> v.model(modelLocation).x(90).y(180));
            }
        });

        if (this.modelJson != null) {
            generator.json(this.newID("models/block/", ""), this.modelJson);
        } else {
            generator.blockModel(this.id, m -> {
                String particle = this.textures.get("particle").getAsString();
                m.parent(parent);
                m.texture("particle", particle);
                m.texture("1_2", particle); // Overwrite (small)
                m.texture("4", particle); // Overwrite (large)
            });
        }

        if (this.itemBuilder != null) {
            generator.itemModel(this.itemBuilder.id, m -> {
                if (!this.model.isEmpty()) {
                    m.parent(this.model);
                } else {
                    m.parent(this.newID("block/", "").toString());
                }
            });
        }
    }

    @Override
    public Block createObject() {
        return switch (type) {
            case STANDARD -> new CogwheelBlockJS(this);
            case SHAFTLESS -> new ShaftlessCogwheelBlockJs(this);
            case HALF_SHAFT -> new HalfShaftCogwheelBlockJs(this);
        };
    }

    @Override
    public Block transformObject(Block obj) {
        if (obj instanceof CogwheelBlockJS)
            KubeJsCogwheels.addCogwheel(this);
        return super.transformObject(obj);
    }

    public static class HalfShaftCogwheelBlockJs extends CogwheelBlockJS {
        public VoxelShaper voxelShape = shapeBuilder(box(2.0D, 6.0D, 2.0D, 14.0D, 10.0D, 14.0D))
                .add(6.0D, 8, 6.0D, 10.0D, 16, 10.0D).forDirectional();
        public VoxelShaper largeVoxelShape = shapeBuilder(box(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D))
                .add(6.0D, 8, 6.0D, 10.0D, 16, 10.0D).forDirectional();


        public static final BooleanProperty AXIS_DIRECTION = BooleanProperty.create("axis_direction");

        public HalfShaftCogwheelBlockJs(KubeJsCogwheelBuilder builder) {
            super(builder);
            registerDefaultState(this.defaultBlockState().setValue(AXIS_DIRECTION,
                    isDirectionPosotive(Direction.AxisDirection.POSITIVE)));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(AXIS_DIRECTION);
            super.createBlockStateDefinition(builder);
        }

        @Override
        public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos,
                                            @NotNull CollisionContext context) {
            if (!state.hasProperty(AXIS))
                return super.getShape(state, worldIn, pos, context);
            Direction dir = Direction.fromAxisAndDirection(state.getValue(AXIS),
                    directionFromValue(state.getValue(AXIS_DIRECTION)));
            return isLargeCog() ? largeVoxelShape.get(dir) : voxelShape.get(dir);
        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            Direction direction = context.getClickedFace().getOpposite();
            boolean isDirectionPosotive = isDirectionPosotive(direction.getAxisDirection());
            Direction.Axis axisFromDirection = direction.getAxis();
            if (context.getPlayer() == null)
                return super.getStateForPlacement(context);
            return super.getStateForPlacement(context)
                    .setValue(AXIS_DIRECTION, context.getPlayer()
                            .isShiftKeyDown() != isDirectionPosotive)
                    .setValue(AXIS, axisFromDirection);
        }

        public static Direction.AxisDirection directionFromValue(boolean bool) {
            return bool ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;
        }

        @Override
        public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
            return face.getAxis() == state.getValue(AXIS) && face.getAxisDirection() == directionFromValue(state.getValue(AXIS_DIRECTION));
        }

        public static AllShapes.Builder shapeBuilder(VoxelShape shape) {
            return new AllShapes.Builder(shape);
        }
    }

    public static class ShaftlessCogwheelBlockJs extends CogwheelBlockJS {
        public VoxelShape voxelShape = Block.box(2.0D, 6.0D, 2.0D, 14.0D, 10.0D, 14.0D);
        public VoxelShape largeVoxelShape = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D);

        public ShaftlessCogwheelBlockJs(KubeJsCogwheelBuilder builder) {
            super(builder);
        }

        @Override
        public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos,
                                            @NotNull CollisionContext context) {
            if (!state.hasProperty(AXIS))
                return super.getShape(state, worldIn, pos, context);
            return VoxelShaper.forAxis(isLargeCog() ? largeVoxelShape
                    : voxelShape, Direction.Axis.Y).get(state.getValue(AXIS));
        }

        @Override
        public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
            return false;
        }
    }

    public enum CogwheelType {
        STANDARD("cogwheel", "large_cogwheel"),
        SHAFTLESS("shaftless_cogwheel", "large_shaftless_cogwheel"),
        HALF_SHAFT("half_shaft_cogwheel", "large_half_shaft_cogwheel");

        public final String largeParentModel;
        public final String parentModel;

        CogwheelType(String parentModel, String largeParentModel) {
            this.parentModel = parentModel;
            this.largeParentModel = largeParentModel;
        }

        public String getParentModel(boolean isLarge) {
            return "extendedgears:block/base/" + (isLarge ? largeParentModel : parentModel);
        }
    }
}
