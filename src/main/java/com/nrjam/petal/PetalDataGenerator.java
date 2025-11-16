package com.nrjam.petal;

import com.nrjam.petal.datagen.*;
import com.nrjam.petal.worldgen.PetalConfiguredFeature;
import com.nrjam.petal.worldgen.PetalPlacedFeature;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

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
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, PetalConfiguredFeature::initialize);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PetalPlacedFeature::initialize);
	}
}
