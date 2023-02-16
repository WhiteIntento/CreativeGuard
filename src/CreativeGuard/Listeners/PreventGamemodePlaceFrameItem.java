package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import CreativeGuard.Utils.PlayerUtil;

public class PreventGamemodePlaceFrameItem implements Listener {
	
	@EventHandler
	public void onItemFramePlace(PlayerInteractEntityEvent event){
	    Entity entities = event.getRightClicked();
	    if(entities instanceof ItemFrame){
	        Player player = event.getPlayer();
	        if(player.getGameMode() == GameMode.CREATIVE) {
	        	if(!player.hasPermission("creativeguard.frame.item") && !player.hasPermission("creativeguard.admin")){
		            event.setCancelled(true);
		            PlayerUtil.sendLocaleMessage(player, "dont_permission_place_frame");
		        }
	        }
	        
	    }
	}


}
