package io.github.haykam821.extracauldrons;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cornflower.block.CornflowerBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static final String MOD_ID = "extracauldrons";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static final Identifier FLAMMABLE_WOODEN_CAULDRONS_ID = new Identifier(MOD_ID, "flammable_wooden_cauldrons");
	public static final Tag<Block> FLAMMABLE_WOODEN_CAULDRON_BLOCKS = TagRegistry.block(FLAMMABLE_WOODEN_CAULDRONS_ID);
	public static final Tag<Item> FLAMMABLE_WOODEN_CAULDRON_ITEMS = TagRegistry.item(FLAMMABLE_WOODEN_CAULDRONS_ID);

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

	public Block registerCauldron(String type, CauldronBlock block) {
		Identifier identifier = new Identifier(MOD_ID, type + "_cauldron");

		// Register
		Registry.register(Registry.BLOCK, identifier, block);
		Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(ItemGroup.BREWING)));

		// Add color provider
		if (!(block instanceof LavaCauldronBlock)) {
			ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
				if (view == null || pos == null)
					return -1;
				return BiomeColors.getWaterColor(view, pos);
			}, block);
		}

		return block;
	}

	public Block registerCauldron(String type, Block base) {
		return this.registerCauldron(type, new CauldronBlock(FabricBlockSettings.copy(base).build()));
	}

	public Block registerLavaCauldron(String type, Block base) {
		return this.registerCauldron(type, new LavaCauldronBlock(FabricBlockSettings.copy(base).build()));
	}

	public Block registerCauldron(String type, Identifier baseID) {
		Optional<Block> base = Registry.BLOCK.getOrEmpty(baseID);
		if (base.isPresent()) {
			return this.registerCauldron(type, base.get());
		}

		LOGGER.debug("Missing the base block '{}' for cauldron of type '{}' (perhaps it will be registered after)", baseID, type);
		RegistryEntryAddedCallback.event(Registry.BLOCK).register((rawID, id, addedBase) -> {
			if (id.equals(baseID)) {
				this.registerCauldron(type, addedBase);
			}
		});
		return null;
	}

	public Block registerLavaCauldron(String type, Identifier baseID) {
		Optional<Block> base = Registry.BLOCK.getOrEmpty(baseID);
		if (base.isPresent()) {
			return this.registerLavaCauldron(type, base.get());
		}

		LOGGER.debug("Missing the base block '{}' for lava cauldron of type '{}' (perhaps it will be registered after)", baseID, type);
		RegistryEntryAddedCallback.event(Registry.BLOCK).register((rawID, id, addedBase) -> {
			if (id.equals(baseID)) {
				this.registerLavaCauldron(type, addedBase);
			}
		});
		return null;
	}

	public void registerModIntergationCauldrons() {
		FabricLoader loader = FabricLoader.getInstance();

		// Terrestria
		if (loader.isModLoaded("terrestria")) {
			// Wood
			registerCauldron("redwood", new Identifier("terrestria", "redwood"));
			registerCauldron("hemlock", new Identifier("terrestria", "hemlock"));
			registerCauldron("rubber", new Identifier("terrestria", "rubber"));
			registerCauldron("cypress", new Identifier("terrestria", "cypress"));
			registerCauldron("willow", new Identifier("terrestria", "willow"));
			registerCauldron("japanese_maple", new Identifier("terrestria", "japanese_maple"));
			registerCauldron("rainbow_eucalyptus", new Identifier("terrestria", "rainbow_eucalyptus"));
			registerCauldron("sakura", new Identifier("terrestria", "sakura"));

			// Basalt stones
			registerLavaCauldron("basalt", new Identifier("terrestria", "basalt"));
			registerLavaCauldron("basalt_cobblestone", new Identifier("terrestria", "basalt_cobblestone"));
			registerLavaCauldron("mossy_basalt_cobblestone", new Identifier("terrestria", "mossy_basalt_cobblestone"));

			// Basalt bricks
			registerLavaCauldron("basalt_brick", new Identifier("terrestria", "basalt_bricks"));
			registerLavaCauldron("mossy_basalt_brick", new Identifier("terrestria", "mossy_basalt_bricks"));
		}

		// Traverse
		if (loader.isModLoaded("traverse")) {
			registerCauldron("fir", new Identifier("traverse", "fir_planks"));
		}

		// The Hallow
		if (loader.isModLoaded("thehallow")) {
			// Wood
			registerCauldron("deadwood", new Identifier("thehallow", "deadwood_planks"));

			// Tainted stones
			registerCauldron("tainted_stone", new Identifier("thehallow", "tainted_stone"));
			registerCauldron("tainted_cobblestone", new Identifier("thehallow", "tainted_cobblestone"));

			// Tainted stone bricks
			registerCauldron("tainted_stone_brick", new Identifier("thehallow", "tainted_stone_bricks"));

			// Glass
			SOUL_GLASS_CAULDRON = registerCauldron("soul_glass", new GlassCauldronBlock(FabricBlockSettings.copy(Registry.BLOCK.get(new Identifier("thehallow", "soul_glass"))).build()));

			// Sandstone
			registerCauldron("tainted_sandstone", new Identifier("thehallow", "tainted_sandstone"));
		}

		// Tech Reborn
		if (loader.isModLoaded("techreborn")) {
			// Wood
			registerCauldron("reborn_rubber", new Identifier("techreborn", "rubber_planks"));

			// Gem storage blocks
			registerCauldron("peridot", new Identifier("techreborn", "peridot_storage_block"));
			registerCauldron("ruby", new Identifier("techreborn", "ruby_storage_block"));
			registerCauldron("sapphire", new Identifier("techreborn", "sapphire_storage_block"));
			registerCauldron("red_garnet", new Identifier("techreborn", "red_garnet_storage_block"));
			registerCauldron("yellow_garnet", new Identifier("techreborn", "yellow_garnet_storage_block"));

			// Metal storage blocks
			registerCauldron("zinc", new Identifier("techreborn", "zinc_storage_block"));
			registerCauldron("aluminum", new Identifier("techreborn", "aluminum_storage_block"));
			registerCauldron("brass", new Identifier("techreborn", "brass_storage_block"));
			registerCauldron("bronze", new Identifier("techreborn", "bronze_storage_block"));
			registerCauldron("copper", new Identifier("techreborn", "copper_storage_block"));
			registerCauldron("silver", new Identifier("techreborn", "silver_storage_block"));
			registerCauldron("electrum", new Identifier("techreborn", "electrum_storage_block"));
			registerCauldron("invar", new Identifier("techreborn", "invar_storage_block"));
			registerCauldron("nickel", new Identifier("techreborn", "nickel_storage_block"));
			registerCauldron("lead", new Identifier("techreborn", "lead_storage_block"));
			registerCauldron("platinum", new Identifier("techreborn", "platinum_storage_block"));
			registerCauldron("refined_iron", new Identifier("techreborn", "refined_iron_storage_block"));
			registerCauldron("steel", new Identifier("techreborn", "steel_storage_block"));
			registerCauldron("tin", new Identifier("techreborn", "tin_storage_block"));

			// Refractory metal storage blocks
			registerLavaCauldron("tungsten", new Identifier("techreborn", "tungsten_storage_block"));
			registerLavaCauldron("tungstensteel", new Identifier("techreborn", "tungstensteel_storage_block"));
			registerLavaCauldron("iridium", new Identifier("techreborn", "iridium_storage_block"));
			registerLavaCauldron("chrome", new Identifier("techreborn", "chrome_storage_block"));
			registerLavaCauldron("titanium", new Identifier("techreborn", "titanium_storage_block"));
		}

		// NonCorrelatedExtras
		if (loader.isModLoaded("noncorrelatedextras")) {
			registerCauldron("polarized_iron", new Identifier("noncorrelatedextras", "polarized_iron_block"));
		}
		
		// Adabranium
		if (loader.isModLoaded("adabraniummod")) {
			registerCauldron("vibranium", new Identifier("adabraniummod", "vibranium_block"));
			registerCauldron("adamantium", new Identifier("adabraniummod", "adamantium_block"));
		}

		// Cornflower
		if (loader.isModLoaded("cornflower")) {
			registerCauldron("timeworn_brick", new Identifier("cornflower", "timeworn_brick"));
		}

		// Blockus
		if (loader.isModLoaded("blockus")) {
			// Wood
			registerCauldron("white_oak", new Identifier("blockus", "white_oak_planks"));
			registerCauldron("charred", new Identifier("blockus", "charred_planks"));
			registerCauldron("bamboo", new Identifier("blockus", "bamboo_planks"));

			// Sandstone
			registerCauldron("sandy_brick", new Identifier("blockus", "sandy_bricks"));
			registerCauldron("sandstone_brick", new Identifier("blockus", "sandstone_bricks"));
			registerCauldron("red_sandstone_brick", new Identifier("blockus", "red_sandstone_bricks"));
			registerCauldron("soul_sandstone", new Identifier("blockus", "soul_sandstone"));
			registerCauldron("soul_sandstone_brick", new Identifier("blockus", "soul_sandstone_bricks"));

			// Polished
			registerLavaCauldron("polished_netherrack", new Identifier("blockus", "polished_netherrack"));

			// Stained stone brick
			registerCauldron("white_stone_brick", new Identifier("blockus", "white_stone_bricks"));
			registerCauldron("orange_stone_brick", new Identifier("blockus", "orange_stone_bricks"));
			registerCauldron("magenta_stone_brick", new Identifier("blockus", "magenta_stone_bricks"));
			registerCauldron("light_blue_stone_brick", new Identifier("blockus", "light_blue_stone_bricks"));
			registerCauldron("yellow_stone_brick", new Identifier("blockus", "yellow_stone_bricks"));
			registerCauldron("lime_stone_brick", new Identifier("blockus", "lime_stone_bricks"));
			registerCauldron("pink_stone_brick", new Identifier("blockus", "pink_stone_bricks"));
			registerCauldron("gray_stone_brick", new Identifier("blockus", "gray_stone_bricks"));
			registerCauldron("cyan_stone_brick", new Identifier("blockus", "cyan_stone_bricks"));
			registerCauldron("purple_stone_brick", new Identifier("blockus", "purple_stone_bricks"));
			registerCauldron("blue_stone_brick", new Identifier("blockus", "blue_stone_bricks"));
			registerCauldron("brown_stone_brick", new Identifier("blockus", "brown_stone_bricks"));
			registerCauldron("green_stone_brick", new Identifier("blockus", "green_stone_bricks"));
			registerCauldron("red_stone_brick", new Identifier("blockus", "red_stone_bricks"));
			registerCauldron("black_stone_brick", new Identifier("blockus", "black_stone_bricks"));
		}
	}

	@Override
	public void onInitialize() {
		// Stones
		registerCauldron("stone", Blocks.STONE);
		registerCauldron("cobblestone", Blocks.COBBLESTONE);
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
		registerCauldron("oak", Blocks.OAK_PLANKS);
		registerCauldron("spruce", Blocks.SPRUCE_PLANKS);
		registerCauldron("birch", Blocks.BIRCH_PLANKS);
		registerCauldron("jungle", Blocks.JUNGLE_PLANKS);
		registerCauldron("acacia", Blocks.ACACIA_PLANKS);
		registerCauldron("dark_oak", Blocks.DARK_OAK_PLANKS);
		FlammableBlockRegistry.getInstance(Blocks.FIRE).add(FLAMMABLE_WOODEN_CAULDRON_BLOCKS, 5, 20);
		FuelRegistry.INSTANCE.add(FLAMMABLE_WOODEN_CAULDRON_ITEMS, 300);

		// Metals and gems
		registerCauldron("gold", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.GOLD_BLOCK).build()));
		registerCauldron("diamond", Blocks.DIAMOND_BLOCK);
		registerCauldron("emerald", Blocks.EMERALD_BLOCK);

		// Glass
		GLASS_CAULDRON = registerCauldron("glass", new GlassCauldronBlock(FabricBlockSettings.copy(Blocks.GLASS).build()));
		WHITE_STAINED_GLASS_CAULDRON = registerCauldron("white_stained_glass", new StainedGlassCauldronBlock(DyeColor.WHITE, FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS).build()));
		ORANGE_STAINED_GLASS_CAULDRON = registerCauldron("orange_stained_glass", new StainedGlassCauldronBlock(DyeColor.ORANGE, FabricBlockSettings.copy(Blocks.ORANGE_STAINED_GLASS).build()));
		MAGENTA_STAINED_GLASS_CAULDRON = registerCauldron("magenta_stained_glass", new StainedGlassCauldronBlock(DyeColor.MAGENTA, FabricBlockSettings.copy(Blocks.MAGENTA_STAINED_GLASS).build()));
		LIGHT_BLUE_STAINED_GLASS_CAULDRON = registerCauldron("light_blue_stained_glass", new StainedGlassCauldronBlock(DyeColor.LIGHT_BLUE, FabricBlockSettings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS).build()));
		YELLOW_STAINED_GLASS_CAULDRON = registerCauldron("yellow_stained_glass", new StainedGlassCauldronBlock(DyeColor.YELLOW, FabricBlockSettings.copy(Blocks.YELLOW_STAINED_GLASS).build()));
		LIME_STAINED_GLASS_CAULDRON = registerCauldron("lime_stained_glass", new StainedGlassCauldronBlock(DyeColor.LIME, FabricBlockSettings.copy(Blocks.LIME_STAINED_GLASS).build()));
		PINK_STAINED_GLASS_CAULDRON = registerCauldron("pink_stained_glass", new StainedGlassCauldronBlock(DyeColor.PINK, FabricBlockSettings.copy(Blocks.PINK_STAINED_GLASS).build()));
		GRAY_STAINED_GLASS_CAULDRON = registerCauldron("gray_stained_glass", new StainedGlassCauldronBlock(DyeColor.GRAY, FabricBlockSettings.copy(Blocks.GRAY_STAINED_GLASS).build()));
		LIGHT_GRAY_STAINED_GLASS_CAULDRON = registerCauldron("light_gray_stained_glass", new StainedGlassCauldronBlock(DyeColor.LIGHT_GRAY, FabricBlockSettings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS).build()));
		CYAN_STAINED_GLASS_CAULDRON = registerCauldron("cyan_stained_glass", new StainedGlassCauldronBlock(DyeColor.CYAN, FabricBlockSettings.copy(Blocks.CYAN_STAINED_GLASS).build()));
		PURPLE_STAINED_GLASS_CAULDRON = registerCauldron("purple_stained_glass", new StainedGlassCauldronBlock(DyeColor.PURPLE, FabricBlockSettings.copy(Blocks.PURPLE_STAINED_GLASS).build()));
		BLUE_STAINED_GLASS_CAULDRON = registerCauldron("blue_stained_glass", new StainedGlassCauldronBlock(DyeColor.BLUE, FabricBlockSettings.copy(Blocks.BLUE_STAINED_GLASS).build()));
		BROWN_STAINED_GLASS_CAULDRON = registerCauldron("brown_stained_glass", new StainedGlassCauldronBlock(DyeColor.BROWN, FabricBlockSettings.copy(Blocks.BROWN_STAINED_GLASS).build()));
		GREEN_STAINED_GLASS_CAULDRON = registerCauldron("green_stained_glass", new StainedGlassCauldronBlock(DyeColor.GREEN, FabricBlockSettings.copy(Blocks.GREEN_STAINED_GLASS).build()));
		RED_STAINED_GLASS_CAULDRON = registerCauldron("red_stained_glass", new StainedGlassCauldronBlock(DyeColor.RED, FabricBlockSettings.copy(Blocks.RED_STAINED_GLASS).build()));
		BLACK_STAINED_GLASS_CAULDRON = registerCauldron("black_stained_glass", new StainedGlassCauldronBlock(DyeColor.BLACK, FabricBlockSettings.copy(Blocks.BLACK_STAINED_GLASS).build()));

		// Coal
		FuelRegistry.INSTANCE.add(registerCauldron("coal", Blocks.COAL_BLOCK), 6400);

		// Miscellaneous
		registerCauldron("purpur", Blocks.PURPUR_BLOCK);
		registerCauldron("honeycomb", Blocks.HONEYCOMB_BLOCK);
		registerCauldron("quartz", Blocks.QUARTZ_BLOCK);
		registerCauldron("lapis", Blocks.LAPIS_BLOCK);
		registerCauldron("redstone", new RedstoneCauldronBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build()));
		registerCauldron("freezing", new FreezingCauldronBlock(FabricBlockSettings.copy(Blocks.BLUE_ICE).build()));
		registerCauldron("brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.BRICKS).build()));
		registerCauldron("obsidian", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).build()));
		registerCauldron("nether_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.NETHER_BRICKS).build()));
		registerCauldron("red_nether_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.RED_NETHER_BRICKS).build()));
		registerCauldron("end_stone_brick", new LavaCauldronBlock(FabricBlockSettings.copy(Blocks.END_STONE_BRICKS).build()));

		// Shulker
		registerCauldron("shulker", new ShulkerCauldronBlock(FabricBlockSettings.copy(Blocks.SHULKER_BOX).build()));

		// Mod integrations
		registerModIntergationCauldrons();
	}
}