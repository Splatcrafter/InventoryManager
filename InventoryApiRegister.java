package net.splatcrafter.clansystem.api.inventorymanager;

import de.splatcrafter.clansystem.ClanSystem;
import org.bukkit.Bukkit;

/**
 * This class is used to register the InventoryAPI.
 * It only registers the events and creates the cache.
 * This class is with the only one you need to register in your Main class.
 *
 * @author Splatcrafter
 * @version 1.0.0
 * @see InventoryListener
 * @see InventoryClickListener
 * @see CustomInventoryCache
 * @see CustomItemInventoryCache
 */
public class InventoryApiRegister {

    private static CustomInventoryCache customInventoryCache;
    private CustomItemInventoryCache customItemInventoryCache;
    private ClanSystem plugin;

    public InventoryApiRegister(ClanSystem plugin) {
        this.plugin = plugin;
        customInventoryCache = new CustomInventoryCache();
        customItemInventoryCache = new CustomItemInventoryCache();
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this.plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this.plugin);
    }

    public static CustomInventoryCache getCustomInventoryCache() {
        return customInventoryCache;
    }
}
