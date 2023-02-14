package CreativeGuard.Player;

import java.io.IOException;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import CreativeGuard.ErrorProtect;

/**
 * 
 * @deprecated
 *
 */

public class GamemodesRegister {
	private static HashMap<String,Gamemodes> players=new HashMap<String, Gamemodes>();
	
	public static void registerPlayer(Player p) {
		String uuid=p.getUniqueId().toString();
		/*
		 * Clear player inventory for protect real inventory who be loaded soon
		 *
		p.getInventory().clear();
		*/
		Gamemodes playerGamemodeInventory;
		try {
			playerGamemodeInventory = PlayerFileStore.get(uuid, "gamemodes");
			
			/*
			 * this method removes all of the player's effects from the current state in order to restore the squeezed effects if they have been manipulated elsewhere
			 */
			playerGamemodeInventory.removePlayerPotionEffect(p);
			/*
			 * In this method, the player's real items and effects are loaded relative to their game mode
			 */
			playerGamemodeInventory.setPlayerGamemodeInventory(p.getGameMode(), p);
			GamemodesRegister.players.put(uuid, playerGamemodeInventory);
		} catch (IOException e) {
			ErrorProtect.HAS_FATAL_ERROR=true;
			e.printStackTrace();
		}
		
	}
	
	public static Gamemodes getPlayerGameMode(Player p) {
		if(GamemodesRegister.players.get(p.getUniqueId().toString())== null) {
			GamemodesRegister.registerPlayer(p);
		}
		return GamemodesRegister.players.get(p.getUniqueId().toString());
	}
	
	public static void removePlayer(Player p) {
		GamemodesRegister.getPlayerGameMode(p).updateGamemodeInventoryItems(p.getGameMode(), p);
		try {
			PlayerFileStore.save(p.getUniqueId().toString(), "gamemodes", GamemodesRegister.getPlayerGameMode(p).serialize());
		} catch (IOException e) {
			ErrorProtect.HAS_FATAL_ERROR=true;
			e.printStackTrace();
		}
		GamemodesRegister.players.remove(p.getUniqueId().toString());
		if(p.getGameMode() == GameMode.CREATIVE) {
			p.getInventory().clear();
		}
	}
	
	public static int getSize() {
		return GamemodesRegister.players.size();
	}
	
	public static void savePlayerData(Player p) {
		GamemodesRegister.getPlayerGameMode(p).updateGamemodeInventoryItems(p.getGameMode(), p);
		try {
			PlayerFileStore.save(p.getUniqueId().toString(), "gamemodes", GamemodesRegister.getPlayerGameMode(p).serialize());
		} catch (IOException e) {
			ErrorProtect.HAS_FATAL_ERROR=true;
			e.printStackTrace();
		}
	}
}
