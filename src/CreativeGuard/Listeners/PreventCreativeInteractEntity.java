package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PreventCreativeInteractEntity implements Listener {

	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
	    Entity clickedEntity = event.getRightClicked();
	    Player p = event.getPlayer();
	    if(p.getGameMode() == GameMode.CREATIVE) {
	    	if(!p.hasPermission("creativeguard.admin")  && p.hasPermission("creativeguard.prevent_entity." + clickedEntity.getName())) {
	    		event.setCancelled(true);
	    	}
	    }
	}
}
