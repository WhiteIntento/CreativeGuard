package CreativeGuard.Player;

import java.util.List;
import java.util.Map;

import org.bukkit.GameMode;

public class SerializeGamemodeInventory {
	public  List<String> items;
	public List<Map<String,Object>> effects;
	public GameMode gamemode;
	public SerializeGamemodeInventory(List<String> items, GameMode gm, List<Map<String,Object>> effects){
		this.items=items;
		this.gamemode=gm;
		this.effects=effects;
	}

}
