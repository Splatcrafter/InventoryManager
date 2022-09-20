package de.splatgames.clansystem.api.inventorymanager;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CustomItemInventoryCache {

    private final ConcurrentHashMap<InventoryManager, Set<CustomItem>> customItemHashMap;
    private static CustomItemInventoryCache instance = null;

    public CustomItemInventoryCache() {
        this.customItemHashMap = new ConcurrentHashMap<>();
        instance = this;
    }

    public ConcurrentHashMap<InventoryManager, Set<CustomItem>> getCustomItemHashMap() {
        return customItemHashMap;
    }

    public static CustomItemInventoryCache getInstance() {
        return instance;
    }

    public void addCustomItem(InventoryManager inventoryManager, CustomItem customItem) {
        Set<CustomItem> customItemSet = this.customItemHashMap.get(inventoryManager);
        if (customItemSet == null) {
            customItemSet = new HashSet<>();
        }
        customItemSet.add(customItem);
        this.customItemHashMap.put(inventoryManager, customItemSet);
    }
}
