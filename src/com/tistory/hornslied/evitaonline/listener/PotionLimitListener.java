package com.tistory.hornslied.evitaonline.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;

public class PotionLimitListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
		if (e.getItem().getType() == Material.POTION) {
			switch (((PotionMeta) e.getItem().getItemMeta()).getBasePotionData().getType()) {
			case INSTANT_DAMAGE:
			case INVISIBILITY:
			case WEAKNESS:
				e.setCancelled(true);
			default:
				break;
			}
		} else if(e.getItem().getType() == Material.GOLDEN_APPLE && e.getItem().getData().getData()== (byte) 1) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSplash(PotionSplashEvent e) {
		switch (((PotionMeta) e.getPotion().getItem().getItemMeta()).getBasePotionData().getType()) {
		case FIRE_RESISTANCE:
		case INSTANT_DAMAGE:
		case INSTANT_HEAL:
		case INVISIBILITY:
		case JUMP:
		case LUCK:
		case SPEED:
		case WATER_BREATHING:
		case WEAKNESS:
			e.setCancelled(true);
		default:
			break;
		}
	}
}
