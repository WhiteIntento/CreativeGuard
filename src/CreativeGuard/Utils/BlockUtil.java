package CreativeGuard.Utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BlockUtil {
	
	//Not used for moment
	public static boolean hasRelations(Block block) {
		boolean has = false;
		for(BlockFace bf : BlockFace.values()) {
			if(block.getRelative(bf).getType() != Material.AIR) {
				has=true;
			}
		}
		return has;
	}
}
