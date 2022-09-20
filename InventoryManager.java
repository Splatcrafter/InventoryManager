package de.splatgames.clansystem.api.inventorymanager;

import de.splatgames.clansystem.clansystem.ClanSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This {@link org.bukkit.event.Event} is called when a {@link Player} clicks a slot in an {@link Inventory}.
 * <p>
 * This {@link org.bukkit.event.Event} is easier to use and simpler as the normal {@link org.bukkit.event.inventory.InventoryClickEvent}.
 *
 * @author Splatcrafter
 * @version InventoryManager 1.1
 */
public class InventoryManager {

    /**
     * Get the Inventory to set an {@link ItemStack} in the {@link Inventory}.
     * <p>
     * To set an {@link ItemStack} in the Inventory or get
     * the {@link Inventory} itself.
     *
     * @return the {@link Inventory}
     * @author Splatcrafter
     * @since InventoryManager 1.0
     */
    public Inventory getInventory() {
        return inv;
    }

    /**
     * Set the {@link CustomItem} with an integrated ClickEvent
     * <p>
     * Adds a ClickEvent to the {@link CustomItem} to
     * interact with it.
     * It uses the normal {@link org.bukkit.event.inventory.InventoryClickEvent}
     * from the BukkitAPI.
     *
     * @param customItem add the {@link CustomItem} to the {@link Inventory}
     *
     * @author Splatcrafter
     * @since InventoryManager 1.1
     */
    public void setItem(CustomItem customItem) {
        getInventory().setItem(customItem.slot, customItem.itemStack);
        CustomItemInventoryCache.getInstance().addCustomItem(this, customItem);
    }

    private String name;
    private int size;
    private Player player;

    public Boolean canceled;
    @SuppressWarnings("unused")
    private Inventory inv;
    private Listener listener;
    private Listener itemListener;

    public InventoryManager(Player player, int inventorySize, String inventoryName, Boolean isCanceled) {
        this.name = inventoryName;
        this.size = inventorySize;
        this.player = player;
        this.canceled = isCanceled;
        inv = Bukkit.createInventory(null, size, name);
        player.openInventory(inv);

        this.listener = new Listener() {
            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getPlayer() instanceof Player) {
                    Inventory inv = event.getInventory();
                    if (inv.equals(InventoryManager.this.inv)) {
                        destroy();
                    }
                }
            }

            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent event) {
                if (event.getPlayer().equals(getPlayer())) {
                    destroy();
                }
            }

            @EventHandler
            public void onPlayerKickEvent(PlayerKickEvent event) {
                if (event.getPlayer().equals(getPlayer())) {
                    destroy();
                }
            }
        };

        Bukkit.getPluginManager().registerEvents(listener, ClanSystem.getInstance());
        this.itemListener = new InventoryClickListener();
        Bukkit.getPluginManager().registerEvents(itemListener, ClanSystem.getInstance());
    }

    public InventoryManager(Player owner, Player player, int inventorySize, String inventoryName, Boolean isCanceled) {
        this.name = inventoryName;
        this.size = inventorySize;
        this.player = player;
        this.canceled = isCanceled;
        inv = Bukkit.createInventory(owner, size, name);
        player.openInventory(inv);
        this.listener = new Listener() {

            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getPlayer() instanceof Player) {
                    Inventory inv = event.getInventory();
                    if (inv.equals(InventoryManager.this.inv)) {
                        destroy();
                    }
                }
            }

            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent event) {
                if (event.getPlayer().equals(getPlayer())) {
                    destroy();
                }
            }

            @EventHandler
            public void onPlayerKickEvent(PlayerKickEvent event) {
                if (event.getPlayer().equals(getPlayer())) {
                    destroy();
                }
            }
        };

        Bukkit.getPluginManager().registerEvents(listener, ClanSystem.getInstance());
    }

    /**
     * Gets the {@link Player} who clicked the {@link Inventory}
     * <p>
     * Can only apply if an {@link Inventory} is created
     * and have an {@link org.bukkit.event.Event} in constructor.
     *
     * @return the {@link Player} who triggered the {@link org.bukkit.event.Event}
     *
     * @author Splatcrafter
     * @since  InventoryManager 1.0
     */

    public Player getPlayer() {
        return player;
    }

    /**
     * Destroys the active {@link Inventory}.
     * <p>
     * Unregister the {@link org.bukkit.event.inventory.InventoryClickEvent} and all
     * other {@link Listener} in the active session.
     * Can only apply manually.
     * Changes the variables to null.
     *
     * @author Splatcrafter
     * @since InventoryManager 1.0
     */
    public void destroy() {
        player = null;
        size = 0;
        name = null;

        HandlerList.unregisterAll(listener);
        HandlerList.unregisterAll(itemListener);

        listener = null;
        itemListener = null;
    }
}
