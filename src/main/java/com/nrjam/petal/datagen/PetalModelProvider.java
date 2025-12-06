package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.block.crop.MagmaBerriesBlock;
import com.nrjam.petal.block.crop.TurnipsBlock;
import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;

import static net.minecraft.client.data.BlockStateModelGenerator.createValueFencedModelMap;
import static net.minecraft.client.data.BlockStateModelGenerator.createWeightedVariant;

public class PetalModelProvider extends FabricModelProvider {
    public PetalModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerTintableCrossBlockState(PetalBlocks.DEAD_ROOTS, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerItemModel(PetalBlocks.DEAD_ROOTS);
        generator.registerTintableCrossBlockState(PetalBlocks.LAVA_ROOT, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerItemModel(PetalBlocks.LAVA_ROOT);
        generator.registerTintableCrossBlockState(PetalBlocks.MAGMA_BLOOM, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerItemModel(PetalBlocks.MAGMA_BLOOM);
        generator.registerSingleton(PetalBlocks.HUGE_TURNIP, TexturedModel.CUBE_BOTTOM_TOP);

        registerFarmland(Blocks.MUD, PetalBlocks.MUDDY_FARMLAND, generator);
        registerFarmland(Blocks.SOUL_SOIL, PetalBlocks.NETHER_FARMLAND, generator);
        generator.registerCrop(PetalBlocks.TURNIPS, TurnipsBlock.AGE, 0, 1, 2, 3);
        generator.registerTintableCrossBlockStateWithStages(PetalBlocks.MAGMA_BERRIES, BlockStateModelGenerator.CrossType.TINTED, MagmaBerriesBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(PetalItems.ROASTED_TURNIP, Models.GENERATED);
        generator.register(PetalItems.GLAZED_TURNIP, Models.GENERATED);
        generator.register(PetalItems.TURNIP_PIE, Models.GENERATED);

        generator.register(PetalItems.LAVA_FRUIT, Models.GENERATED);
        generator.register(PetalItems.BAKED_LAVA_FRUIT, Models.GENERATED);

        generator.register(PetalItems.FUGU, Models.GENERATED);
        generator.register(PetalItems.MOUSSE, Models.GENERATED);
    }

    private void registerFarmland(Block sideBlock, Block farmland, BlockStateModelGenerator generator) {
        TextureMap dryTextures = new TextureMap()
                .put(TextureKey.DIRT, TextureMap.getId(sideBlock))
                .put(TextureKey.TOP, TextureMap.getId(farmland));
        TextureMap moistTextures = new TextureMap()
                .put(TextureKey.DIRT, TextureMap.getId(sideBlock))
                .put(TextureKey.TOP, TextureMap.getSubId(farmland, "_moist"));
        WeightedVariant dryModel = createWeightedVariant(
                Models.TEMPLATE_FARMLAND.upload(farmland, dryTextures, generator.modelCollector)
        );
        WeightedVariant moistModel = createWeightedVariant(
                Models.TEMPLATE_FARMLAND.upload(TextureMap.getSubId(farmland, "_moist"), moistTextures, generator.modelCollector)
        );

        generator.blockStateCollector.accept(
                VariantsBlockModelDefinitionCreator.of(farmland)
                        .with(createValueFencedModelMap(Properties.MOISTURE, 7, moistModel, dryModel))
        );
    }
}
