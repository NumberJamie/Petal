package com.nrjam.petal;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.PetalItems;
import com.nrjam.petal.util.PetalComposting;
import com.nrjam.petal.util.PetalLootModifiers;
import com.nrjam.petal.worldgen.PetalWorldGeneration;
import net.fabricmc.api.ModInitializer;


public class Petal implements ModInitializer {
	public static final String MOD_ID = "petal";

	@Override
	public void onInitialize() {
		PetalItems.initialize();
		PetalBlocks.initialize();
		PetalComposting.initialize();
		PetalLootModifiers.initialize();
		PetalWorldGeneration.initialize();
	}
}
