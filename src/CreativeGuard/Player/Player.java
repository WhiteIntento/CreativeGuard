package CreativeGuard.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

import CreativeGuard.ErrorProtect;
import CreativeGuard.Main;

public class Player {
	protected Gamemodes gamemodes;
	protected org.bukkit.entity.Player player;
	protected Map<String,EntityPlayerPlace> entityPlayerPlace = new HashMap<String,EntityPlayerPlace>();
	protected Map<String,BlockPlayerPlace> blockPlayerPlace = new HashMap<String,BlockPlayerPlace>();
	
	public Player(org.bukkit.entity.Player player,Gamemodes gamemodes) {
		this.player=player;
		this.gamemodes=gamemodes;
	}
	
	/*
	 * This constructor init player and get gamemode inventory from storage
	 */
	public Player(org.bukkit.entity.Player player) {
		this.player=player;
		String uuid=player.getUniqueId().toString();
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
			playerGamemodeInventory.removePlayerPotionEffect(player);
			/*
			 * In this method, the player's real items and effects are loaded relative to their game mode
			 */
			playerGamemodeInventory.setPlayerGamemodeInventory(player.getGameMode(), player);
			this.gamemodes=playerGamemodeInventory;
		} catch (IOException e) {
			ErrorProtect.HAS_FATAL_ERROR=true;
			e.printStackTrace();
		}
	}
	
	public boolean canPlaceBlock(String m) {
		if(this.blockPlayerPlace.get(m) == null) {
			this.blockPlayerPlace.put(m, new BlockPlayerPlace(m));
		}
		if(this.blockPlayerPlace.get(m).canPlace()) {
			this.blockPlayerPlace.get(m).place();
			return true;
		}
		
		return false;
	}
	
	public boolean canPlaceEntity(String material) {
		if(this.entityPlayerPlace.get(material) == null) {
			this.entityPlayerPlace.put(material, new EntityPlayerPlace(material));
		}
		if(this.entityPlayerPlace.get(material).canPlace()) {
			this.entityPlayerPlace.get(material).place();
			return true;
		}
		
		return false;
	}

	public Map<String, EntityPlayerPlace> getEntityPlayerPlace() {
		return entityPlayerPlace;
	}

	public void setEntityPlayerPlace(Map<String, EntityPlayerPlace> entityPlayerPlace) {
		this.entityPlayerPlace = entityPlayerPlace;
	}

	public Map<String, BlockPlayerPlace> getBlockPlayerPlace() {
		return blockPlayerPlace;
	}

	public void setBlockPlayerPlace(Map<String, BlockPlayerPlace> blockPlayerPlace) {
		this.blockPlayerPlace = blockPlayerPlace;
	}

	public Gamemodes getGamemodes() {
		return gamemodes;
	}

	public void setGamemodes(Gamemodes gamemodes) {
		this.gamemodes = gamemodes;
	}

	public org.bukkit.entity.Player getPlayer() {
		return player;
	}

	public void setPlayer(org.bukkit.entity.Player player) {
		this.player = player;
	}
	
	public void save() {
		this.gamemodes.updateGamemodeInventoryItems(this.player.getGameMode(), this.player);
		try {
			PlayerFileStore.save(this.player.getUniqueId().toString(), "gamemodes", this.gamemodes.serialize());
		} catch (IOException e) {
			ErrorProtect.HAS_FATAL_ERROR=true;
			e.printStackTrace();
		}
	}
}
