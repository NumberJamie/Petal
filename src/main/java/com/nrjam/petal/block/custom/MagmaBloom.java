package com.nrjam.petal.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class MagmaBloom extends PlantBlock {
    public static final MapCodec<MagmaBloom> CODEC = createCodec(MagmaBloom::new);
    private static final VoxelShape SHAPE = Block.createColumnShape(12.0, 0.0, 8.0);

    public MagmaBloom(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.NETHER_CARVER_REPLACEABLES) || floor.isOf(Blocks.MAGMA_BLOCK) || super.canPlantOnTop(floor, world, pos);
    }
}
