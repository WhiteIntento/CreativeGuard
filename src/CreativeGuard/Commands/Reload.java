package CreativeGuard.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import CreativeGuard.Main;

public class Reload implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("creativeguard.reload")) {
			sender.sendMessage("You don't have permission to access reload command");
			return true;
		}
		
		Main.getPluginInstance().reloadConfig();
		Main.getPluginInstance()._stopPlugin();
		Main.getPluginInstance().registerListeners();
		sender.sendMessage("Plugin has been reloaded sucessful");
		return true;
	}

}
