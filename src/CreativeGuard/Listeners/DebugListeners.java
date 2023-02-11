package CreativeGuard.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DebugListeners implements Listener{
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		String material=event.getBlock().getType().name();
		Player player=event.getPlayer();
		
		player.sendMessage("Place: " + material);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		String material=event.getMaterial().name();
		Player player=event.getPlayer();
		
		player.sendMessage("Interact: " + material);
	}
}
