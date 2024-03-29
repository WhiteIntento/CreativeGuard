package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import CreativeGuard.ErrorProtect;
import CreativeGuard.Player.PlayerRegister;
import CreativeGuard.Utils.LocaleUtil;
import CreativeGuard.Utils.PlayerUtil;


public class GamemodeChangeDetectDifferentInventory implements Listener {

	
	@EventHandler
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
		Player p=event.getPlayer();
		if(!p.hasPermission("creativeguard.disable.changeinventory") && !p.hasPermission("creativeguard.admin")) {
			GameMode currentGameMode=p.getGameMode();
			GameMode newGamemode = event.getNewGameMode();
			if(ErrorProtect.HAS_FATAL_ERROR && newGamemode == GameMode.CREATIVE) {
				p.sendMessage(LocaleUtil.get("fatal_error_protect"));
				PlayerUtil.sendLocaleMessage(p, "fatal_error_protect");
				event.setCancelled(true);
				return;
			}
			PlayerRegister.getOrCreatePlayer(p).getGamemodes().changeGameMode(currentGameMode, newGamemode,p);
		}
		
	}
}
