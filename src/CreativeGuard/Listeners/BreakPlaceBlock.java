package CreativeGuard.Listeners;

import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
import CreativeGuard.Utils.BlockUtil;
import CreativeGuard.Utils.SurroundingBlocks;
import me.nome.BlockStorage.BlockContent;

public class BreakPlaceBlock implements Listener{

	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
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
			//This code stop falling items placed in water
			if(event.getBlockReplacedState().getType() == Material.WATER || event.getBlockReplacedState().getType() == Material.LAVA) {
				if(FallingMaterial.is(block.getType())) {
					event.setCancelled(true);
				}
			}
			BlockContent blockContent = ChunkRegister.getOrCreate(x, z).getOrCreateBlock(bx, bz, by);
			blockContent.set("o", player.getUniqueId().toString());
			blockContent.set("g", GamemodeInfo.getId(player.getGameMode()));
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
		if(ChunkRegister.getOrCreate(x, z) != null) {
			
			BlockContent blockContent = ChunkRegister.getOrCreate(x, z).getOrCreateBlock(bx, bz, by); //Use this method because is not sure if it is loaded from the file system
			if(blockContent != null) {
				if(checkTopIsFalling(block)) {
					event.setCancelled(true);
					return;
				}
				if(FallingMaterial.is(block.getType())==false && this.checkForFallingBlocks(block)) {
					if(this.checkForFallingCreativeBlocks(block)) {
						event.setCancelled(true);
						return;
					}
				}
				
				if(blockContent.get("o") != null && blockContent.get("g")!=null) {
					int gm = (int)blockContent.get("g");
					if(blockContent.get("o").equals(player.getUniqueId().toString())) {
						if(gm == 1 && GamemodeInfo.getId(player.getGameMode()) !=1) {
							ChunkRegister.get(x, z).removeBlock(bx, bz, by);
							event.getBlock().setType(Material.AIR);
						}

					} else {
						if(gm == 1) {
							ChunkRegister.get(x, z).removeBlock(bx, bz, by);
							event.getBlock().setType(Material.AIR);
						}
					}
				}
				
			}
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
