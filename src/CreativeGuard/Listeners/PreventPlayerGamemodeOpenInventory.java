package CreativeGuard.Listeners;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import CreativeGuard.Main;
import CreativeGuard.Utils.PlayerUtil;

public class PreventPlayerGamemodeOpenInventory implements Listener {
	
	@EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
		String type=event.getInventory().getType().name();
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE && 
        		(!event.getPlayer().hasPermission("creativeguard.admin") && 
        				!event.getPlayer().hasPermission("creativeguard.open."+type)) ) {
        	if(Main.getPluginInstance().getConfig().getStringList("PREVENT_OPEN_ITEM_INVENTORY").contains(type)) {
        		if(event.getPlayer() instanceof Player) {
        			PlayerUtil.sendLocaleMessage((Player)event.getPlayer(), "dont_permission_open_inventory");
        		}        		
        		event.setCancelled(true);
        	}	
        }
    }

}
