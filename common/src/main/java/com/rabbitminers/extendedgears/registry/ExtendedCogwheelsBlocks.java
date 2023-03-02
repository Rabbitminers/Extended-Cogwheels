package com.rabbitminers.extendedgears.registry;

import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

public class ExtendedCogwheelsBlocks {
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ExtendedCogwheels.MOD_ID);

	public static final BlockEntry<Block> EXAMPLE_BLOCK = REGISTRATE.block("example_block", Block::new).register();

	public static void init() {
		ExtendedCogwheels.LOGGER.info("Registering blocks for " + ExtendedCogwheels.NAME);
	}
}
