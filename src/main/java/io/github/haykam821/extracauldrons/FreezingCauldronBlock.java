package io.github.haykam821.extracauldrons;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FreezingCauldronBlock extends CauldronBlock {
	public FreezingCauldronBlock(Settings settings) {
		super(settings);
	}

	public void setLevel(World world, BlockPos blockPos, BlockState blockState, int newLevel) {
		if (newLevel == 3) {
			world.getBlockTickScheduler().schedule(blockPos, blockState.getBlock(), 20);
		}
		super.setLevel(world, blockPos, blockState, newLevel);
	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if ((Integer) state.get(LEVEL) == 3) {
			ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5D, pos.getY() + 0.6D, pos.getZ() + 0.5D, new ItemStack(Items.ICE));
			itemEntity.setToDefaultPickupDelay();
			world.spawnEntity(itemEntity);

			this.setLevel(world, pos, state, 0);
			world.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		super.scheduledTick(state, world, pos, random);
	}
}