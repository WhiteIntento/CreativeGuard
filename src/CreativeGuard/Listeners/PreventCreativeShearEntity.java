package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

import CreativeGuard.Utils.PlayerUtil;

public class PreventCreativeShearEntity implements Listener{
	
	@EventHandler
    public void onShear(PlayerShearEntityEvent event) {
        Player p = event.getPlayer();
        if(p.getGameMode() == GameMode.CREATIVE) {
        	if(!p.hasPermission("creativeguard.use.shear_entity") && !p.hasPermission("creativeguard.admin")) {
        		PlayerUtil.sendLocaleMessage(p, "dont_permission_to_shear_entity");
        		event.setCancelled(true);
        	}
        }
    }

}
