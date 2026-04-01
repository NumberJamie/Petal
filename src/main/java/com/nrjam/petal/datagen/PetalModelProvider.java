package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.block.crop.MagmaBerriesBlock;
import com.nrjam.petal.block.crop.TurnipsBlock;
import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PetalModelProvider extends FabricModelProvider {
    public PetalModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createCrossBlock(PetalBlocks.DEAD_ROOTS, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.registerSimpleFlatItemModel(PetalBlocks.DEAD_ROOTS);
        generator.createCrossBlock(PetalBlocks.LAVA_ROOT, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.registerSimpleFlatItemModel(PetalBlocks.LAVA_ROOT);
        generator.createCrossBlock(PetalBlocks.MAGMA_BLOOM, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.registerSimpleFlatItemModel(PetalBlocks.MAGMA_BLOOM);
        generator.createTrivialBlock(PetalBlocks.HUGE_TURNIP, TexturedModel.CUBE_TOP_BOTTOM);
        generator.createCrossBlock(PetalBlocks.TURNIP_GREENS, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.registerSimpleFlatItemModel(PetalBlocks.TURNIP_GREENS);

        registerFarmland(Blocks.MUD, PetalBlocks.MUDDY_FARMLAND, generator);
        registerFarmland(Blocks.SOUL_SOIL, PetalBlocks.NETHER_FARMLAND, generator);
        generator.createCropBlock(PetalBlocks.TURNIPS, TurnipsBlock.AGE, 0, 1, 2, 3);
        generator.createCrossBlock(PetalBlocks.MAGMA_BERRIES, BlockModelGenerators.PlantType.TINTED, MagmaBerriesBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(PetalItems.ROASTED_TURNIP, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(PetalItems.GLAZED_TURNIP, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(PetalItems.TURNIP_PIE, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(PetalItems.LAVA_FRUIT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(PetalItems.BAKED_LAVA_FRUIT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(PetalItems.FUGU, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(PetalItems.MOUSSE, ModelTemplates.FLAT_ITEM);
    }

    private void registerFarmland(Block sideBlock, Block farmland, BlockModelGenerators generator) {
        TextureMapping dryTextures = new TextureMapping()
                .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(sideBlock))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(farmland));
        TextureMapping moistTextures = new TextureMapping()
                .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(sideBlock))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(farmland, "_moist"));
        Identifier dryModelId = ModelTemplates.FARMLAND.create(farmland, dryTextures, generator.modelOutput);
        Identifier moistModelId = ModelTemplates.FARMLAND.createWithSuffix(farmland, "_moist", moistTextures, generator.modelOutput);

        generator.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(farmland, BlockModelGenerators.plainVariant(dryModelId))
        );
    }
}
