package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import CreativeGuard.Utils.PlayerUtil;

public class PreventUsageAllBuckets implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerBucketEmptyEvent event) {
		Player p = event.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE) {
			if(!p.hasPermission("creativeguard.admin") && !p.hasPermission("creativeguard.use.buckets")) {
				PlayerUtil.sendLocaleMessage(p, "dont_permission_use_bucket");
				event.setCancelled(true);
			}
		}
	}
}
