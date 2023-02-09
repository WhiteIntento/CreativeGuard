package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import CreativeGuard.Main;



public class PreventPlayerSendCommand implements Listener{
	
	
	@EventHandler
	public void onPlayerSendCommand(PlayerCommandPreprocessEvent event) {
		Player p = event.getPlayer();
		String command = event.getMessage().toLowerCase();
		String[] sc= command.split(" ");
		if(p.getGameMode() == GameMode.CREATIVE) {
			if(!p.hasPermission("creativeguard.usepreventcmd."+ command) && !p.hasPermission("creativeguard.admin")) {
				for(Object i : Main.getPluginInstance().getConfig().getList("PREVENT_CREATIVE_USE_COMMANDS")) {
					String str = "/" + i.toString();
					if(sc[0].equalsIgnoreCase(str) && command.contains(str)) {
						event.setCancelled(true);
						break;
					}
				}
			}
		}
	}
	
	

}
