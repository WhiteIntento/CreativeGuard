package CreativeGuard.Player;

import CreativeGuard.Main;

public class BlockPlayerPlace  extends PlayerPlace{

	public BlockPlayerPlace(String material) {
		super(material);
		// TODO Auto-generated constructor stub
	}

	@Override
	Integer getMaterialLimitPlace() {
		int l=Main.getPluginInstance().getConfig().getInt("LIMIT_PLAYER_PLACE_BLOCK."+this.material);
		if(l==0) {
			l=Main.getPluginInstance().getConfig().getInt("DEFAULT_LIMIT_BLOCK");
		}
		return Main.getPluginInstance().getConfig().getInt("LIMIT_PLAYER_PLACE_BLOCK."+this.material);
	}

}
