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
		l.set("creative_place_entity_limit", "You have exceeded your entity placement limit");
		l.set("dont_permission_interact", "You are not allowed to interact with this item");
		l.set("dont_permission_place_block", "You are not allowed to place this block");
		l.set("dont_permission_build_iron_golem", "You are not allowed to build an iron golem");
		l.set("dont_permission_build_snow_golem", "You are not allowed to build snow golem");
		l.set("protect_build_falling_block_air", "You are not allowed to place blocks of this type on the air");
		l.set("dont_permission_use_lead", "You are not allowed to use lead");
		l.set("dont_permission_puckup_creative", "You are not allowed to take items in creative mode");
		l.set("dont_permission_creative_eat", "You are not allowed to eat in creative mode");
		l.set("dont_permission_to_shear_entity", "You are not allowed to shear entity");
		l.set("dont_permission_throw_trident", "You are not allowed to throw trident");
		l.set("dont_permission_use_armor_stand", "You are not allowed to use armor stand");
		l.set("dont_permission_use_arrow_instruments", "You are not allowed to use arrow instruments");
		l.set("dont_permission_drop_potions", "You are not allowed to drop potions in creative mode");
		l.set("dont_permission_place_frame", "You are not allowed to place item in frame in creative mode");
		l.set("dont_permission_spawn_egg", "You are not allowed to spawn egg in creative mode");
		l.set("dont_permission_set_egg_in_spawner", "You are not allowed to set egg in spawner in creative mode");
		l.set("dont_permission_place_custom_block", "You are not allowed to place custom blocks in creative mode");
		l.set("dont_permission_creative_attack", "You are not allowed to attack in creative mode");
		l.set("dont_permission_open_inventory", "You are not allowed to open inventory in creative mode");
		l.set("dont_permission_send_command", "You are not allowed to send this command in creative mode");
		l.set("dont_permission_use_bucket", "You are not allowed to use buckets in creative mode");
		l.set("start_message", "has been started successful. (The right approach is light)");
		l.set("plugin_reload_successful", "Plugin has been reloaded successful");
		l.set("dont_permission_access_command", "You are not allowed to use this command");
		if(!l.getConfig().getKeys(false).isEmpty()) {
			l.getConfigHandler().save();
		}
	}
}
