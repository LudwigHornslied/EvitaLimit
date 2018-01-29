package com.tistory.hornslied.evitaonline.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tistory.hornslied.evitaonline.limit.GUI;
import com.tistory.hornslied.evitaonline.utils.Resources;

public class LimitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Resources.messageConsole);
			return true;
		}
		
		if(args.length > 0) {
			switch(args[0].toLowerCase()) {
			case "인챈트":
			case "enchant":
				((Player) sender).openInventory(GUI.ENCHANT);
				break;
			case "포션":
			case "potion":
				((Player) sender).openInventory(GUI.POTION);
				break;
			}
		} else {
			
		}
		
		return true;
	}

}
