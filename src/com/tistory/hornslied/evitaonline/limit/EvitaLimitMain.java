package com.tistory.hornslied.evitaonline.limit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tistory.hornslied.evitaonline.commands.LimitCommand;
import com.tistory.hornslied.evitaonline.listener.EnchantLimitListener;
import com.tistory.hornslied.evitaonline.listener.PotionLimitListener;

public class EvitaLimitMain extends JavaPlugin {
	public static EvitaLimitMain instance;
	
	public static EvitaLimitMain getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		registerEvents();
		initCommands();
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new EnchantLimitListener(), this);
		pm.registerEvents(new PotionLimitListener(), this);
	}
	
	private void initCommands() {
		getCommand("limit").setExecutor(new LimitCommand());
	}
}
