package io.github.haykam821.extracauldrons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), Main.GLASS_CAULDRON);
		BlockRenderLayerMap.INSTANCE.putBlocks(
			RenderLayer.getTranslucent(),
			Main.WHITE_STAINED_GLASS_CAULDRON,
			Main.ORANGE_STAINED_GLASS_CAULDRON,
			Main.MAGENTA_STAINED_GLASS_CAULDRON,
			Main.LIGHT_BLUE_STAINED_GLASS_CAULDRON,
			Main.YELLOW_STAINED_GLASS_CAULDRON,
			Main.LIME_STAINED_GLASS_CAULDRON,
			Main.PINK_STAINED_GLASS_CAULDRON,
			Main.GRAY_STAINED_GLASS_CAULDRON,
			Main.LIGHT_GRAY_STAINED_GLASS_CAULDRON,
			Main.CYAN_STAINED_GLASS_CAULDRON,
			Main.PURPLE_STAINED_GLASS_CAULDRON,
			Main.BLUE_STAINED_GLASS_CAULDRON,
			Main.BROWN_STAINED_GLASS_CAULDRON,
			Main.GREEN_STAINED_GLASS_CAULDRON,
			Main.RED_STAINED_GLASS_CAULDRON,
			Main.BLACK_STAINED_GLASS_CAULDRON
		);
	}
}