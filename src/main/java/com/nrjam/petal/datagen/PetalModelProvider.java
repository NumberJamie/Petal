package com.nrjam.petal.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.block.crop.MagmaBerriesBlock;
import com.nrjam.petal.block.crop.TurnipsBlock;
import com.nrjam.petal.item.PetalItems;
import com.mojang.math.Quadrant;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.core.registries.BuiltInRegistries;
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

        registerWaterLilyPadModel(generator);
        registerWaterLilyModel(generator);

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

    private void registerLilyPadBlockState(Block block, BlockModelGenerators generator, boolean flatItemModel) {
        Identifier blockId = BuiltInRegistries.BLOCK.getKey(block);
        Identifier modelId = blockId.withPrefix("block/");
        Variant base = new Variant(modelId);
        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block, new MultiVariant(WeightedList.<Variant>builder()
                        .add(base)
                        .add(base.withYRot(Quadrant.R90))
                        .add(base.withYRot(Quadrant.R180))
                        .add(base.withYRot(Quadrant.R270))
                        .build()))
        );
        if (flatItemModel) {
            generator.registerSimpleFlatItemModel(block);
        } else {
            generator.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(modelId));
        }
    }

    private void registerWaterLilyPadModel(BlockModelGenerators generator) {
        registerLilyPadBlockState(PetalBlocks.WATER_LILY_PAD, generator, true);
        Identifier modelId = Identifier.fromNamespaceAndPath("petal", "block/water_lily_pad");
        String texture = "petal:block/water_lily_pad";
        JsonObject model = new JsonObject();
        model.addProperty("ambientocclusion", false);
        JsonObject textures = new JsonObject();
        textures.addProperty("particle", texture);
        textures.addProperty("texture", texture);
        model.add("textures", textures);
        JsonArray elements = new JsonArray();
        elements.add(flatElement(0, 0.25, 0, 16, 0.25, 16, "#texture", true));
        model.add("elements", elements);
        generator.modelOutput.accept(modelId, () -> model);
    }

    private void registerWaterLilyModel(BlockModelGenerators generator) {
        registerLilyPadBlockState(PetalBlocks.WATER_LILY, generator, false);
        Identifier modelId = Identifier.fromNamespaceAndPath("petal", "block/water_lily");
        String flower = "petal:block/water_lily";
        String base = "petal:block/water_lily_base";
        JsonObject model = new JsonObject();
        model.addProperty("ambientocclusion", false);
        model.addProperty("parent", "minecraft:block/block");
        model.addProperty("render_type", "minecraft:cutout");
        JsonObject textures = new JsonObject();
        textures.addProperty("particle", flower);
        textures.addProperty("flower", flower);
        textures.addProperty("base", base);
        model.add("textures", textures);
        JsonObject display = new JsonObject();
        display.add("thirdperson_righthand", displayEntry(jsonArray(75, 45, 0), jsonArray(0, 2.5, 2), jsonArray(0.4, 0.4, 0.4)));
        display.add("firstperson_righthand", displayEntry(jsonArray(0, 45, 0), jsonArray(0, 4, 0), jsonArray(0.40, 0.40, 0.40)));
        display.add("firstperson_lefthand", displayEntry(jsonArray(0, 225, 0), jsonArray(0, 4, 0), jsonArray(0.40, 0.40, 0.40)));
        display.add("gui", displayEntry(jsonArray(30, 225, 0), jsonArray(0, 3, 0), jsonArray(0.625, 0.625, 0.625)));
        display.add("ground", displayEntry(jsonArray(0, 0, 0), jsonArray(0, 5, 0), jsonArray(0.25, 0.25, 0.25)));
        display.add("fixed", displayEntry(jsonArray(0, 0, 0), jsonArray(0, 3, 0), jsonArray(0.5, 0.5, 0.5)));
        model.add("display", display);
        JsonArray elements = new JsonArray();
        elements.add(flatElement(0, 0.1, 0, 16, 0.1, 16, "#base", false));
        elements.add(rotatedPetal(4, -1, 12, 7, "x", 22.5, 8, 0, 7, false, false, 0));
        elements.add(rotatedPetal(4, 9, 12, 17, "x", -22.5, 8, 0, 9, true, true, 0));
        elements.add(rotatedPetal(-1, 4, 7, 12, "z", -22.5, 7, 0, 8, true, true, 90));
        elements.add(rotatedPetal(9, 4, 17, 12, "z", 22.5, 9, 0, 8, true, true, 270));
        model.add("elements", elements);
        generator.modelOutput.accept(modelId, () -> model);
    }

    private static JsonObject flatElement(double x1, double y1, double z1, double x2, double y2, double z2, String texture, boolean tinted) {
        JsonObject element = new JsonObject();
        element.add("from", jsonArray(x1, y1, z1));
        element.add("to", jsonArray(x2, y2, z2));
        element.addProperty("shade", false);
        JsonObject faces = new JsonObject();
        faces.add("up", face(0, 0, 16, 16, texture, tinted ? 0 : -1, 0));
        faces.add("down", face(0, tinted ? 16 : 0, 16, tinted ? 0 : 16, texture, tinted ? 0 : -1, 0));
        element.add("faces", faces);
        return element;
    }

    private static JsonObject rotatedPetal(double x1, double z1, double x2, double z2, String axis, double angle, double ox, double oy, double oz, boolean flipU, boolean flipV, int rotation) {
        JsonObject element = new JsonObject();
        element.add("from", jsonArray(x1, 0.1, z1));
        element.add("to", jsonArray(x2, 0.1, z2));
        JsonObject rot = new JsonObject();
        rot.addProperty("angle", angle);
        rot.addProperty("axis", axis);
        rot.add("origin", jsonArray(ox, oy, oz));
        rot.addProperty("rescale", false);
        element.add("rotation", rot);
        element.addProperty("shade", false);
        JsonObject faces = new JsonObject();
        double u1 = flipU ? 16 : 0, v1 = flipV ? 16 : 0, u2 = flipU ? 0 : 16, v2 = flipV ? 0 : 16;
        faces.add("up", face(u1, v1, u2, v2, "#flower", -1, rotation));
        faces.add("down", face(u1, flipV ? 0 : 16, u2, flipV ? 16 : 0, "#flower", -1, rotation == 0 ? 0 : (rotation == 90 ? 270 : 90)));
        element.add("faces", faces);
        return element;
    }

    private static JsonObject face(double u1, double v1, double u2, double v2, String texture, int tintindex, int rotation) {
        JsonObject face = new JsonObject();
        face.add("uv", jsonArray(u1, v1, u2, v2));
        face.addProperty("texture", texture);
        if (tintindex >= 0) face.addProperty("tintindex", tintindex);
        if (rotation != 0) face.addProperty("rotation", rotation);
        return face;
    }

    private static JsonObject displayEntry(JsonArray rotation, JsonArray translation, JsonArray scale) {
        JsonObject entry = new JsonObject();
        entry.add("rotation", rotation);
        entry.add("translation", translation);
        entry.add("scale", scale);
        return entry;
    }

    private static JsonArray jsonArray(double... values) {
        JsonArray arr = new JsonArray();
        for (double v : values) arr.add(v);
        return arr;
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
