package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PreventCreativeUsageArmorStand implements Listener{
	
	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
	    Player player = event.getPlayer();
	    if (player.getGameMode() == GameMode.CREATIVE) {
	    	if(event.getRightClicked() instanceof ArmorStand) {
	    		if(
		    			!player.hasPermission("creativeguard.admin") &&
		    			!player.hasPermission("creativeguard.use.armor_stand")
		    	){
		    		event.setCancelled(true);
		    	}
	    	}
	        
	    }
	}

}
