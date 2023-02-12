package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import CreativeGuard.Main;
import CreativeGuard.Utils.FindUtil;

public class PreventCreateiveBreakBlocks implements Listener{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			String material = event.getBlock().getType().name();
			if(!event.getPlayer().hasPermission("creativeguard.break."+material) && !event.getPlayer().hasPermission("creativeguard.admin")) {
				
				if(FindUtil.existsInList(material, Main.getPluginInstance().getConfig().getStringList("PREVENT_CREATIVE_BREAK_BLOCKS"))) {
					event.setCancelled(true);
				}
			}
		}
	}
}
