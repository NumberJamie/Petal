package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
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
        registerFarmland(Blocks.MUD, PetalBlocks.MUDDY_FARMLAND, generator);
        generator.registerCrop(PetalBlocks.TURNIPS, TurnipsBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(PetalItems.ROASTED_TURNIP, Models.GENERATED);
        itemModelGenerator.register(PetalItems.GLAZED_TURNIP, Models.GENERATED);
        itemModelGenerator.register(PetalItems.TURNIP_PIE, Models.GENERATED);

        itemModelGenerator.register(PetalItems.LAVA_FRUIT, Models.GENERATED);

        itemModelGenerator.register(PetalItems.FUGU, Models.GENERATED);
        itemModelGenerator.register(PetalItems.MOUSSE, Models.GENERATED);
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
