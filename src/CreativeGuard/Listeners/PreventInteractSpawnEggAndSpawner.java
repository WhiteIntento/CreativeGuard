package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
/**
 * This class listener prevent interact gamemode creative players to click on spawner with egg
 * 
 *
 */
public class PreventInteractSpawnEggAndSpawner implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if (player.getGameMode() == GameMode.CREATIVE) {
	    	if(!player.hasPermission("creativeguard.place_egg_in_spawner") && !player.hasPermission("creatiiveguard.admin")) {
	    		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		            Block rcBlock = event.getClickedBlock();
		            if(rcBlock.getType() != null) {
		            	if(rcBlock.getType().name().contains("SPAWNER")) {
			            	if(event.getItem()!=null) {
			            		if(event.getItem().getType().name().contains("SPAWN_EGG")) {
				            		event.setCancelled(true);
				            	}
			            	}
			            }
		            }
		            
		        }
	    	}
	        
	    }
	}

}
