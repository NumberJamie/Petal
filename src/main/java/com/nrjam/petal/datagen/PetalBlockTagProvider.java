package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlockIds;
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
        builder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(PetalBlockIds.MUDDY_FARMLAND)
                .add(PetalBlockIds.NETHER_FARMLAND)
                .add(PetalBlockIds.END_SOIL);

        builder(BlockTags.MINEABLE_WITH_AXE)
                .add(PetalBlockIds.HUGE_TURNIP);

        builder(BlockTags.SUPPORTS_DRY_VEGETATION)
                .add(PetalBlockIds.MUDDY_FARMLAND);

        builder(BlockTags.SUPPORTS_BIG_DRIPLEAF)
                .add(PetalBlockIds.MUDDY_FARMLAND)
                .add(PetalBlockIds.NETHER_FARMLAND);

        builder(BlockTags.MAINTAINS_FARMLAND)
                .add(PetalBlockIds.TURNIPS);
    }
}
