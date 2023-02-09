package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PreventCreativePickup implements Listener{
	
	 @EventHandler
	  public void onItemPickup(PlayerPickupItemEvent event) {
		 if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			 if(!event.getPlayer().hasPermission("creativeguard.pickup") && !event.getPlayer().hasPermission("creativeguard.admin")) {
				 event.setCancelled(true);
			 }
			 
		 }
	    
	  }

}
