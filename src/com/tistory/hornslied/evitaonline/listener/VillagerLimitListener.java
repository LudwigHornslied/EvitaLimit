package com.tistory.hornslied.evitaonline.listener;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class VillagerLimitListener implements Listener {

	@EventHandler
	public void onBreed(CreatureSpawnEvent e) {
		if(e.getEntity() instanceof Villager && e.getSpawnReason() == SpawnReason.BREEDING)
			e.setCancelled(true);
	}
}
