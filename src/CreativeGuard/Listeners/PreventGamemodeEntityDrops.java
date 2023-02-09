package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PreventGamemodeEntityDrops implements Listener{

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
	    Player player = event.getEntity().getKiller();
	    if (player != null) {
	    	if(player.getGameMode() == GameMode.CREATIVE && (
	    			!player.hasPermission("creativeguard.gamemode.getdropsentity") && !player.hasPermission("creativeguard.admin"))) {
	    		event.setDroppedExp(0);
		        event.getDrops().clear();
	    	}
	    }
	}
}
