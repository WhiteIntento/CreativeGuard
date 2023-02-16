package CreativeGuard.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import CreativeGuard.Main;
import CreativeGuard.Utils.PlayerUtil;

public class Reload implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("creativeguard.reload")) {
			PlayerUtil.sendLocaleMessage(sender, "dont_permission_access_command");
			return true;
		}
		
		Main.getPluginInstance().reloadConfig();
		Main.getPluginInstance()._stopPlugin();
		Main.getPluginInstance().registerListeners();
		PlayerUtil.sendLocaleMessage(sender, "plugin_reload_successful");
		return true;
	}

}
