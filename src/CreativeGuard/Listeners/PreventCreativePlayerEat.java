package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import CreativeGuard.Utils.PlayerUtil;

public class PreventCreativePlayerEat implements Listener{
	
	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			if(!event.getPlayer().hasPermission("creativeguard.eat") && !event.getPlayer().hasPermission("creativeguard.admin")) {
				PlayerUtil.sendLocaleMessage(event.getPlayer(), "dont_permission_creative_eat");
				event.setCancelled(true);
			}
		}
	}

}
