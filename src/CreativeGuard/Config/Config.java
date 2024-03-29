package CreativeGuard.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import CreativeGuard.Main;

public class Config {
	protected FileConfiguration config;
	public Config(FileConfiguration config) {
		this.config=config;
		this.defineDefaultConfiguration();
	}
	
	public void createConfigIfNotExists() {
		File c=new File("plugins" + File.separator + Main.PLUGIN_NAME + File.separator + "config.yml");
		if(!c.exists()) {
			this.defineDefaultConfiguration();
			Main.getPluginInstance().saveConfig();
		}
	}
	public void defineDefaultConfiguration() {
		this.config.addDefault("DEFAULT_LOCALE", "en");
		this.config.addDefault("DIFFERENT_INVENTORY", true);
		this.config.addDefault("PREVENT_CREATIVE_MODE_ATTACK_ANOTHER_ENTITY",true);
		this.config.addDefault("PREVENT_CREATIVE_MODE_DROP_ITEMS",true);
		this.config.addDefault("PREVENT_CREATIVE_INTERACT_SPAWNER_EGG", true);
		this.config.addDefault("PREVENT_DROP_MONSTERS_WHEN_GAMEMODE_KILL", false); //creativeguard.gamemode.getdrops
		this.config.addDefault("PREVENT_GAMEMODE_DROP_IF_PLAYER_IS_KILLED", true);
		this.config.addDefault("PREVENT_GAMEMODE_SPAWN_EGG", true);
		this.config.addDefault("PREVENT_GAMEMODE_DROP_POTION", true);
		this.config.addDefault("PREVENT_USAGE_SAVED_HOTBARS", true);
		this.config.addDefault("PREVENT_CREATIVE_BUILD_IRON_GOLEMS", false); //By default is false because by default iron_block place is forbidden
		this.config.addDefault("PREVENT_CREATIVE_BUILD_SNOW_GOLEMS", true);
		this.config.addDefault("PREVENT_CREATIVE_USE_ARROW_INSTRUMENTS", true);
		this.config.addDefault("PREVENT_PLACE_CUSTOM_ITEMS", true);
		this.config.addDefault("PREVENT_USAGE_ALL_BUCKETS", true);
		this.config.addDefault("PREVENT_CREATIVE_USAGE_ARMOR_STAND", true);
		this.config.addDefault("PREVENT_CREATIVE_LEAD_USAGE", true);
		this.config.addDefault("PREVENT_CREATIVE_THROW_TRIDENT", true);
		this.config.addDefault("PREVENT_CREATIVE_SHEAR_ENTITY_USAGE", true);
		this.config.addDefault("PREVENT_CREATIVE_PLAYER_EAT", true);
		this.config.addDefault("PREVENT_CREATIVE_PICKUP", true);
		this.config.addDefault("PREVENT_CREATIVE_INTERACT_ENTITY", true);
		this.config.addDefault("PREVENT_CREATIVE_FALLING_BLOCKS_CHEAT", true);
		this.config.addDefault("BLOCK_STORAGE_ENGINE", "BLOCK_STORAGE");
		this.config.addDefault("debug", false);
		List<String> list=new ArrayList<String>();
		list.add("CHEST");
		list.add("ENDER_CHEST");
		list.add("ANVIL");
		list.add("HOPPER");
		list.add("CRAFTING");
		list.add("BEACON");
		list.add("WORKBENCH");
		list.add("ENCHANTING");
		list.add("BREWING");
		list.add("DISPENSER");
		list.add("DROPER");
		list.add("FURNACE");
		list.add("MERCHANT");
		list.add("SHULKER_BOX");
		list.add("SMOKER");
		list.add("STONECUTTER");
		list.add("CARTOGRAPHY");
		list.add("SMITHING");
		list.add("BLAST_FURNANCE");
		list.add("BARREL");
		list.add("LOOM");
		list.add("GRINDSTONE");
		list.add("BLAST_FURNACE");
		list.add("DROPPER");
		this.config.addDefault("PREVENT_OPEN_ITEM_INVENTORY", list);
		List<String> ppi=new ArrayList<String>();
		ppi.add("DIAMOND_BLOCK");
		ppi.add("GOLD_BLOCK");
		ppi.add("EMERALD_BLOCK");
		ppi.add("IRON_BLOCK");
		ppi.add("TNT");
		ppi.add("ANVIL");
		ppi.add("ENCHANTMENT_TABLE");
		ppi.add("RESPAWN_ANCHOR");
		ppi.add("END_PORTAL_FRAME");
		ppi.add("SPAWNER");
		ppi.add("POINTED_DRIPSTONE");
		ppi.add("SCAFFOLDING");
		ppi.add("INFESTED_STONE");
		ppi.add("BEDROCK");
		ppi.add("BARRIER");
		ppi.add("COMMAND_BLOCK");
		ppi.add("WITHER_SKELETON_SKULL");
		ppi.add("ARMOR_STAND");
		ppi.add("HOPPER");
		ppi.add("STRUCTURE_VOID");
		ppi.add("OBSIDIAN");
		ppi.add("CHORUS_PLANT");
		ppi.add("SPONGE");
		ppi.add("BIG_DRIPLEAF");
		ppi.add("OAK_SAPLING");
		ppi.add("SPRUCE_SAPLING");
		ppi.add("BIRCH_SAPLING");
		ppi.add("JUNGLE_SAPLING");
		ppi.add("ACACIA_SAPLING");
		ppi.add("CACTUS");
		ppi.add("SUGAR_CANE");
		ppi.add("PLAYER_HEAD");
		ppi.add("CHIPPED_ANVIL");
		ppi.add("DAMAGED_ANVIL");
		ppi.add("BEETROOTS");
		ppi.add("DARK_OAK_SAPLING");
		ppi.add("BAMBOO_SAPLING");
		ppi.add("NETHER_WART");
		ppi.add("POTATOES");
		ppi.add("FROGSPAWN");
		ppi.add("COCAO");
		ppi.add("BEACON");
		ppi.add("CAKE");
		ppi.add("MYCELIUM"); //This block transform neary blocks like it
		ppi.add("BUDDING_AMETHYST"); //Crystals grow on this block
		ppi.add("BOOKSHELF");
		ppi.add("ENCHANTING_TABLE");
		ppi.add("OBSERVER");
		ppi.add("SCULK_SENSOR");
		ppi.add("POWDER_SNOW");
		this.config.addDefault("PREVENT_PLACE_ITEM", ppi);
		String[] playerInteract= {
				"FLINT_AND_STEEL",
				"FIRE_CHARGE",
				"WATER_BUCKET",
				"LAVA_BUCKET",
				"TNT_MINECART",
				"EXPERIENCE_BOTTLE",
				"BONE_MEAL",
				"WHEAT_SEEDS",
				"MELON_SEEDS",
				"PUMPKIN_SEEDS",
				"BETROOT_SEEDS",
				"FISHING_ROD",
				"ARMOR_STAND",
				"WRITABLE_BOOK",
				"STRING",
				"END_CRYSTAL",
				"PAINTING",
				"ITEM_FRAME",
				"GLOW_ITEM_FRAME",
				"ENDER_EYE",
				"OAK_SIGN",
				"SPRUCE_SIGN",
				"BIRCH_SIGN",
				"JUNGLE_SIGN",
				"ACACIA_SIGN",
				"DARK_OAK_SIGN",
				"MANGROVE_SIGN",
				"CRIMSON_SIGN",
				"WARPED_SIGN"
				};
		this.config.addDefault("PREVENT_PLAYER_INTERACT_ITEM", Arrays.asList(playerInteract));
		String[] preventInteractOnAnotherItem= {
			"CAMPFIRE",
			"SOUL_CAMPFIRE",
			"COMPOSTER",
			"JUKEBOX",
		};
		this.config.addDefault("PREVENT_INTERACT_CLICKED_ITEM", Arrays.asList(preventInteractOnAnotherItem));
		String[] preventPlayerSendCommand= {
				"ah",
				"shop",
				"trade",
				"enderchest",
				"ec",
				"sellall inventory",
				"sell",
				"sellall"
			};
		this.config.addDefault("PREVENT_CREATIVE_USE_COMMANDS", Arrays.asList(preventPlayerSendCommand));
		String[] preventCreateiveBreakBlocks= {
				"BEDROCK",
				"@SHULKER_BOX"
			};
		this.config.addDefault("PREVENT_CREATIVE_BREAK_BLOCKS", Arrays.asList(preventCreateiveBreakBlocks));
		this.config.addDefault("PREVENT_CREATIVE_CHEAT_WITH_PISTON", true);
		this.config.addDefault("BLOCK_PLACE_TIME_INTERVAL_IN", "HOUR");
		this.config.addDefault("DEFAULT_LIMIT_BLOCK", 0);
		this.config.addDefault("DEFAULT_LIMIT_ENTITY", 0);
		Map<String,Integer> bpie=new HashMap<String,Integer>();
		bpie.put("MINECART", 1);
		bpie.put("HOPPER_MINECART", 1);
		bpie.put("CHEST_MINECART", 1);
		bpie.put("FURNACE_MINECART", 1);
		bpie.put("TNT_MINECART", 1);
		this.config.addDefault("LIMIT_PLAYER_PLACE_ENTITY", bpie);
		Map<String,Integer> lppb=new HashMap<String,Integer>();
		lppb.put("REDSTONE_WIRE", 5);
		this.config.addDefault("LIMIT_PLAYER_PLACE_BLOCK", lppb);
		this.config.addDefault("MESSAGE_TITLE", "&4<&c<&7< &e&lCreative&9&lGuard&f &7>&c>&4> &a");
		this.config.options().copyDefaults(true);
		Main.getPluginInstance().saveConfig();
		Main.getPluginInstance().reloadConfig(); //I run this method because bukkit yml have bug with get data from map if not reloaded
	}

}
