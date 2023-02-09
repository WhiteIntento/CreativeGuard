package CreativeGuard.Utils;


import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class SurroundingBlocks {
	protected Block[] blocks;
	
	public SurroundingBlocks(Block block){
		this.blocks=new Block[5];
		BlockFace[] bf = new BlockFace[5];
		bf[0]=BlockFace.NORTH;
		bf[1]=BlockFace.EAST;
		bf[2]=BlockFace.SOUTH;
		bf[3]=BlockFace.WEST;
		bf[4]=BlockFace.UP;
		int i = 0;
		for(BlockFace bff : bf) {
			blocks[i]=block.getRelative(bff);
			i++;
		}
		
	}

	public Block[] getBlocks() {
		return blocks;
	}
}
