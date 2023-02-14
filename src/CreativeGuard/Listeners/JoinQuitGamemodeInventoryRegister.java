package CreativeGuard.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import CreativeGuard.Player.PlayerRegister;


public class JoinQuitGamemodeInventoryRegister implements Listener {

	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerRegister.registerPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		PlayerRegister.removePlayer(event.getPlayer());
	}
}
