package CreativeGuard.Listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import CreativeGuard.Main;
import CreativeGuard.Chunk.ChunkRegister;
import CreativeGuard.Material.FallingMaterial;
import CreativeGuard.Utils.SurroundingBlocks;
import me.nome.BlockStorage.BlockContent;

public class PreventCreativeCheatWithPiston implements Listener{
	
	@EventHandler
	public void onPistonExtend(BlockPistonExtendEvent event) {
		Chunk c = event.getBlock().getChunk();
		int cx=c.getX();
		int xy = c.getZ();
		Block b = event.getBlock();
		int bx=b.getX();
		int by=b.getY();
		int bz=b.getZ();
		BlockContent bc = ChunkRegister.getOrCreate(cx, xy).getOrCreateBlock(bx, bz, by);
		if(this.checkBlockFaceBlockIsFalling(event.getDirection(), event.getBlock())) {
			event.setCancelled(true);
		}
		if(this.checkSurroundingBlockIsFalling(event.getDirection(), event.getBlock())) {
			event.setCancelled(true);
		}
		
		
	}
	
	@EventHandler
	public void onPistonRetrect(BlockPistonRetractEvent event) {
		
	}
	
	
	public boolean checkBlockFaceBlockIsFalling(BlockFace bf,Block block) {
		World world = block.getWorld();
		int x= block.getX();
		int y = block.getY();
		int z = block.getZ();
		if(bf.getModX() != 0) {
			x=x+bf.getModX();
		} else if(bf.getModY() != 0) {
			y=y+bf.getModY();
		} else if(bf.getModZ() != 0) {
			z=z+bf.getModZ();
		}
		Location l = new Location(world,x,y,z);
		return FallingMaterial.is(world.getBlockAt(l).getType());
	}
	
	public boolean checkSurroundingBlockIsFalling(BlockFace bf,Block block) {
		World world = block.getWorld();
		int x= block.getX();
		int y = block.getY();
		int z = block.getZ();
		Block[] b= new Block[4];
		if(bf.getModX() != 0) {
			b[0]=this.getBlockByXYZ(world, x, y+1, z);
			b[1]=this.getBlockByXYZ(world, x, y-1, z);
			b[2]=this.getBlockByXYZ(world, x, y, z+1);
			b[3]=this.getBlockByXYZ(world, x, y, z-1);
			x=x+bf.getModX();
		} else if(bf.getModY() != 0) {
			b[0]=this.getBlockByXYZ(world, x+1, y, z);
			b[1]=this.getBlockByXYZ(world, x-1, y, z);
			b[2]=this.getBlockByXYZ(world, x, y, z+1);
			b[3]=this.getBlockByXYZ(world, x, y, z-1);
			y=y+bf.getModY();
		} else if(bf.getModZ() != 0) {
			b[0]=this.getBlockByXYZ(world, x+1, y, z);
			b[1]=this.getBlockByXYZ(world, x-1, y, z);
			b[2]=this.getBlockByXYZ(world, x, y+1, z);
			b[3]=this.getBlockByXYZ(world, x, y-1, z);
			z=z+bf.getModZ();
		}
		for(Block _b : b) {
			if(_b != null) {
				if(FallingMaterial.is(_b.getType()) && 
						ChunkRegister.getOrCreate(_b.getChunk().getX(), _b.getChunk().getZ()).getBlock(_b.getX(), _b.getZ(), _b.getY()) != null
						) {
					return true;
				}
			}
		}
		return false;
	}
	
	protected Block getBlockByXYZ(World world, int x, int y, int z) {
		return world.getBlockAt(new Location(world,x,y,z));
	}

}
