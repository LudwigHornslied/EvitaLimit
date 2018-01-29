package com.tistory.hornslied.evitaonline.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

public class EnchantLimitListener implements Listener {
	private HashMap<Enchantment, Integer> limits;
	private HashSet<Enchantment> prevents;

	public EnchantLimitListener() {
		limits = new HashMap<>();
		prevents = new HashSet<>();

		limits.put(Enchantment.PROTECTION_EXPLOSIONS, 2);
		limits.put(Enchantment.FIRE_ASPECT, 1);
		limits.put(Enchantment.PROTECTION_FIRE, 2);
		limits.put(Enchantment.ARROW_DAMAGE, 2);
		limits.put(Enchantment.PROTECTION_PROJECTILE, 2);
		limits.put(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		limits.put(Enchantment.DAMAGE_ALL, 1);

		prevents.add(Enchantment.BINDING_CURSE);
		prevents.add(Enchantment.VANISHING_CURSE);
		prevents.add(Enchantment.FROST_WALKER);
		prevents.add(Enchantment.KNOCKBACK);
		prevents.add(Enchantment.ARROW_KNOCKBACK);
		prevents.add(Enchantment.SWEEPING_EDGE);
		prevents.add(Enchantment.THORNS);
	}

	@EventHandler
	public void onAnvil(PrepareAnvilEvent e) {
		ItemStack result = e.getResult();
		Map<Enchantment, Integer> enchants = result.getEnchantments();

		if (!enchants.isEmpty()) {
			Set<Enchantment> keys = new HashSet<>(enchants.keySet());

			for (Enchantment enchant : keys) {
				if (prevents.contains(enchant)) {
					result.removeEnchantment(enchant);
				} else if (limits.containsKey(enchant) && enchants.get(enchant) > limits.get(enchant)) {
					result.addEnchantment(enchant, limits.get(enchant));
				}
			}
		}
	}

	@EventHandler
	public void onTrade(InventoryClickEvent e) {
		if (e.getInventory().getType() != InventoryType.MERCHANT || e.getSlotType() != SlotType.RESULT)
			return;
		ItemStack result = e.getCurrentItem();
		
		Map<Enchantment, Integer> enchants = result.getEnchantments();

		if (!enchants.isEmpty()) {
			Set<Enchantment> keys = new HashSet<>(enchants.keySet());

			for (Enchantment enchant : keys) {
				if (prevents.contains(enchant)) {
					result.removeEnchantment(enchant);
				} else if (limits.containsKey(enchant) && enchants.get(enchant) > limits.get(enchant)) {
					result.addEnchantment(enchant, limits.get(enchant));
				}
			}
		}
	}

	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		Set<Enchantment> keys = new HashSet<>(e.getEnchantsToAdd().keySet());
		for (Enchantment enchant : keys) {
			if (prevents.contains(enchant)) {
				e.getEnchantsToAdd().remove(enchant);
			} else if (limits.containsKey(enchant) && e.getEnchantsToAdd().get(enchant) > limits.get(enchant)) {
				e.getEnchantsToAdd().put(enchant, limits.get(enchant));
			}
		}
	}
	
	@EventHandler
    public void onChest(InventoryOpenEvent e){
        if (e.getInventory().getHolder() instanceof Chest || e.getInventory().getHolder() instanceof DoubleChest){
            for(ItemStack is : e.getInventory().getContents()) {
            	if(is != null) {
            		Map<Enchantment, Integer> enchants = is.getEnchantments();
            		
            		if(is.getItemMeta().hasLore())
            			return;

            		if (!enchants.isEmpty()) {
            			Set<Enchantment> keys = new HashSet<>(enchants.keySet());

            			for (Enchantment enchant : keys) {
            				if (prevents.contains(enchant)) {
            					is.removeEnchantment(enchant);
            				} else if (limits.containsKey(enchant) && enchants.get(enchant) > limits.get(enchant)) {
            					is.addUnsafeEnchantment(enchant, limits.get(enchant));
            				}
            			}
            		}
            	}
            }
        }
    }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		for(ItemStack is : e.getPlayer().getInventory()) {
			if(is != null) {
        		Map<Enchantment, Integer> enchants = is.getEnchantments();
        		
        		if(is.getItemMeta().hasLore())
        			return;

        		if (!enchants.isEmpty()) {
        			Set<Enchantment> keys = new HashSet<>(enchants.keySet());

        			for (Enchantment enchant : keys) {
        				if (prevents.contains(enchant)) {
        					is.removeEnchantment(enchant);
        				} else if (limits.containsKey(enchant) && enchants.get(enchant) > limits.get(enchant)) {
        					is.addEnchantment(enchant, limits.get(enchant));
        				}
        			}
        		}
        	}
		}
	}
}
