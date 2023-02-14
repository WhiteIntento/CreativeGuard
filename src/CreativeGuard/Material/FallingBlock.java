package CreativeGuard.Material;

import java.util.Map;

import org.bukkit.Material;

public enum FallingBlock implements MaterialInterface {
	SAND,
	RED_SAND,
	GRAVEL,
	WHITE_CONCRETE_POWDER,
	LIGHT_GRAY_CONCRETE_POWDER,
	GRAY_CONCRETE_POWDER,
	BLACK_CONCRETE_POWDER,
	BROWN_CONCRETE_POWDER,
	RED_CONCRETE_POWDER,
	ORANGE_CONCRETE_POWDER,
	YELLOW_CONCRETE_POWDER,
	LIME_CONCRETE_POWDER,
	GREEN_CONCRETE_POWDER,
	CYAN_CONCRETE_POWDER,
	LIGHT_BLUE_CONCRETE_POWDER,
	BLUE_CONCRETE_POWDER,
	PURPULE_CONCRETE_POWDER,
	MAGENTA_CONCRETE_POWDER,
	PINK_CONCRETE_POWDER
	;
	
	public FallingPosition[] fallingPositions;
	protected static Map<String,MaterialInterface> map = MaterialUtils.createMap(FallingBlock.class);
	
	FallingBlock(){
		this.fallingPositions=new FallingPosition[6];
		int i=0;
		for(FallingPosition p : FallingPosition.values()) {
			this.fallingPositions[i]=p;
			i++;
		}
	}
	
	FallingBlock(FallingPosition[] fallingPositions){
		this.fallingPositions=fallingPositions;
	}
	FallingBlock(FallingPosition fallingPositions){
		this.fallingPositions= new FallingPosition[1];
		this.fallingPositions[0]=fallingPositions;
	}
	

	

	
	public static boolean is(String material) {
		return map.containsKey(material);
	}
	
	public static boolean is(Material material) {
		return is(material.name());
	}
	
	public static FallingBlock get(String material) {
		return (FallingBlock) map.get(material);
	}
	
	public static FallingBlock get(Material material) {
		return get(material.name());
	}
	
}
