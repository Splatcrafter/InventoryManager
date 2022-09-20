package de.splatgames.clansystem.api.inventorymanager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Optional;
import java.util.Set;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                Optional<InventoryManager> inventoryManagerOptional = CustomItemInventoryCache.getInstance().getCustomItemHashMap().keySet().parallelStream().filter(inventoryManager -> inventoryManager.getInventory().getName().equals(event.getClickedInventory().getName())).findFirst();
                inventoryManagerOptional.ifPresent(inventoryManager -> {
                    event.setCancelled(inventoryManager.canceled);
                    Set<CustomItem> customItems = CustomItemInventoryCache.getInstance().getCustomItemHashMap().get(inventoryManager);
                    Optional<CustomItem> customItemOptional = customItems.parallelStream().filter(customItem -> event.getCurrentItem().equals(customItem.itemStack) && event.getSlot() == customItem.slot).findFirst();

                    customItemOptional.ifPresent(customItem -> customItem.onClick(event));
                });
            }
        }
    }
}
