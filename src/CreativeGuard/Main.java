package CreativeGuard;


import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import CreativeGuard.Chunk.ChunkRegister;
import CreativeGuard.Commands.Reload;
import CreativeGuard.Config.Config;
import CreativeGuard.Config.DefineDefaultLocales;
import CreativeGuard.Listeners.*;
import CreativeGuard.Player.PlayerFileStore;
import CreativeGuard.Utils.ColorMessage;

public class Main extends JavaPlugin{
	protected Config config;
	protected static Main plugin=null;
	public static final String PLUGIN_NAME="CreativeGuard";
	@Override
    public void onEnable() {

		try {
			this._startPlugin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void _startPlugin() throws IOException {
		Main.plugin = this;
		this.config= new Config(this.getConfig());
		this.config.createConfigIfNotExists();
		PlayerFileStore.initDirectory();
		Directories.createDirectoriesIfNotExists();
		DefineDefaultLocales.defineEnglishLocales();
		
		this.getCommand("reload").setExecutor(new Reload());
		//Register Events
		 this.registerListeners();
		 this.getLogger().info(ColorMessage.getLocaleWithTitle("start_message"));
	}
	
	public void registerListeners() {
		if(this.getConfig().getBoolean("PREVENT_CREATIVE_MODE_ATTACK_ANOTHER_ENTITY")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventPlayerGamemodeAttack(), this);
		 }
		
		 Bukkit.getServer().getPluginManager().registerEvents(new PreventPlayerGamemodeOpenInventory(), this);
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_MODE_DROP_ITEMS")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventPlayerGamemodeDropItems(), this);
		 }
		 
		 Bukkit.getServer().getPluginManager().registerEvents(new BreakPlaceBlock(), this);
		 Bukkit.getServer().getPluginManager().registerEvents(new PreventBlockPlace(), this);
		 if(this.getConfig().getBoolean("DIFFERENT_INVENTORY")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new GamemodeChangeDetectDifferentInventory(), this); 
		 }
		 Bukkit.getServer().getPluginManager().registerEvents(new PreventPlayerSendCommand(), this);
		 Bukkit.getServer().getPluginManager().registerEvents(new JoinQuitGamemodeInventoryRegister(), this);
		 Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_INTERACT_SPAWNER_EGG")==true) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventInteractSpawnEggAndSpawner(), this);
		 }
		 Bukkit.getServer().getPluginManager().registerEvents(new PreventGamemodePlaceFrameItem(), this);
		 if(this.getConfig().getBoolean("PREVENT_DROP_MONSTERS_WHEN_GAMEMODE_KILL")==true) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventGamemodeEntityDrops(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_GAMEMODE_DROP_IF_PLAYER_IS_KILLED")==true) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventGamemodeDeathDropItems(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_GAMEMODE_SPAWN_EGG")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventGamemodeSpawnEgg(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_GAMEMODE_DROP_POTION")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventGamemodeDropPotion(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_BUILD_IRON_GOLEMS")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeBuildIronGolem(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_BUILD_SNOW_GOLEMS")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeBuildSnowGolem(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_USE_ARROW_INSTRUMENTS")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeUseArrowInstruments(), this);
		 }
		 Bukkit.getServer().getPluginManager().registerEvents(new ChunkListener(), this);
		 if(this.getConfig().getBoolean("PREVENT_PLACE_CUSTOM_ITEMS")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventPlaceCustomItems(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_USAGE_ALL_BUCKETS")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventUsageAllBuckets(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_USAGE_ARMOR_STAND")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeUsageArmorStand(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_LEAD_USAGE")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeLeadUsage(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_SHEAR_ENTITY_USAGE")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeShearEntity(), this);
		 }
		 Bukkit.getServer().getPluginManager().registerEvents(new PreventInteractClickedItem(), this);
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_PLAYER_EAT")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativePlayerEat(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_PICKUP")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativePickup(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_INTERACT_ENTITY")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeInteractEntity(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_FALLING_BLOCKS_CHEAT")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeFallingBlocksCheat(), this);
		 }
		 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreateiveBreakBlocks(), this);
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_THROW_TRIDENT")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeThrowListener(), this);
		 }
		 if(this.getConfig().getBoolean("debug")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new DebugListeners(), this);
		 }
		 if(this.getConfig().getBoolean("PREVENT_CREATIVE_CHEAT_WITH_PISTON")) {
			 Bukkit.getServer().getPluginManager().registerEvents(new PreventCreativeCheatWithPiston(), this);
		 }
	}
	
	public void unregisterListeners() {
		HandlerList.unregisterAll(this);
	}
	
	public void _stopPlugin() {
		this.unregisterListeners();
		ChunkRegister.saveAll();
	}
	

    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	this._stopPlugin();
    }
    
    public static Main getPluginInstance() {
    	if(Main.plugin==null) {
    		//todo
    	}
    	return Main.plugin;
    }

}
