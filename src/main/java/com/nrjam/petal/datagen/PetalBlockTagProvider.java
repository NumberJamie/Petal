package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class PetalBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public PetalBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(PetalBlocks.MUDDY_FARMLAND)
                .add(PetalBlocks.NETHER_FARMLAND);

        valueLookupBuilder(BlockTags.DRY_VEGETATION_MAY_PLACE_ON)
                .add(PetalBlocks.MUDDY_FARMLAND);

        valueLookupBuilder(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                .add(PetalBlocks.MUDDY_FARMLAND)
                .add(PetalBlocks.NETHER_FARMLAND);

        valueLookupBuilder(BlockTags.MAINTAINS_FARMLAND)
                .add(PetalBlocks.TURNIPS);
    }
}
