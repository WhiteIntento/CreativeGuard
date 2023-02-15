package CreativeGuard.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import CreativeGuard.Material.FallingBlock;
import CreativeGuard.Material.FallingMaterial;
import CreativeGuard.Utils.LocaleUtil;

public class PreventCreativeFallingBlocksCheat implements Listener{
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			String material = event.getBlock().getType().name();
			if(!event.getPlayer().hasPermission("creativeguard.place_falling."+material) && !event.getPlayer().hasPermission("creativeguard.admin")) {
				
				this.preventPlaceFallingBlock(event);
			}
		}
	}
	
	public void preventPlaceFallingBlock(BlockPlaceEvent event) {
		Block block= event.getBlock();
		int x = block.getX();
		int z = block.getZ();
		int y = block.getY();
		World world = block.getWorld();
		Block underBlock= world.getBlockAt(new Location(world,x,y-1,z));

		if(underBlock.getType() ==  Material.AIR  || underBlock.getType() == Material.SNOW) {
			if(FallingBlock.is(block.getType())) {
				event.getPlayer().sendMessage(LocaleUtil.get("protect_build_falling_block_air"));
				event.setCancelled(true);
			}
			
		}
	}

}
