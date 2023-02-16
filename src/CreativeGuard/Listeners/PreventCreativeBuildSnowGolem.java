package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import CreativeGuard.Utils.PlayerUtil;

public class PreventCreativeBuildSnowGolem implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
	    if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
	    	if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.SNOW_BLOCK) {
	    		if(event.getItem() != null) {
	    			if(!event.getPlayer().hasPermission("creativeguard.build.snow_golem") && !event.getPlayer().hasPermission("creativeguard.admin")) {
	    				String material = event.getItem().getType().toString();
		    			if(material.contains("PUMPKIN")) {
		    				event.setCancelled(true);
		    				PlayerUtil.sendLocaleMessage(event.getPlayer(), "dont_permission_build_snow_golem");
		    			}
	    			}
	    		}
		    }
	    }
	    
	}

}
