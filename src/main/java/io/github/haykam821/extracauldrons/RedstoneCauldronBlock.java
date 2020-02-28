package io.github.haykam821.extracauldrons;

import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RedstoneCauldronBlock extends CauldronBlock {
	public RedstoneCauldronBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void setLevel(World world, BlockPos pos, BlockState blockState, int newLevel) {
		super.setLevel(world, pos, blockState, newLevel);

		world.updateNeighborsAlways(pos, this);
		world.updateNeighborsAlways(pos.down(), this);
	}

	public boolean emitsRedstonePower(BlockState blockState) {
		return true;
	}

	public int getWeakRedstonePower(BlockState blockState, BlockView view, BlockPos pos, Direction direction) {
		return 12 + blockState.get(LEVEL);
	}

	public int getStrongRedstonePower(BlockState blockState, BlockView view, BlockPos pos, Direction direction) {
		return direction == Direction.UP ? this.getWeakRedstonePower(blockState, view, pos, direction) : 0;
	}
}