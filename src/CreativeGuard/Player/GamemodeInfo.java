package CreativeGuard.Player;

import org.bukkit.GameMode;

public class GamemodeInfo {
	public static int getId(GameMode gm) {
		if(gm== GameMode.CREATIVE) {
			return 1;
		} else if(gm == GameMode.SURVIVAL) {
			return 0;
		} else if(gm == GameMode.ADVENTURE) {
			return 2;
		} else if(gm == GameMode.SPECTATOR) {
			return 3;
		}
		
		return -1;
	}
	
	public static boolean equalId(int gmId, GameMode gm) {
		return GamemodeInfo.getId(gm) == gmId;
	}
}
