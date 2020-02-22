package io.github.haykam821.extracauldrons;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static Block GLASS_CAULDRON;

	public Block registerSpecialCauldron(String type, Block block) {
		Identifier identifier = new Identifier("extracauldrons", type + "_cauldron");

		Registry.register(Registry.BLOCK, identifier, block);
		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
			if (view == null || pos == null) return -1;
			return BiomeColors.getWaterColor(view, pos);
		}, block);

		Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(ItemGroup.BREWING)));

		return block;
	}

	public Block registerCauldron(String type, Block base) {
		return registerSpecialCauldron(type, new CauldronBlock(FabricBlockSettings.copy(base).build()));
	}

	public Block registerCauldron(String type) {
		return registerCauldron(type, Blocks.CAULDRON);
	}

	public Block registerFlammableCauldron(String type, Block base) {
		Block block = registerCauldron(type, base);
		((FireBlock) Blocks.FIRE).registerFlammableBlock(block, 5, 20);
		return block;
	}

	public Block registerFlammableCauldron(String type) {
		return registerFlammableCauldron(type, Blocks.CAULDRON);
	}

	@Override
	public void onInitialize() {
		// Stones
		registerCauldron("cobblestone");
		registerCauldron("stone");

		// Stone variants
		registerCauldron("andesite", Blocks.ANDESITE);
		registerCauldron("polished_andesite", Blocks.POLISHED_ANDESITE);
		registerCauldron("diorite", Blocks.DIORITE);
		registerCauldron("polished_diorite", Blocks.POLISHED_DIORITE);
		registerCauldron("granite", Blocks.GRANITE);
		registerCauldron("polished_granite", Blocks.POLISHED_GRANITE);

		// Wood
		registerFlammableCauldron("acacia", Blocks.ACACIA_PLANKS);
		registerFlammableCauldron("dark_oak", Blocks.DARK_OAK_PLANKS);
		registerFlammableCauldron("birch", Blocks.BIRCH_PLANKS);
		registerFlammableCauldron("jungle", Blocks.JUNGLE_PLANKS);
		registerFlammableCauldron("oak", Blocks.OAK_PLANKS);
		registerFlammableCauldron("spruce", Blocks.SPRUCE_PLANKS);

		// Metals and gems
		registerCauldron("gold", Blocks.GOLD_BLOCK);
		registerCauldron("diamond", Blocks.DIAMOND_BLOCK);

		// Miscellaneous
		registerCauldron("obsidian", Blocks.OBSIDIAN);
		registerCauldron("bricks", Blocks.BRICKS);
		GLASS_CAULDRON = registerSpecialCauldron("glass", new GlassCauldronBlock(FabricBlockSettings.copy(Blocks.GLASS).build()));
	}
}