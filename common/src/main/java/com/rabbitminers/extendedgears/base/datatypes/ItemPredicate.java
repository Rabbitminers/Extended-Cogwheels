package com.rabbitminers.extendedgears.base.datatypes;

import com.simibubi.create.foundation.data.recipe.StandardRecipeGen;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public sealed interface ItemPredicate extends Predicate<ItemStack> {
    final class ItemWrapper implements ItemPredicate {
        private final Item item;

        public ItemWrapper(Item item) {
            this.item = item;
        }
        @Override
        public boolean test(ItemStack stack) {
            return stack.is(item);
        }
    }

    final class ItemEntryWrapper implements ItemPredicate {
        private final ItemEntry<?> item;

        public ItemEntryWrapper(ItemEntry<?> item) {
            this.item = item;
        }
        @Override
        public boolean test(ItemStack stack) {
            return stack.is(item.get());
        }
    }

    final class TagWrapper implements ItemPredicate {
        private final TagKey<Item> tag;

        public TagWrapper(TagKey<Item> tag) {
            this.tag = tag;
        }
        @Override
        public boolean test(ItemStack stack) {
            return stack.is(tag);
        }
    }

    public static ItemPredicate of(Item item) {
        return new ItemWrapper(item);
    }

    public static ItemPredicate of(ItemEntry<?> entry) {
        return new ItemEntryWrapper(entry);
    }

    public static ItemPredicate of(TagKey<Item> tag) {
        return new TagWrapper(tag);
    }
}
