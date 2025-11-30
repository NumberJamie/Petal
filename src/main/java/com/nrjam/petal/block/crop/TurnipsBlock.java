package com.nrjam.petal.block.crop;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.PetalItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TurnipsBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    private static final VoxelShape[] SHAPES_BY_AGE = Block.createShapeArray(7, age -> Block.createColumnShape(16.0, 0.0, 2 + age * 2.5));

    public TurnipsBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return PetalItems.TURNIP;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(3) != 0) {
            if (this.getAge(state) + 1 >= MAX_AGE && random.nextInt(256) == 0) {
                world.setBlockState(pos, PetalBlocks.HUGE_TURNIP.getDefaultState(), Block.NOTIFY_LISTENERS);
            } else {
                super.randomTick(state, world, pos, random);
            }
        }
    }

    @Override
    protected int getGrowthAmount(World world) {
        return super.getGrowthAmount(world) / 3;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES_BY_AGE[this.getAge(state)];
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
