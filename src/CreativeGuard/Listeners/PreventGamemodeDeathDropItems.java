package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PreventGamemodeDeathDropItems implements Listener{
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
	    Player player = event.getEntity();
	    if (player.getGameMode() == GameMode.CREATIVE) {
	        event.setDroppedExp(0);
	        event.getDrops().clear();
	    }
	}

}
