package CreativeGuard.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerRegister {
	protected static Map<String,Player> players = new HashMap<String,Player>();
	
	public static Player getPlayer(String uuid) {
		return players.get(uuid);
	}
	
	public static Player getPlayer(org.bukkit.entity.Player player) {
		return getPlayer(player.getUniqueId().toString());
	}
	
	public static Player getOrCreatePlayer(org.bukkit.entity.Player player) {
		Player p = getPlayer(player);
		if(p == null) {
			registerPlayer(player);
			p = getPlayer(player);
		}
		return p;
	}
	
	public static void registerPlayer(org.bukkit.entity.Player player) {
		players.put(player.getUniqueId().toString(), new Player(player));
	}
	
	public static void removePlayer(String uuid) {
		Player p=getPlayer(uuid);
		if(p!=null) {
			p.save();
			players.remove(uuid);
		}
	}
	
	public static void removePlayer(org.bukkit.entity.Player player) {
		removePlayer(player.getUniqueId().toString());
	}
}
