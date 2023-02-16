package CreativeGuard.Listeners;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import CreativeGuard.Utils.PlayerUtil;


public class PreventPlayerGamemodeAttack implements Listener {
	
	@EventHandler
	  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
	    if (event.getDamager() instanceof Player) {
	      Player player = (Player) event.getDamager();
	      if (player.getGameMode() == GameMode.CREATIVE && 
	    		  (!player.hasPermission("creativeguard.admin") && !player.hasPermission("creativeguard.creative.attack")) ) {
	    	  PlayerUtil.sendLocaleMessage(player, "dont_permission_creative_attack");
	    	  event.setCancelled(true);
	      }
	    }
	  }

}
