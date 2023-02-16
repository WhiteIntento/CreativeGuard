package CreativeGuard.Utils;

import CreativeGuard.Main;
import net.md_5.bungee.api.ChatColor;

public class ColorMessage {

	public static String get(String message) {
		String c="&";
		return ChatColor.translateAlternateColorCodes(c.toCharArray()[0], message);
	}
	
	public static String getWithTitle(String message) {
		return get(Main.getPluginInstance().getConfig().getString("MESSAGE_TITLE") + message);
	}
	
	public static String getLocale(String key) {
		return get(LocaleUtil.get(key));
	}
	
	public static String getLocaleWithTitle(String key) {
		return getWithTitle(getLocale(key));
	}
}
