package CreativeGuard.Listeners;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import CreativeGuard.Main;
import CreativeGuard.Player.PlayerRegister;
import CreativeGuard.Utils.LocaleUtil;
import CreativeGuard.Utils.PlayerUtil;

/**
 * 
 * This listener check for forbidden entity and limited entity
 *
 */

public class PlayerInteract implements Listener {

	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE && (
				event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) {
			String material = event.getPlayer().getInventory().getItemInMainHand().getType().name();
			//event.getPlayer().sendMessage(material);
	        if (Main.getPluginInstance().getConfig().getStringList("PREVENT_PLAYER_INTERACT_ITEM").contains(material) &&
	        		(!event.getPlayer().hasPermission("creativeguard.admin") && !event.getPlayer().hasPermission("creativeguard.interact."+material)
	        				&& !event.getPlayer().hasPermission("creativeguard.interact.*"))){
	        	PlayerUtil.sendLocaleMessage(event.getPlayer(), "dont_permission_interact");
	        	event.setCancelled(true);
	        	return;
	        }
			if(!PlayerRegister.getOrCreatePlayer(event.getPlayer()).canPlaceEntity(material)) {
				PlayerUtil.sendLocaleMessage(event.getPlayer(), "creative_place_entity_limit");
				event.setCancelled(true);
			}
		}
    }
	
	
	/* This is function for all entyty interact
	@EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
		Main.getPluginInstance().getLogger().info(event.getEntityType().name());
		if(event.getEntity().getShooter() instanceof Player) {
			Player p= (Player) event.getEntity().getShooter();
			p.sendMessage(event.getEntityType().name());
			if (event.getEntityType() == EntityType.FIREBALL) {
	            event.setCancelled(true);
	        }
			
		}
    }
    */
}

