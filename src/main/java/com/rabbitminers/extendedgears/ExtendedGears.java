package com.rabbitminers.extendedgears;

import com.mojang.logging.LogUtils;
import com.rabbitminers.extendedgears.index.AddonBlocks;
import com.rabbitminers.extendedgears.index.AddonItems;
import com.rabbitminers.extendedgears.index.AddonTileEntities;
import com.rabbitminers.extendedgears.index.ECPartials;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(ExtendedGears.MODID)
public class ExtendedGears {
    public static final String MODID = "extendedgears";
    private static final NonNullSupplier<CreateRegistrate> registrate = CreateRegistrate.lazy(ExtendedGears.MODID);
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // TODO: Add new icon for your mod's icon group
    public static final CreativeModeTab itemGroup = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AddonBlocks.CRIMSON_COGWHEEL.get());
        }
    };

    public ExtendedGears()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                .getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        /*
         For adding simple kinetic blocks this is all you need but for fluids etc.
         see the Create GitHub repo -
         https://github.com/Creators-of-Create/Create/tree/mc1.18/dev/src/main/java/com/simibubi/create
         */

        AddonBlocks.register();
        AddonItems.register(modEventBus);
        AddonTileEntities.register();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> ECPartials::init);
    }

    private void setup(final FMLCommonSetupEvent event) {}

    public static CreateRegistrate registrate() {
        return registrate.get();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
