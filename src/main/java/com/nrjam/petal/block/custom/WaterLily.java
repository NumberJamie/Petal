package com.nrjam.petal.block.custom;

import com.nrjam.petal.block.PetalBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LilyPadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

public class WaterLily extends LilyPadBlock {
    public WaterLily(Properties settings) {
        super(settings);
    }

    @Override
    protected @NonNull InteractionResult useItemOn(ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (stack.is(Items.SHEARS)) {
            if (!level.isClientSide()) {
                level.setBlock(pos, PetalBlocks.WATER_LILY_PAD.defaultBlockState(), Block.UPDATE_ALL);
                Block.popResource(level, pos, new ItemStack(Items.SPORE_BLOSSOM));
                stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                level.gameEvent(player, GameEvent.SHEAR, pos);
            }
            level.playSound(player, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
