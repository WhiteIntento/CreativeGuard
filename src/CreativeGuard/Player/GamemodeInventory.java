package CreativeGuard.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import CreativeGuard.Main;
import CreativeGuard.Utils.ItemStackSerializer;

public class GamemodeInventory {
	public List<ItemStack> items;
	public List<PotionEffect> effects;
	protected GameMode gamemode;
	GamemodeInventory(GameMode gamemode){
		this.items=new ArrayList<ItemStack>();
		this.effects=new ArrayList<PotionEffect>();
		this.gamemode=gamemode;
	}
	
	public void setItemsFromInventory(Inventory invetory){
		this.items.clear();
		for(int i=0;i<invetory.getSize(); i++) {
			this.items.add(i, invetory.getItem(i));
		}
	}
	
	public void setItemsFromInventory(PlayerInventory pi) {
		this.items.clear();
		for(int i=0;i<pi.getSize(); i++) {
			this.items.add(i, pi.getItem(i));
		}
	}
	
	public void setPotionEffectsFromPlayer(Player p) {
		this.effects.clear();
		Collection<PotionEffect> effects = p.getActivePotionEffects();
		for (PotionEffect effect : effects) {
		    this.effects.add(effect);
		}
	}

	
	/*
	 * 
	 * This method set items in player inventory
	 */
	public void setItemsInPlayer(Player p) {
		PlayerInventory pi = p.getInventory();
		for(int i=0; i<this.items.size(); i++) {
			pi.setItem(i, this.items.get(i));
		}
	}
	
	public void setEffectsInPlayer(Player p) {
		for(int i=0; i < effects.size(); i++) {
			p.addPotionEffect(effects.get(i));
		}
	}

	
	public SerializeGamemodeInventory serialize() {
		List<String> items=new ArrayList<String>();
		for(int i=0; i<this.items.size(); i++) {
			if(this.items.get(i)!=null) {
				items.add(i,ItemStackSerializer.serializeToBase64(this.items.get(i)));
			} else {
				items.add(i, null);
			}
		}
		List<Map<String,Object>> effects = new ArrayList<Map<String,Object>>();
		for(int i=0; i < this.effects.size(); i++) {
			PotionEffect effect = this.effects.get(i);
			Map<String, Object> effectMap=new HashMap<String,Object>();
			effectMap.put("type", effect.getType().getName());
			effectMap.put("duration", effect.getDuration());
			effectMap.put("amplifier", effect.getAmplifier());
			effects.add(effectMap);
		}
		return new SerializeGamemodeInventory(items,this.gamemode,effects);
	}
	
	public static GamemodeInventory unserialize(SerializeGamemodeInventory sgi) {
		GamemodeInventory gm =new GamemodeInventory(sgi.gamemode);
		for(int i=0;i<sgi.effects.size(); i++) {
			Map<String,Object> effectMap = sgi.effects.get(i);
			PotionEffectType pet=PotionEffectType.getByName((String) effectMap.get("type"));
			Object duration = effectMap.get("duration");
			Object amplifier = effectMap.get("amplifier");
			int d=0;
			int a=0;
			if(duration instanceof Double) {
				d=(int)(double) duration;
			} else if(duration instanceof Integer) {
				d=(int)duration;
			} else if(duration instanceof String) {
				d=Integer.parseInt((String)duration);
			}
			if(amplifier instanceof Double) {
				a=(int)(double) amplifier;
			} else if(amplifier instanceof Integer) {
				a=(int)amplifier;
			} else if (amplifier instanceof String) {
				a=Integer.parseInt((String)amplifier);
			}
			
			gm.effects.add(new PotionEffect(pet,d,a));
		}
		for(int i=0; i<sgi.items.size(); i++) {
			if(sgi.items.get(i) != null) {
				gm.items.add(i,ItemStackSerializer.deserializeFromBase64(sgi.items.get(i)));
			} else {
				gm.items.add(i,new ItemStack(Material.AIR));
			}
			
		}
		return gm;
	}
	

}
