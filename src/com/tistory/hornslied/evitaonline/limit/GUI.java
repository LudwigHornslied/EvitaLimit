package com.tistory.hornslied.evitaonline.limit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class GUI implements Listener {
	public static Inventory POTION;
	public static Inventory ENCHANT;
	
	static {
		Bukkit.getPluginManager().registerEvents(new GUI(), EvitaLimitMain.getInstance());
		
		POTION = Bukkit.createInventory(null, 18, ChatColor.YELLOW + ChatColor.BOLD.toString() + "금지 포션");
		POTION.addItem(newPotion(false, PotionType.INSTANT_DAMAGE));
		POTION.addItem(newPotion(false, PotionType.INVISIBILITY));
		POTION.addItem(newPotion(false, PotionType.WEAKNESS));
		POTION.addItem(newPotion(true, PotionType.FIRE_RESISTANCE));
		POTION.addItem(newPotion(true, PotionType.INSTANT_DAMAGE));
		POTION.addItem(newPotion(true, PotionType.INSTANT_HEAL));
		POTION.addItem(newPotion(true, PotionType.INVISIBILITY));
		POTION.addItem(newPotion(true, PotionType.JUMP));
		POTION.addItem(newPotion(true, PotionType.LUCK));
		POTION.addItem(newPotion(true, PotionType.SPEED));
		POTION.addItem(newPotion(true, PotionType.WATER_BREATHING));
		POTION.addItem(newPotion(true, PotionType.WEAKNESS));
		
		ENCHANT = Bukkit.createInventory(null, 18, ChatColor.YELLOW + ChatColor.BOLD.toString() + "제한 인챈트");
		ENCHANT.addItem(enchantBook("폭발로부터 보호: 2"));
		ENCHANT.addItem(enchantBook("발화: 1"));
		ENCHANT.addItem(enchantBook("화염으로부터 보호: 2"));
		ENCHANT.addItem(enchantBook("힘: 2"));
		ENCHANT.addItem(enchantBook("발사체로부터 보호: 2"));
		ENCHANT.addItem(enchantBook("보호: 2"));
		ENCHANT.addItem(enchantBook("날카로움: 1"));
		ENCHANT.addItem(enchantBook("귀속 저주: 비활성화"));
		ENCHANT.addItem(enchantBook("소실 저주: 비활성화"));
		ENCHANT.addItem(enchantBook("차가운 걸음: 비활성화"));
		ENCHANT.addItem(enchantBook("밀치기: 비활성화"));
		ENCHANT.addItem(enchantBook("밀어내기: 비활성화"));
		ENCHANT.addItem(enchantBook("휘몰아치는 칼날: 비활성화"));
		ENCHANT.addItem(enchantBook("가시: 비활성화"));
	}
	
	private static ItemStack newPotion(boolean splash, PotionType type) {
		ItemStack pot;
		
		if(splash) {
			pot = new ItemStack(Material.SPLASH_POTION);
		} else {
			pot = new ItemStack(Material.POTION);
		}
		
		PotionMeta pm = (PotionMeta) pot.getItemMeta();
		pm.setBasePotionData(new PotionData(type));
		pot.setItemMeta(pm);
		
		return pot;
	}
	
	private static ItemStack enchantBook(String name) {
		ItemStack enchantBook = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta meta = enchantBook.getItemMeta();
		meta.setDisplayName(name);
		enchantBook.setItemMeta(meta);
		return enchantBook;
	}
	
	private GUI() {
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		switch(e.getInventory().getName()) {
		case "§e§l금지 포션":
		case "§e§l제한 인챈트":
			e.setCancelled(true);
			break;
		}
	}
}
