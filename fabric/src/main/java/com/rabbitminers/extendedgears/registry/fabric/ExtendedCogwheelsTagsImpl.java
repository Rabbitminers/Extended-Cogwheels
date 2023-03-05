package com.rabbitminers.extendedgears.registry.fabric;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;

public class ExtendedCogwheelsTagsImpl {

    public static <T> TagsProvider.TagAppender<T> builder(RegistrateTagsProvider<T> prov, TagKey<T> tag) {
        return prov.tag(tag);
    }
}
