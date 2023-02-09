package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import CreativeGuard.Main;

public class PreventInteractClickedItem implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			if(event.getClickedBlock() != null) {
				String material = event.getClickedBlock().getType().name();
				if(!event.getPlayer().hasPermission("creativeguard.useblock."+material) && !event.getPlayer().hasPermission("creativeguard.admin")) {
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						if(Main.getPluginInstance().getConfig().getList("PREVENT_INTERACT_CLICKED_ITEM").contains(material)) {
							event.setCancelled(true);
						}
					}
				}
				
			}
		}
		
	}
}
