package CreativeGuard.Player;

import CreativeGuard.Main;

public abstract class PlayerPlace {
	protected String material;
	protected  Long expiry=(long) 0;
	protected Integer countPlace=0;
	
	public PlayerPlace(String material) {
		this.material=material;
	}
	
	public boolean canPlace() {
		if(this.getMaterialLimitPlace() == 0) {
			return true;
		}
		if(countPlace>=getMaterialLimitPlace()) {
			if(this.expiry > this.getTimeInCurrentUnit(System.currentTimeMillis())) {
				return false;
			} else {
				this.countPlace=0;
			}
		}
		return true;
	}
	
	public void place() {
		if(!this.canPlace()) {
			return;
		}
		this.expiry=this.getTimeInCurrentUnitPlusExpiryPeriod(System.currentTimeMillis());
		this.countPlace++;
	}
	
	public Long getTimeInCurrentUnitPlusExpiryPeriod(Long miliseconds) {
		switch(PlayerPlace.getCalculateTimeIn()) {
			case "SECOND":
				return this.timeInSecond(miliseconds) + 1;
			case "MINUTE":
				return this.timeInMinute(miliseconds) + 1;
			case "HOUR":
				return this.timeInHour(miliseconds) + 1;
			case "DAY":
				return this.timeInDay(miliseconds) + 1;
				
		}
		
		throw new IllegalArgumentException("Not supported time unit in "+ PlayerPlace.getCalculateTimeIn());
	}
	public Long getTimeInCurrentUnit(Long miliseconds) {
		switch(PlayerPlace.getCalculateTimeIn()) {
			case "SECOND":
				return this.timeInSecond(miliseconds);
			case "MINUTE":
				return this.timeInMinute(miliseconds);
			case "HOUR":
				return this.timeInHour(miliseconds);
			case "DAY":
				return this.timeInDay(miliseconds);
				
		}
		
		throw new IllegalArgumentException("Not supported time unit in "+ PlayerPlace.getCalculateTimeIn());
	}
	
	public Long timeInSecond(Long miliseconds) {
		return miliseconds/1000;
	}

	public long timeInMinute(Long miliseconds) {
		return timeInSecond(miliseconds)/60;
	}
	
	public Long timeInHour(Long miliseconds) {
		return timeInMinute(miliseconds)/60;
	}
	
	public Long timeInDay(Long miliseconds) {
		return timeInHour(miliseconds)/24;
	}
	
	public static String getCalculateTimeIn() {
		return Main.getPluginInstance().getConfig().getString("BLOCK_PLACE_TIME_INTERVAL_IN");
		//return "HORSE";
	}
	
	abstract Integer getMaterialLimitPlace();
}
