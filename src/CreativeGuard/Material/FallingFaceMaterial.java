package CreativeGuard.Material;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;

public enum FallingFaceMaterial implements MaterialInterface{
	TORCH,
	SOUL_TORCH,
	REDSTONE_TORCH;
	protected static Map<String,MaterialInterface> map = MaterialUtils.createMap(FallingFaceMaterial.class);
	
	public static boolean is(String name) {
		return map.containsKey(name);
	}
	
	public static boolean is(Material material) {
		return is(material.name());
	}
	
	public static FallingFaceMaterial get(String name) {
		return (FallingFaceMaterial) map.get(name);
	}
	
	public static FallingFaceMaterial get(Material material) {
		return get(material.name());
	}
}
