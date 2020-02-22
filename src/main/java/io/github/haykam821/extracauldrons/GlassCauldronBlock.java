package io.github.haykam821.extracauldrons;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class GlassCauldronBlock extends CauldronBlock {
	public GlassCauldronBlock(Settings settings) {
		super(settings);
	}

	public boolean isTranslucent(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return true;
	}
}