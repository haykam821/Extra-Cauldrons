package io.github.haykam821.extracauldrons;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassCauldronBlock extends GlassCauldronBlock implements Stainable {
	private final DyeColor dyeColor;

	public StainedGlassCauldronBlock(DyeColor dyeColor, Settings settings) {
		super(settings);
		this.dyeColor = dyeColor;
	}

	public DyeColor getColor() {
		return this.dyeColor;
	}
}