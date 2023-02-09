package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import CreativeGuard.ErrorProtect;
import CreativeGuard.Player.GamemodesRegister;


public class GamemodeChangeDetectDifferentInventory implements Listener {

	
	@EventHandler
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
		Player p=event.getPlayer();
		if(!p.hasPermission("creativeguard.disable.changeinventory") && !p.hasPermission("creativeguard.admin")) {
			GameMode currentGameMode=p.getGameMode();
			GameMode newGamemode = event.getNewGameMode();
			if(ErrorProtect.HAS_FATAL_ERROR && newGamemode == GameMode.CREATIVE) {
				p.sendMessage("We have a problem and you can't switch your gamemode to creative mode at the moment. Please contact the server administrator.");
				event.setCancelled(true);
				return;
			}
			p.sendMessage("Current game mode is " + currentGameMode.name());
			p.sendMessage("New game mode is " + newGamemode.name());
			GamemodesRegister.getPlayerGameMode(p).changeGameMode(currentGameMode, newGamemode,p);
		}
		
	}
}
