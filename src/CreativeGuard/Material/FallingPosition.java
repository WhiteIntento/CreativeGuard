package CreativeGuard.Material;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public enum FallingPosition {
	lEFT(-1,0,0),
	RIGHT(+1,0,0),
	TOP(0,+1,0),
	BOTTOM(0,-1,0),
	FORWARD(0,0, +1), //НАПРЕД
	BACK(0,0,-1);
	
	public int x,y,z;
	FallingPosition(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Block getBlock(Block block) {
		World world = block.getWorld();
		int x=block.getX();
		int z= block.getZ();
		int y=block.getY();
		return world.getBlockAt(new Location(world,x+this.x,y+this.y,z+this.z));
	}
	
}
