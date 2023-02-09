package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PreventCreativeUseArrowInstruments implements Listener {
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(p.getGameMode() == GameMode.CREATIVE  && !p.hasPermission("creativeguard.arrowinsruments")) {
				event.setCancelled(true);
			}
		}
	}


}
