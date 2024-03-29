package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import CreativeGuard.Utils.PlayerUtil;

public class PreventPlaceCustomItems implements Listener{
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		ItemStack item = event.getItemInHand();
		Player p = event.getPlayer();
		if(!p.hasPermission("creativeguard.admin") && !p.hasPermission("creativeguard.place_custom_items")) {
			if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
				if(item.hasItemMeta()) {
					PlayerUtil.sendLocaleMessage(p, "dont_permission_place_custom_block");
					event.setCancelled(true);
				}
			}
		}
		
		
	}
}
