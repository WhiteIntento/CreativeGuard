package CreativeGuard.Player;


import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import CreativeGuard.Main;

public class Gamemodes {
	protected GamemodeInventory survivalInventory;
	protected GamemodeInventory creativeInventory;
	protected GamemodeInventory adventureInventory;
	protected GamemodeInventory spectatorInventory;
	protected boolean lockGameModeChange=false;
	
	public Gamemodes() {
		this.survivalInventory=new GamemodeInventory(GameMode.SURVIVAL);
		this.creativeInventory=new GamemodeInventory(GameMode.CREATIVE);
		this.adventureInventory=new GamemodeInventory(GameMode.ADVENTURE);
		this.spectatorInventory=new GamemodeInventory(GameMode.SPECTATOR);
		
	}
	
	public GamemodeInventory getSurvivalInventory() {
		return survivalInventory;
	}

	public void setSurvivalInventory(GamemodeInventory survivalInventory) {
		this.survivalInventory = survivalInventory;
	}

	public GamemodeInventory getCreativeInventory() {
		return creativeInventory;
	}

	public void setCreativeInventory(GamemodeInventory creativeInventory) {
		this.creativeInventory = creativeInventory;
	}

	public GamemodeInventory getAdventureInventory() {
		return adventureInventory;
	}

	public void setAdventureInventory(GamemodeInventory adventureInventory) {
		this.adventureInventory = adventureInventory;
	}

	public GamemodeInventory getSpectatorInventory() {
		return spectatorInventory;
	}

	public void setSpectatorInventory(GamemodeInventory spectatorInventory) {
		this.spectatorInventory = spectatorInventory;
	}


	
	/**
	 * 
	 * This methods save items in current GamemodeInventory from player inventory
	 */
	public void updateGamemodeInventoryItems(GameMode gm,Player player) {
		if(gm == GameMode.SURVIVAL) {
			this.survivalInventory.setItemsFromInventory(player.getInventory());
			this.survivalInventory.setPotionEffectsFromPlayer(player);
		} else if(gm == GameMode.CREATIVE) {
			this.creativeInventory.setItemsFromInventory(player.getInventory());
			this.creativeInventory.setPotionEffectsFromPlayer(player);
		} else if(gm == GameMode.ADVENTURE) {
			this.adventureInventory.setItemsFromInventory(player.getInventory());
			this.adventureInventory.setPotionEffectsFromPlayer(player);
		} else if(gm == GameMode.SPECTATOR) {
			this.spectatorInventory.setItemsFromInventory(player.getInventory());
			this.spectatorInventory.setPotionEffectsFromPlayer(player);
		}
	}
	/**
	 * 
	 * This method set items in current gamemode of player
	 */
	public void setPlayerGamemodeInventory(GameMode gm,Player player) {
		if(gm == GameMode.SURVIVAL) {
			this.survivalInventory.setItemsInPlayer(player);
			this.survivalInventory.setEffectsInPlayer(player);
		} else if(gm == GameMode.CREATIVE) {
			this.creativeInventory.setItemsInPlayer(player);
			this.creativeInventory.setEffectsInPlayer(player);
		} else if(gm == GameMode.ADVENTURE) {
			this.adventureInventory.setItemsInPlayer(player);
			this.adventureInventory.setEffectsInPlayer(player);
		} else if(gm == GameMode.SPECTATOR) {
			this.spectatorInventory.setItemsInPlayer(player);
			this.spectatorInventory.setEffectsInPlayer(player);
		}
	}
	
	/**
	 * 
	 * this method toggles the player's inverters
	 * @param curretGameMode
	 * @param newGameMode
	 */
	
	public void changeGameMode(GameMode curretGameMode, GameMode newGameMode, Player player) {
		
		if(this.lockGameModeChange==false) {
			this.lockGameModeChange=true;
			this.updateGamemodeInventoryItems(curretGameMode,player);
			player.getInventory().clear();
			this.removePlayerPotionEffect(player);
			this.setPlayerGamemodeInventory(newGameMode,player);
			this.lockGameModeChange=false;
		}
	}
	
	public void removePlayerPotionEffect(Player player) {
		Collection<PotionEffect> effects = player.getActivePotionEffects();
		for (PotionEffect effect : effects) {
		    player.removePotionEffect(effect.getType());
		}
	}
	
	public SerializeGamemodes serialize() {
		SerializeGamemodes sgi = new SerializeGamemodes();
		sgi.adventureInventory=this.adventureInventory.serialize();
		sgi.survivalInventory=this.survivalInventory.serialize();
		sgi.spectatorInventory=this.spectatorInventory.serialize();
		sgi.creativeInventory=this.creativeInventory.serialize();
		
		return sgi;
	}
	
	public static Gamemodes unserialize(SerializeGamemodes sg) {
		Gamemodes gms=new Gamemodes();
		gms.setAdventureInventory(GamemodeInventory.unserialize(sg.adventureInventory));
		gms.setCreativeInventory(GamemodeInventory.unserialize(sg.creativeInventory));
		gms.setSurvivalInventory(GamemodeInventory.unserialize(sg.survivalInventory));
		gms.setSpectatorInventory(GamemodeInventory.unserialize(sg.spectatorInventory));
		return gms;
		
	}
}
