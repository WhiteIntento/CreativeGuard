package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PreventGamemodeDropPotion implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		if(event.getPlayer().getGameMode()==GameMode.CREATIVE) {
			if(event.getItem() != null) {
				String type = event.getItem().getType().name();
				if(type.contains("_POTION")) {
					if(!event.getPlayer().hasPermission("creativeguard.droppotion."+type) && !event.getPlayer().hasPermission("creativeguard.admin")) {
						event.setCancelled(true);
					}
				}
				
			}
		}
	}
}
