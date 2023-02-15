package CreativeGuard.Config;

import java.io.IOException;

import CreativeGuard.Utils.LocaleUtil;

public class DefineDefaultLocales {

	public static void defineEnglishLocales() throws IOException {
		Locale l = LocaleUtil.getOrCreateLocale("en");
		l.set("creative_place_block_limit", "You have exceeded your block placement limit");
		l.set("creative_place_falling_item_water", "You cannot place an item of this type in water or lava");
		l.set("creative_break_top_first", "You cannot take this item, you must take the above item first");
		l.set("creative_break_falling_items", "There are falling objects around the item you are trying to pick up, you must remove them first");
		l.set("fatal_error_protect", "You cannot change your game mod because a fatal error has been caught. Please contact the server administrator to have it fixed");
		
		if(!l.getConfig().getKeys(false).isEmpty()) {
			l.getConfigHandler().save();
		}
	}
}
