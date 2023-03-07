package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.recipe.Mods;
import com.simibubi.create.foundation.utility.Lang;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class ExtendedCogwheelsTags {

    private static final CreateRegistrate REGISTRATE = ExtendedCogwheels.registrate();

    public enum NameSpace {

        MOD(ExtendedCogwheels.MOD_ID, false, true), FORGE("forge")

        ;

        public final String id;
        public final boolean optionalDefault;
        public final boolean alwaysDatagenDefault;

        NameSpace(String id) {
            this(id, true, false);
        }

        NameSpace(String id, boolean optionalDefault, boolean alwaysDatagenDefault) {
            this.id = id;
            this.optionalDefault = optionalDefault;
            this.alwaysDatagenDefault = alwaysDatagenDefault;
        }

    }



    public enum ExtendedCogwheelsBlockTags {
        COGWHEEL, METAL_COGWHEEL, WOODEN_COGWHEEL, LARGE_COGWHEEL, SMALL_COGWHEEL
        ;

        public final TagKey<Block> tag;


        ExtendedCogwheelsBlockTags() {
            this(NameSpace.MOD);
        }

        ExtendedCogwheelsBlockTags(NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        ExtendedCogwheelsBlockTags(NameSpace namespace, String path) {
            this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        ExtendedCogwheelsBlockTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
            this(namespace, null, optional, alwaysDatagen);
        }

        ExtendedCogwheelsBlockTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
            ResourceLocation id = new ResourceLocation(namespace.id, path == null ? Lang.asId(name()) : path);
            tag = optionalTag(Registry.BLOCK, id);
            if (alwaysDatagen) {
                REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> builder(prov, tag));
            }
        }

        @SuppressWarnings("deprecation")
        public boolean matches(Block block) {
            return block.builtInRegistryHolder()
                    .is(tag);
        }

        public boolean matches(ItemStack stack) {
            return stack != null && stack.getItem() instanceof BlockItem blockItem && matches(blockItem.getBlock());
        }

        public boolean matches(BlockState state) {
            return state.is(tag);
        }

        public void add(Block... values) {
            REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> builder(prov, tag)
                    .add(values));
        }

        public void addOptional(Mods mod, String... ids) {
            REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> {
                TagsProvider.TagAppender<Block> builder = builder(prov, tag);
                for (String id : ids)
                    builder.addOptional(mod.asResource(id));
            });
        }

        public void addOptional(String namespace, String... ids) {
            REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> {
                TagsProvider.TagAppender<Block> builder = builder(prov, tag);
                for (String id : ids)
                    builder.addOptional(new ResourceLocation(namespace, id));
            });
        }

        public void addOptional(ResourceLocation location) {
            addOptional(location.getNamespace(), location.getPath());
        }

        public void includeIn(TagKey<Block> parent) {
            REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(parent)
                    .addTag(tag));
        }

        public void includeIn(AllTags.AllBlockTags parent) {
            includeIn(parent.tag);
        }

        public void includeAll(TagKey<Block> child) {
            REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> builder(prov, tag)
                    .addTag(child));
        }
    }

    public static <T> TagKey<T> optionalTag(Registry<T> registry, ResourceLocation id) {
        return TagKey.create(registry.key(), id);
    }

    public static void init() {
        ExtendedCogwheelsBlockTags.COGWHEEL.addOptional(AllBlocks.COGWHEEL.getId());
        ExtendedCogwheelsBlockTags.SMALL_COGWHEEL.addOptional(AllBlocks.COGWHEEL.getId());
        ExtendedCogwheelsBlockTags.WOODEN_COGWHEEL.addOptional(AllBlocks.COGWHEEL.getId());
    }

    public static TagKey<Item>
            forgeSteel = AllTags.forgeItemTag("ingots/steel"),
            fabricSteel = optionalTag(Registry.ITEM, new ResourceLocation("c", "steel_ingots"));
    ;
    @ExpectPlatform
    public static <T> TagsProvider.TagAppender<T> builder(RegistrateTagsProvider<T> prov, TagKey<T> tag) {
        throw new AssertionError();
    }
}
