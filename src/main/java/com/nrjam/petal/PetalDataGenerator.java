package com.nrjam.petal;

import com.nrjam.petal.datagen.*;
import com.nrjam.petal.worldgen.PetalConfiguredFeature;
import com.nrjam.petal.worldgen.PetalPlacedFeature;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class PetalDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(PetalEnglishLangProvider::new);
		pack.addProvider(PetalLootTableProvider::new);
		pack.addProvider(PetalModelProvider::new);
		pack.addProvider(PetalRecipeProvider::new);
		pack.addProvider(PetalBlockTagProvider::new);
		pack.addProvider(PetalRegistryDataGenerator::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, PetalConfiguredFeature::initialize);
		registryBuilder.add(Registries.PLACED_FEATURE, PetalPlacedFeature::initialize);
	}
}
