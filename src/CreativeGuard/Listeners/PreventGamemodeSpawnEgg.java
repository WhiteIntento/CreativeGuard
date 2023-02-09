package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import CreativeGuard.Main;

public class PreventGamemodeSpawnEgg implements Listener{

	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		if(event.getPlayer().getGameMode()==GameMode.CREATIVE) {
			if(event.getItem() != null) {
				String type = event.getItem().getType().name();
				if(type.contains("_EGG")) {
					if(!event.getPlayer().hasPermission("creativeguard.spawnegg."+type) && !event.getPlayer().hasPermission("creativeguard.admin")) {
					
						event.setCancelled(true);
					}
				}
				
			}
			
		}
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
	    if (event.getRightClicked() instanceof Entity) {
	    	String type = event.getPlayer().getInventory().getItemInMainHand().getType().name();
	    	if(type.contains("_EGG")) {
				if(!event.getPlayer().hasPermission("creativeguard.spawnegg."+type) && !event.getPlayer().hasPermission("creativeguard.admin")) {
				
					event.setCancelled(true);
				}
			}
	    }
	}
}
