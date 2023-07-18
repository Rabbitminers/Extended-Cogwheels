package com.rabbitminers.extendedgears.mixin_interface;

import com.jozufozu.flywheel.core.StitchedSprite;
import com.rabbitminers.extendedgears.cogwheels.CogwheelModelKey;
import com.rabbitminers.extendedgears.registry.ExtendedCogwheelsPartials;
import com.simibubi.create.foundation.model.BakedModelHelper;
import com.simibubi.create.foundation.render.SuperByteBufferCache.Compartment;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DynamicCogwheelRenderer {
    public static final Compartment<CogwheelModelKey> COGWHEEL = new Compartment<>();

    public static final StitchedSprite STRIPPED_LOG_TEMPLATE = new StitchedSprite(new ResourceLocation("block/stripped_spruce_log"));
    public static final StitchedSprite STRIPPED_LOG_TOP_TEMPLATE = new StitchedSprite(new ResourceLocation("block/stripped_spruce_log_top"));

    public static final String[] LOG_SUFFIXES = new String[] { "_log", "_stem" };

    @Nullable
    public static BakedModel generateModel(CogwheelModelKey key) {
        return generateModel(key.large() ? ExtendedCogwheelsPartials.LARGE_SHAFTLESS_COGWHEEL.get()
                : ExtendedCogwheelsPartials.SHAFTLESS_COGWHEEL.get(), key.material());
    }

    public static BakedModel generateModel(BakedModel template, BlockState planksBlockState) {
        Block planksBlock = planksBlockState.getBlock();
        ResourceLocation id = RegisteredObjects.getKeyOrThrow(planksBlock);
        String path = id.getPath();

        if (path.endsWith("_planks")) {
            String namespace = id.getNamespace();
            String wood = path.substring(0, path.length() - 7);
            BlockState logBlockState = getStrippedLogState(namespace, wood);

            Map<TextureAtlasSprite, TextureAtlasSprite> map = new Reference2ReferenceOpenHashMap<>();

            map.put(STRIPPED_LOG_TEMPLATE.get(), getSpriteOnSide(logBlockState, Direction.SOUTH));
            map.put(STRIPPED_LOG_TOP_TEMPLATE.get(), getSpriteOnSide(logBlockState, Direction.UP));

            return BakedModelHelper.generateModel(template, map::get);
        }

        return BakedModelHelper.generateModel(template, sprite -> null);
    }

    public static BlockState getStrippedLogState(String namespace, String wood) {
        for (String suffix : LOG_SUFFIXES) {
            Optional<BlockState> state =
                    Registry.BLOCK.getHolder(ResourceKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(namespace,  "stripped_" + wood + suffix)))
                            .map(Holder::value)
                            .map(Block::defaultBlockState);
            if (state.isPresent())
                return state.get();
        }
        return Blocks.OAK_LOG.defaultBlockState();
    }

    public static TextureAtlasSprite getSpriteOnSide(BlockState state, Direction side) {
        BakedModel model = Minecraft.getInstance()
                .getBlockRenderer()
                .getBlockModel(state);
        if (model == null)
            return null;
        Random random = new Random(42L);
        List<BakedQuad> quads = model.getQuads(state, side, random);
        if (!quads.isEmpty()) {
            return quads.get(0)
                    .getSprite();
        }
        random.setSeed(42L);
        quads = model.getQuads(state, null, random);
        if (!quads.isEmpty()) {
            for (BakedQuad quad : quads) {
                if (quad.getDirection() == side) {
                    return quad.getSprite();
                }
            }
        }
        return model.getParticleIcon();
    }
}
