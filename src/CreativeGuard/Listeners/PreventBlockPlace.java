package CreativeGuard.Listeners;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.GameMode;

import CreativeGuard.Main;
import CreativeGuard.Utils.LocaleUtil;

public class PreventBlockPlace implements Listener{

	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Material material=event.getBlock().getType();
		Player p = event.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE && (!p.hasPermission("creativeguard.admin") && !p.hasPermission("creativeguard.place."+material.name()) )) {
			if(Main.getPluginInstance().getConfig().getStringList("PREVENT_PLACE_ITEM").contains(material.name())) {
				p.sendMessage(LocaleUtil.get("dont_permission_place_block"));
				event.setCancelled(true);
			}
		}
		
	}
	
	/*
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE && !event.getPlayer().hasPermission("creativeguard.admin")) {
			boolean isLava=event.getBucket().equals(Material.LAVA_BUCKET);
			boolean isWater=event.getBucket().equals(Material.WATER_BUCKET);
			FileConfiguration config = Main.getPluginInstance().getConfig();
			if((!config.getBoolean("place_lava")) && isLava) {
				event.setCancelled(true);
				return;
			}
			if((!config.getBoolean("place_water")) && isWater) {
				event.setCancelled(true);
				return;
			}
		}
	}
	*/
}
