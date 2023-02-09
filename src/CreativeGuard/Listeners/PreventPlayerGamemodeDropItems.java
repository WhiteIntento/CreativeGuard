package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;


public class PreventPlayerGamemodeDropItems implements Listener{

	
	@EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE && 
				(!event.getPlayer().hasPermission("creativeguard.admin") &&
						!event.getPlayer().hasPermission("creativeguard.dropitem"))) {
            event.setCancelled(true);
        }
    }
}
