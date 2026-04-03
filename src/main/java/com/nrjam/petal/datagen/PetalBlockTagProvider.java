package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class PetalBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public PetalBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(PetalBlocks.MUDDY_FARMLAND)
                .add(PetalBlocks.NETHER_FARMLAND)
                .add(PetalBlocks.END_SOIL);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(PetalBlocks.HUGE_TURNIP);

        valueLookupBuilder(BlockTags.SUPPORTS_DRY_VEGETATION)
                .add(PetalBlocks.MUDDY_FARMLAND);

        valueLookupBuilder(BlockTags.SUPPORTS_BIG_DRIPLEAF)
                .add(PetalBlocks.MUDDY_FARMLAND)
                .add(PetalBlocks.NETHER_FARMLAND);

        valueLookupBuilder(BlockTags.MAINTAINS_FARMLAND)
                .add(PetalBlocks.TURNIPS);
    }
}
