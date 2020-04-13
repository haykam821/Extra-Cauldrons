package io.github.haykam821.extracauldrons;

import com.brand.adabraniummod.content.ModBlocks;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.traverse.block.TraverseBlocks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
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
import io.github.cornflower.block.CornflowerBlocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import techreborn.init.TRContent;
import techreborn.init.TRContent.StorageBlocks;

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
	public static Block SOUL_GLASS_CAULDRON;

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
			// Wood
			registerFlammableCauldron("redwood", TerrestriaBlocks.REDWOOD.planks);
			registerFlammableCauldron("hemlock", TerrestriaBlocks.HEMLOCK.planks);
			registerFlammableCauldron("rubber", TerrestriaBlocks.RUBBER.planks);
			registerFlammableCauldron("cypress", TerrestriaBlocks.CYPRESS.planks);
			registerFlammableCauldron("willow", TerrestriaBlocks.WILLOW.planks);
			registerFlammableCauldron("japanese_maple", TerrestriaBlocks.JAPANESE_MAPLE.planks);
			registerFlammableCauldron("rainbow_eucalyptus", TerrestriaBlocks.RAINBOW_EUCALYPTUS.planks);
			registerFlammableCauldron("sakura", TerrestriaBlocks.SAKURA.planks);

			// Basalt stones
			registerSpecialCauldron("basalt", new LavaCauldronBlock(FabricBlockSettings.copy(TerrestriaBlocks.BASALT.plain.full).build()));
			registerSpecialCauldron("basalt_cobblestone", new LavaCauldronBlock(FabricBlockSettings.copy(TerrestriaBlocks.BASALT.cobblestone.full).build()));
			registerSpecialCauldron("mossy_basalt_cobblestone", new LavaCauldronBlock(FabricBlockSettings.copy(TerrestriaBlocks.BASALT.mossyCobblestone.full).build()));

			// Basalt bricks
			registerSpecialCauldron("basalt_brick", new LavaCauldronBlock(FabricBlockSettings.copy(TerrestriaBlocks.BASALT.bricks.full).build()));
			registerSpecialCauldron("mossy_basalt_brick", new LavaCauldronBlock(FabricBlockSettings.copy(TerrestriaBlocks.BASALT.mossyBricks.full).build()));
		}

		// Traverse
		if (loader.isModLoaded("traverse")) {
			registerFlammableCauldron("fir", TraverseBlocks.FIR_PLANKS);
		}

		// The Hallow
		if (loader.isModLoaded("thehallow")) {
			// Wood
			registerFlammableCauldron("deadwood", HallowedBlocks.DEADWOOD_PLANKS);

			// Tainted stones
			registerCauldron("tainted_stone", HallowedBlocks.TAINTED_STONE);
			registerCauldron("tainted_cobblestone", HallowedBlocks.TAINTED_COBBLESTONE);

			// Tainted stone bricks
			registerCauldron("tainted_stone_brick", HallowedBlocks.TAINTED_STONE_BRICKS);

			// Glass
			SOUL_GLASS_CAULDRON = registerSpecialWaterCauldron("soul_glass", new GlassCauldronBlock(FabricBlockSettings.copy(HallowedBlocks.SOUL_GLASS).build()));

			// Sandstone
			registerCauldron("tainted_sandstone", HallowedBlocks.TAINTED_SANDSTONE);
		}

		// Tech Reborn
		if (loader.isModLoaded("techreborn")) {
			// Wood
			registerFlammableCauldron("reborn_rubber", TRContent.RUBBER_PLANKS);

			// Gem storage blocks
			registerCauldron("peridot", StorageBlocks.PERIDOT.block);
			registerCauldron("ruby", StorageBlocks.RUBY.block);
			registerCauldron("sapphire", StorageBlocks.SAPPHIRE.block);
			registerCauldron("red_garnet", StorageBlocks.RED_GARNET.block);
			registerCauldron("yellow_garnet", StorageBlocks.YELLOW_GARNET.block);

			// Metal storage blocks
			registerCauldron("zinc", StorageBlocks.ZINC.block);
			registerCauldron("aluminum", StorageBlocks.ALUMINUM.block);
			registerCauldron("brass", StorageBlocks.BRASS.block);
			registerCauldron("bronze", StorageBlocks.BRONZE.block);
			registerCauldron("copper", StorageBlocks.COPPER.block);
			registerCauldron("silver", StorageBlocks.SILVER.block);
			registerCauldron("electrum", StorageBlocks.ELECTRUM.block);
			registerCauldron("invar", StorageBlocks.INVAR.block);
			registerCauldron("nickel", StorageBlocks.NICKEL.block);
			registerCauldron("lead", StorageBlocks.LEAD.block);
			registerCauldron("platinum", StorageBlocks.PLATINUM.block);
			registerCauldron("refined_iron", StorageBlocks.REFINED_IRON.block);
			registerCauldron("steel", StorageBlocks.STEEL.block);
			registerCauldron("tin", StorageBlocks.TIN.block);

			// Refractory metal storage blocks
			registerSpecialCauldron("tungsten", new LavaCauldronBlock(FabricBlockSettings.copy(StorageBlocks.TUNGSTEN.block).build()));
			registerSpecialCauldron("tungstensteel", new LavaCauldronBlock(FabricBlockSettings.copy(StorageBlocks.TUNGSTENSTEEL.block).build()));
			registerSpecialCauldron("iridium", new LavaCauldronBlock(FabricBlockSettings.copy(StorageBlocks.IRIDIUM.block).build()));
			registerSpecialCauldron("chrome", new LavaCauldronBlock(FabricBlockSettings.copy(StorageBlocks.CHROME.block).build()));
			registerSpecialCauldron("titanium", new LavaCauldronBlock(FabricBlockSettings.copy(StorageBlocks.TITANIUM.block).build()));
		}

		// NonCorrelatedExtras
		if (loader.isModLoaded("noncorrelatedextras")) {
			registerCauldron("polarized_iron", Registry.BLOCK.get(new Identifier("noncorrelatedextras", "polarized_iron_block")));
		}
		
		// Adabranium
		if (loader.isModLoaded("adabraniummod")) {
			registerCauldron("vibranium", ModBlocks.VIBRANIUM_BLOCK);
			registerCauldron("adamantium", ModBlocks.ADAMANTIUM_BLOCK);
		}

		if (loader.isModLoaded("cornflower")) {
			registerCauldron("timeworn_brick", CornflowerBlocks.TIMEWORN_BRICK);
		}
	}

	@Override
	public void onInitialize() {
		// Stones
		registerCauldron("stone");
		registerCauldron("cobblestone");
		registerCauldron("mossy_cobblestone", Blocks.MOSSY_COBBLESTONE);

		// Stone variants
		registerCauldron("granite", Blocks.GRANITE);
		registerCauldron("polished_granite", Blocks.POLISHED_GRANITE);
		registerCauldron("diorite", Blocks.DIORITE);
		registerCauldron("polished_diorite", Blocks.POLISHED_DIORITE);
		registerCauldron("andesite", Blocks.ANDESITE);
		registerCauldron("polished_andesite", Blocks.POLISHED_ANDESITE);

		// Stone bricks
		registerCauldron("stone_brick", Blocks.STONE_BRICKS);
		registerCauldron("mossy_stone_brick", Blocks.MOSSY_STONE_BRICKS);

		// Sandstone
		registerCauldron("sandstone", Blocks.SANDSTONE);
		registerCauldron("red_sandstone", Blocks.RED_SANDSTONE);

		// Wood
		registerFlammableCauldron("oak", Blocks.OAK_PLANKS);
		registerFlammableCauldron("spruce", Blocks.SPRUCE_PLANKS);
		registerFlammableCauldron("birch", Blocks.BIRCH_PLANKS);
		registerFlammableCauldron("jungle", Blocks.JUNGLE_PLANKS);
		registerFlammableCauldron("acacia", Blocks.ACACIA_PLANKS);
		registerFlammableCauldron("dark_oak", Blocks.DARK_OAK_PLANKS);
		FuelRegistry.INSTANCE.add(TagRegistry.item(new Identifier("extracauldrons", "wooden_cauldrons")), 300);

		// Metals and gems
		registerSpecialCauldron("gold", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.GOLD_BLOCK).build()));
		registerCauldron("diamond", Blocks.DIAMOND_BLOCK);
		registerCauldron("emerald", Blocks.EMERALD_BLOCK);

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

		// Coal
		FuelRegistry.INSTANCE.add(registerCauldron("coal", Blocks.COAL_BLOCK), 6400);

		// Miscellaneous
		registerCauldron("purpur", Blocks.PURPUR_BLOCK);
		registerCauldron("honeycomb", Blocks.HONEYCOMB_BLOCK);
		registerCauldron("quartz", Blocks.QUARTZ_BLOCK);
		registerCauldron("lapis", Blocks.LAPIS_BLOCK);
		registerSpecialWaterCauldron("redstone", new RedstoneCauldronBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build()));
		registerSpecialWaterCauldron("freezing", new FreezingCauldronBlock(FabricBlockSettings.copy(Blocks.BLUE_ICE).build()));
		registerSpecialCauldron("brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.BRICKS).build()));
		registerSpecialCauldron("obsidian", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).build()));
		registerSpecialCauldron("nether_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.NETHER_BRICKS).build()));
		registerSpecialCauldron("red_nether_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.RED_NETHER_BRICKS).build()));
		registerSpecialCauldron("end_stone_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS).build()));

		// Shulker
		registerSpecialWaterCauldron("shulker", new ShulkerCauldronBlock(FabricBlockSettings.copy(Blocks.SHULKER_BOX).build()));

		// Mod integrations
		registerModIntergationCauldrons();
	}
}