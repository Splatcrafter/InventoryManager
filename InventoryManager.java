package net.splatcrafter.clansystem.api.inventorymanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

/**
 * This class is the heart of the InventoryManager API.
 * It is used to create a new inventory for a player.
 * It is also used to add items to the inventory.
 *
 * @author Splatcrafter
 * @version 1.0.0
 * @see InventoryApiRegister
 */
public class InventoryManager {

    private String name;
    private int size;
    private UUID uuid;
    public boolean canceled;
    private Inventory inv;

    /**
     * This constructor is used to create a new inventory for a player.
     *
     * @param player   The player who should get the inventory.
     * @param size     The size of the inventory.
     * @param name     The name of the inventory.
     * @param canceled If the inventory should be canceled.
     * @since 1.0.0
     */
    public InventoryManager(Player player, int size, String name, boolean canceled) {
        this.size = size;
        this.name = name;
        this.uuid = player.getUniqueId();
        this.canceled = canceled;
        this.inv = Bukkit.createInventory(null, size, name);
        InventoryApiRegister.getCustomInventoryCache().addInventory(player, this);
        player.openInventory(inv);
    }

    /**
     * Returns the player who has the {@link Inventory}.
     *
     * @return The player who has the {@link Inventory}.
     * @since 1.0.0
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Returns the size of the {@link Inventory}.
     *
     * @return The size of the {@link Inventory}.
     * @since 1.0.0
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the name of the {@link Inventory}.
     *
     * @return The name of the {@link Inventory}.
     * @since 1.0.0
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the UUID of the {@link Inventory}.
     *
     * @return The UUID of the {@link Inventory}.
     * @since 1.0.0
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Returns if the {@link Inventory} is canceled.
     *
     * @return If the {@link Inventory} is canceled.
     * @since 1.0.0
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Returns the {@link Inventory}.
     *
     * @return The {@link Inventory}.
     * @since 1.0.0
     */
    public Inventory getInventory() {
        return inv;
    }

    /**
     * This method is used to set a {@link CustomItem} in the {@link Inventory}.
     * It also adds the {@link CustomItem} to the {@link CustomItemInventoryCache}.
     * If you want to add a {@link CustomItem} to the {@link Inventory} use {@link #addItem(CustomItem)}.
     *
     * @param customItem The {@link CustomItem} which should be set.
     * @see CustomItemInventoryCache
     * @see CustomItem
     * @since 1.0.0
     */
    public void setItem(CustomItem customItem) {
        getInventory().setItem(customItem.slot, customItem.itemStack);
        CustomItemInventoryCache.getInstance().addCustomItem(this, customItem);
    }

    /**
     * This method is used to add a {@link CustomItem} to the {@link Inventory}.
     * It also adds the {@link CustomItem} to the {@link CustomItemInventoryCache}.
     * If you want to set a {@link CustomItem} in the {@link Inventory} use {@link #setItem(CustomItem)}.
     *
     * @param customItem The {@link CustomItem} which should be added.
     * @see CustomItemInventoryCache
     * @see CustomItem
     * @since 1.0.0
     */
    public void addItem(CustomItem customItem) {
        getInventory().addItem(customItem.itemStack);
        CustomItemInventoryCache.getInstance().addCustomItem(this, customItem);
    }
}
