package io.github.haykam821.extracauldrons;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.traverse.block.TraverseBlocks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static Block GLASS_CAULDRON;
	public static Block WHITE_STAINED_GLASS_CAULDRON;
	public static Block ORANGE_STAINED_GLASS_CAULDRON;
	public static Block MAGENTA_STAINED_GLASS_CAULDRON;
	public static Block LIGHT_BLUE_STAINED_GLASS_CAULDRON;
	public static Block YELLOW_STAINED_GLASS_CAULDRON;
	public static Block LIME_STAINED_GLASS_CAULDRON;
	public static Block PINK_STAINED_GLASS_CAULDRON;
	public static Block GRAY_STAINED_GLASS_CAULDRON;
	public static Block LIGHT_GRAY_STAINED_GLASS_CAULDRON;
	public static Block CYAN_STAINED_GLASS_CAULDRON;
	public static Block PURPLE_STAINED_GLASS_CAULDRON;
	public static Block BLUE_STAINED_GLASS_CAULDRON;
	public static Block BROWN_STAINED_GLASS_CAULDRON;
	public static Block GREEN_STAINED_GLASS_CAULDRON;
	public static Block RED_STAINED_GLASS_CAULDRON;
	public static Block BLACK_STAINED_GLASS_CAULDRON;

	public Block registerSpecialCauldron(String type, Block block) {
		Identifier identifier = new Identifier("extracauldrons", type + "_cauldron");

		Registry.register(Registry.BLOCK, identifier, block);
		Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(ItemGroup.BREWING)));

		return block;
	}

	public Block registerSpecialWaterCauldron(String type, Block block) {
		registerSpecialCauldron(type, block);
		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
			if (view == null || pos == null)
				return -1;
			return BiomeColors.getWaterColor(view, pos);
		}, block);

		return block;
	}

	public Block registerCauldron(String type, Block base) {
		return registerSpecialWaterCauldron(type, new CauldronBlock(FabricBlockSettings.copy(base).build()));
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

	public void registerModIntergationCauldrons() {
		FabricLoader loader = FabricLoader.getInstance();

		// Terrestria
		if (loader.isModLoaded("terrestria")) {
			registerFlammableCauldron("redwood", TerrestriaBlocks.REDWOOD.planks);
			registerFlammableCauldron("hemlock", TerrestriaBlocks.HEMLOCK.planks);
			registerFlammableCauldron("rubber", TerrestriaBlocks.RUBBER.planks);
			registerFlammableCauldron("cypress", TerrestriaBlocks.CYPRESS.planks);
			registerFlammableCauldron("willow", TerrestriaBlocks.WILLOW.planks);
			registerFlammableCauldron("japanese_maple", TerrestriaBlocks.JAPANESE_MAPLE.planks);
			registerFlammableCauldron("rainbow_eucalyptus", TerrestriaBlocks.RAINBOW_EUCALYPTUS.planks);
			registerFlammableCauldron("sakura", TerrestriaBlocks.SAKURA.planks);
		}

		// Traverse
		if (loader.isModLoaded("traverse")) {
			registerFlammableCauldron("fir", TraverseBlocks.FIR_PLANKS);
		}

		// The Hallow
		if (loader.isModLoaded("thehallow")) {
			registerFlammableCauldron("deadwood", HallowedBlocks.DEADWOOD_PLANKS);
		}
	}

	@Override
	public void onInitialize() {
		// Stones
		registerCauldron("stone");
		registerCauldron("cobblestone");

		// Stone variants
		registerCauldron("granite", Blocks.GRANITE);
		registerCauldron("polished_granite", Blocks.POLISHED_GRANITE);
		registerCauldron("diorite", Blocks.DIORITE);
		registerCauldron("polished_diorite", Blocks.POLISHED_DIORITE);
		registerCauldron("andesite", Blocks.ANDESITE);
		registerCauldron("polished_andesite", Blocks.POLISHED_ANDESITE);

		// Wood
		registerFlammableCauldron("oak", Blocks.OAK_PLANKS);
		registerFlammableCauldron("spruce", Blocks.SPRUCE_PLANKS);
		registerFlammableCauldron("birch", Blocks.BIRCH_PLANKS);
		registerFlammableCauldron("jungle", Blocks.JUNGLE_PLANKS);
		registerFlammableCauldron("acacia", Blocks.ACACIA_PLANKS);
		registerFlammableCauldron("dark_oak", Blocks.DARK_OAK_PLANKS);

		// Metals and gems
		registerSpecialCauldron("gold", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.GOLD_BLOCK).build()));
		registerCauldron("diamond", Blocks.DIAMOND_BLOCK);

		// Glass
		GLASS_CAULDRON = registerSpecialWaterCauldron("glass", new GlassCauldronBlock(FabricBlockSettings.copy(Blocks.GLASS).build()));
		WHITE_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("white_stained_glass", new StainedGlassCauldronBlock(DyeColor.WHITE, FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS).build()));
		ORANGE_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("orange_stained_glass", new StainedGlassCauldronBlock(DyeColor.ORANGE, FabricBlockSettings.copy(Blocks.ORANGE_STAINED_GLASS).build()));
		MAGENTA_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("magenta_stained_glass", new StainedGlassCauldronBlock(DyeColor.MAGENTA, FabricBlockSettings.copy(Blocks.MAGENTA_STAINED_GLASS).build()));
		LIGHT_BLUE_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("light_blue_stained_glass", new StainedGlassCauldronBlock(DyeColor.LIGHT_BLUE, FabricBlockSettings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS).build()));
		YELLOW_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("yellow_stained_glass", new StainedGlassCauldronBlock(DyeColor.YELLOW, FabricBlockSettings.copy(Blocks.YELLOW_STAINED_GLASS).build()));
		LIME_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("lime_stained_glass", new StainedGlassCauldronBlock(DyeColor.LIME, FabricBlockSettings.copy(Blocks.LIME_STAINED_GLASS).build()));
		PINK_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("pink_stained_glass", new StainedGlassCauldronBlock(DyeColor.PINK, FabricBlockSettings.copy(Blocks.PINK_STAINED_GLASS).build()));
		GRAY_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("gray_stained_glass", new StainedGlassCauldronBlock(DyeColor.GRAY, FabricBlockSettings.copy(Blocks.GRAY_STAINED_GLASS).build()));
		LIGHT_GRAY_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("light_gray_stained_glass", new StainedGlassCauldronBlock(DyeColor.LIGHT_GRAY, FabricBlockSettings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS).build()));
		CYAN_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("cyan_stained_glass", new StainedGlassCauldronBlock(DyeColor.CYAN, FabricBlockSettings.copy(Blocks.CYAN_STAINED_GLASS).build()));
		PURPLE_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("purple_stained_glass", new StainedGlassCauldronBlock(DyeColor.PURPLE, FabricBlockSettings.copy(Blocks.PURPLE_STAINED_GLASS).build()));
		BLUE_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("blue_stained_glass", new StainedGlassCauldronBlock(DyeColor.BLUE, FabricBlockSettings.copy(Blocks.BLUE_STAINED_GLASS).build()));
		BROWN_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("brown_stained_glass", new StainedGlassCauldronBlock(DyeColor.BROWN, FabricBlockSettings.copy(Blocks.BROWN_STAINED_GLASS).build()));
		GREEN_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("green_stained_glass", new StainedGlassCauldronBlock(DyeColor.GREEN, FabricBlockSettings.copy(Blocks.GREEN_STAINED_GLASS).build()));
		RED_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("red_stained_glass", new StainedGlassCauldronBlock(DyeColor.RED, FabricBlockSettings.copy(Blocks.RED_STAINED_GLASS).build()));
		BLACK_STAINED_GLASS_CAULDRON = registerSpecialWaterCauldron("black_stained_glass", new StainedGlassCauldronBlock(DyeColor.BLACK, FabricBlockSettings.copy(Blocks.BLACK_STAINED_GLASS).build()));

		// Miscellaneous
		registerSpecialCauldron("brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.BRICKS).build()));
		registerSpecialCauldron("obsidian", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).build()));
		registerSpecialCauldron("nether_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.NETHER_BRICKS).build()));
		registerSpecialCauldron("red_nether_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.RED_NETHER_BRICKS).build()));

		// Shulker
		registerSpecialWaterCauldron("shulker", new ShulkerCauldronBlock(FabricBlockSettings.copy(Blocks.SHULKER_BOX).build()));

		// Mod integrations
		registerModIntergationCauldrons();
	}
}