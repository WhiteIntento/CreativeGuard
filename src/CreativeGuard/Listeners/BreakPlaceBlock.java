package CreativeGuard.Listeners;

import java.io.IOException;

import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import CreativeGuard.Main;
import CreativeGuard.Chunk.ChunkRegister;
import CreativeGuard.Material.FallingMaterial;
import CreativeGuard.Material.FallingPosition;
import CreativeGuard.Player.GamemodeInfo;
import CreativeGuard.Player.PlayerRegister;
import CreativeGuard.Utils.BlockUtil;
import CreativeGuard.Utils.LocaleUtil;
import CreativeGuard.Utils.PlayerUtil;
import CreativeGuard.Utils.SurroundingBlocks;
import me.nome.BlockStorage.BlockContent;

public class BreakPlaceBlock implements Listener{

	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) throws IOException {
		if(event.getPlayer().hasPermission("creativeguard.admin")) {
			return;
		}
		Block block = event.getBlock();
		Chunk chunk = block.getChunk();
		Player player = event.getPlayer();
		int x=chunk.getX();
		int z=chunk.getZ();
		int bx=block.getX();
		int bz=block.getZ();
		int by=block.getY();
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			if(!PlayerRegister.getOrCreatePlayer(player).canPlaceBlock(event.getBlock().getType().name())) {
				event.setCancelled(true);
				PlayerUtil.sendLocaleMessage(player, "creative_place_block_limit");
				return;
			}
			//This code stop falling items placed in water
			if(event.getBlockReplacedState().getType() == Material.WATER || event.getBlockReplacedState().getType() == Material.LAVA) {
				if(FallingMaterial.is(block.getRelative(BlockFace.UP).getType())) {
					event.setCancelled(true);
					PlayerUtil.sendLocaleMessage(player, "creative_place_falling_item_water");
					return;
				}
				if(FallingMaterial.is(block.getType())) {
					PlayerUtil.sendLocaleMessage(player, "creative_place_falling_item_water");
					event.setCancelled(true);
					return;
				}
			}
			BlockContent blockContent = ChunkRegister.getOrCreate(x, z).getOrCreateBlock(bx, bz, by);
			blockContent.set("o", player.getUniqueId().toString());
			blockContent.set("g", GamemodeInfo.getId(player.getGameMode()));
		} else {
			ChunkRegister.getOrCreate(x, z).removeBlock(bx, bz, by);
		}
		
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getPlayer().hasPermission("creativeguard.admin")) {
			return;
		}
		Block block = event.getBlock();
		Chunk chunk = block.getChunk();
		Player player = event.getPlayer();
		int x=chunk.getX();
		int z=chunk.getZ();
		int bx=block.getX();
		int bz=block.getZ();
		int by=block.getY();
		BlockContent blockContent = ChunkRegister.getOrCreate(x, z).getOrCreateBlock(bx, bz, by); //Use this method because is not sure if it is loaded from the file system
		if(checkTopIsFalling(block)) {
			player.sendMessage(LocaleUtil.get("creative_break_top_first"));
			event.setCancelled(true);
			return;
		}
		if(FallingMaterial.is(block.getType())==false && this.checkForFallingBlocks(block)) {
			if(this.checkForFallingCreativeBlocks(block)) {
				player.sendMessage(LocaleUtil.get("creative_break_falling_items"));
				event.setCancelled(true);
				return;
			}
		}
		
		if(blockContent.get("o") != null && blockContent.get("g")!=null) {
			ChunkRegister.get(x, z).removeBlock(bx, bz, by);
			event.getBlock().setType(Material.AIR);
		}
	}
	
	private BlockContent getBlockContent(Block block) {
		Chunk chunk = block.getChunk();
		BlockContent blockContent = ChunkRegister.getOrCreate(chunk.getX(), chunk.getZ()).getOrCreateBlock(block.getX(), block.getZ(), block.getY());
		return blockContent;
	}
	
	private boolean checkBlockIfPlacedFromCreative(Block block) {
		if(this.getBlockContent(block).get("g") != null) {
			return true;
		}
		return false;
	}
	
	private boolean checkForFallingCreativeBlocks(Block block) {
		SurroundingBlocks sb= new SurroundingBlocks(block);
		for(Block b : sb.getBlocks()) {
			if(FallingMaterial.is(b.getType())) {
				if(checkBlockIfPlacedFromCreative(b)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	private boolean checkForFallingBlocks(Block block) {
		SurroundingBlocks sb= new SurroundingBlocks(block);
		for(Block b : sb.getBlocks()) {
			if(FallingMaterial.is(b.getType())) {
				return true;
			}
		}
		return false;
	}
	private boolean checkTopIsFalling(Block b) {
		FallingPosition p = FallingPosition.TOP;
		return FallingMaterial.is(p.getBlock(b).getType()) && this.checkBlockIfPlacedFromCreative(p.getBlock(b));
	}
	
	
}
