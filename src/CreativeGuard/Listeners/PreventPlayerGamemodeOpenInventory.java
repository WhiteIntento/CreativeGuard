package CreativeGuard.Listeners;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import CreativeGuard.Main;

public class PreventPlayerGamemodeOpenInventory implements Listener {
	
	@EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
		String type=event.getInventory().getType().name();
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE && 
        		(!event.getPlayer().hasPermission("creativeguard.admin") && 
        				!event.getPlayer().hasPermission("creativeguard.open."+type)) ) {
        	if(Main.getPluginInstance().getConfig().getStringList("PREVENT_OPEN_ITEM_INVENTORY").contains(type)) {
        		event.setCancelled(true);
        	}	
        }
        event.getPlayer().sendMessage("open inventory " + type);
    }

}
