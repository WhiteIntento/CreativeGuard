package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PreventUsageAllBuckets implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerBucketEmptyEvent event) {
		Player p = event.getPlayer();
		p.sendMessage("bucket is checked");
		if(p.getGameMode() == GameMode.CREATIVE) {
			if(!p.hasPermission("creativeguard.admin") && !p.hasPermission("creativeguard.use.buckets")) {
				event.setCancelled(true);
			}
		}
	}
}
