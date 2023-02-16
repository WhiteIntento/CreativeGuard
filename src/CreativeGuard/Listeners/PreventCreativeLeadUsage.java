package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;

import CreativeGuard.Utils.PlayerUtil;

public class PreventCreativeLeadUsage implements Listener {
	
	@EventHandler
	public void onPlayerLeashEntity(PlayerLeashEntityEvent event) {
	    Player player = event.getPlayer();
	    GameMode gameMode = player.getGameMode();
	    if (gameMode == GameMode.CREATIVE) {
	    	if(!player.hasPermission("creativeguard.admin") && !player.hasPermission("creativeguard.use.lead")) {
	    		PlayerUtil.sendLocaleMessage(player, "dont_permission_use_lead");
	    		event.setCancelled(true);
	    	}
	    }
	}

}
