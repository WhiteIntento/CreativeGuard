package CreativeGuard.Listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import CreativeGuard.Utils.SurroundingBlocks;

public class DebugListeners implements Listener{
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		String material=event.getBlock().getType().name();
		Player player=event.getPlayer();
		
		player.sendMessage("Place: " + material);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		String material=event.getMaterial().name();
		Player player=event.getPlayer();
		
		player.sendMessage("Interact: " + material);
	}
	
	@EventHandler
	public void onPlayerBreak(BlockBreakEvent event) {
		String material=event.getBlock().getType().name();
		Player player=event.getPlayer();
		
		player.sendMessage("Break: " + material);
	}
	
	//@EventHandler
	public void onPlayerBreakGetSurroundingBlocks(BlockBreakEvent event) {
		SurroundingBlocks sb = new SurroundingBlocks(event.getBlock());
		
		for(Block b : sb.getBlocks()) {
			event.getPlayer().sendMessage(b.getType().name());
		}
	}
}
