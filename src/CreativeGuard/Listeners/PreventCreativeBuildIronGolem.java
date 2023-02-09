package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PreventCreativeBuildIronGolem implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
	    if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
	    	if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.IRON_BLOCK) {
	    		if(event.getItem() != null) {
	    			if(!event.getPlayer().hasPermission("creativeguard.build.iron_golem") && !event.getPlayer().hasPermission("creativeguard.admin")) {
	    				String material = event.getItem().getType().toString();
		    			if(material.contains("PUMPKIN")) {
		    				event.setCancelled(true);
		    				event.getPlayer().sendMessage("Spawn on iron golem on gamemode creative is forgotten from server creator");
		    			}
	    			}
	    		}
		    }
	    }
	    
	}

}
