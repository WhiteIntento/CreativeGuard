package CreativeGuard.Player;

import CreativeGuard.Main;

public class EntityPlayerPlace extends PlayerPlace{

	public EntityPlayerPlace(String material) {
		super(material);
		// TODO Auto-generated constructor stub
	}

	@Override
	Integer getMaterialLimitPlace() {
		int l=Main.getPluginInstance().getConfig().getInt("LIMIT_PLAYER_PLACE_ENTITY."+this.material);
		if(l==0) {
			l=Main.getPluginInstance().getConfig().getInt("DEFAULT_LIMIT_ENTITY");
		}
		return l;
	}

}
