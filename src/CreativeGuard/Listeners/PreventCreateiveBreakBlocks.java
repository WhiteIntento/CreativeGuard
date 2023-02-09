package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import CreativeGuard.Main;

public class PreventCreateiveBreakBlocks implements Listener{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			String material = event.getBlock().getType().name();
			if(!event.getPlayer().hasPermission("creativeguard.break."+material) && !event.getPlayer().hasPermission("creativeguard.admin")) {
				if(Main.getPluginInstance().getConfig().getList("PREVENT_CREATIVE_BREAK_BLOCKS").contains(material)) {
					event.setCancelled(true);
				}
			}
		}
	}
}
