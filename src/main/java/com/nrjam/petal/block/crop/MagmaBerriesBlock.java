package com.nrjam.petal.block.crop;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.PetalItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class MagmaBerriesBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPES_BY_AGE = Block.boxes(4, age -> Block.column(16.0, 0.0, 2 + age));

    public MagmaBerriesBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected @NonNull ItemLike getBaseSeedId() {
        return PetalItems.MAGMA_BERRY;
    }

    @Override
    public @NonNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void randomTick(@NonNull BlockState state, @NonNull ServerLevel world, @NonNull BlockPos pos, RandomSource random) {
        if (random.nextInt(3) != 0) {
            super.randomTick(state, world, pos, random);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(@NonNull Level world) {
        return super.getBonemealAgeIncrease(world) / 3;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter world, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPES_BY_AGE[this.getAge(state)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, @NonNull BlockGetter world, @NonNull BlockPos pos) {
        return floor.is(PetalBlocks.NETHER_FARMLAND);
    }
}
