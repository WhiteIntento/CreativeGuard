package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import CreativeGuard.Utils.PlayerUtil;

public class PreventCreativeThrowListener implements Listener {

	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
		if (!event.getAction().toString().equals("RIGHT_CLICK_AIR") && !event.getAction().toString().equals("RIGHT_CLICK_BLOCK")) {
            return;
        }
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
        	if(!event.getPlayer().hasPermission("creativeguard.throw.trident") && !event.getPlayer().hasPermission("creativeguard.admin")) {
        		if (item.getType().toString().equals("TRIDENT")) {
        			PlayerUtil.sendLocaleMessage(event.getPlayer(), "dont_permission_throw_trident");
                    event.setCancelled(true);
                }
        	}
        }
    }
}
