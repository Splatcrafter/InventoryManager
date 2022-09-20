package de.splatgames.clansystem.api;

import de.splatgames.clansystem.clansystem.ClanSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * This {@link org.bukkit.event.Event} is called when a {@link Player} clicks a slot in an {@link Inventory}.
 * <p>
 * This {@link org.bukkit.event.Event} is easier to use and simpler as the normal {@link org.bukkit.event.inventory.InventoryClickEvent}.
 * <p>
 * Because {@link InventoryClickEvent} occurs within a modification of the {@link Inventory},
 * not all {@link Inventory} related methods are safe to use.
 */
public class InventoryManager {

    public interface InventoryClickEventHandler {
        void onInventoryClick(InventoryClickEvent event);
    }

    public Inventory getInventory() {
        return inv;
    }

    public class InventoryClickEvent {

        private Player owner;
        private String inventoryName;
        private String name = null;
        private final Player player;
        private Boolean close = true;
        private int rawSlot;
        private ItemStack itemStack;
        private ClickType clickType;

        public InventoryClickEvent(String inventoryName, int slot, ItemStack item, ClickType clickType, String name, Player player) {
            this.inventoryName = inventoryName;
            this.clickType = clickType;
            this.itemStack = item;
            this.rawSlot = slot;
            this.name = name;
            this.player = player;
        }

        @SuppressWarnings("unused")
        public InventoryClickEvent(String inventoryName, int slot, ItemStack item, ClickType clickType, Player player) {
            this.inventoryName = inventoryName;
            this.clickType = clickType;
            this.itemStack = item;
            this.rawSlot = slot;
            this.player = player;
        }

        public InventoryClickEvent(Player owner, String inventoryName, int slot, ItemStack item, ClickType clickType, String name, Player player) {
            this.owner = owner;
            this.inventoryName = inventoryName;
            this.clickType = clickType;
            this.itemStack = item;
            this.rawSlot = slot;
            this.name = name;
            this.player = player;
        }

        /**
         * Get the name of the {@link ItemStack} that is just clicked.
         * <p>
         * If the name is null it returns the name of the {@link ItemStack}.
         * Called by the variable itemStack#getType()#name() on the {@link org.bukkit.event.Event}.
         * If the {@link ItemStack} has a name it returns the exact name with color translation.
         *
         * @return the name of the {@link ItemStack}
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public String getName() {
            if (name != null) {
                return name;
            }
            return itemStack.getType().name();
        }

        /**
         * Get the owner of the {@link Inventory}.
         * <p>
         * If the owner is not accessible in the constellation of
         * the constructor it throws an {@link IllegalArgumentException} that says
         * that the owner cant accessed in this constructor.
         *
         * @return the owner of the {@link Inventory}
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public Player getOwner() {
            if (owner == null) {
                throw new IllegalArgumentException("Owner cannot accessed in in this constructor");
            }
            return owner;
        }

        /**
         * Gets the {@link Player} who clicked the {@link Inventory}.
         *
         * @return the {@link Player} who triggered the {@link org.bukkit.event.Event}
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public Player getPlayer() {
            return player;
        }

        /**
         * Gets if the {@link Inventory} is closing.
         * <p>
         * by default, it says true. It can set Manually
         * with event#setWillClose(true or false) on the {@link org.bukkit.event.Event} body.
         *
         * @return if the state of the {@link Inventory} is closing
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public boolean getWillClose() {
            return close;
        }

        /**
         * Get the {@link Inventory} who clicked by the {@link Player}.
         * <p>
         * It only returns the default {@link Inventory}.
         * The name can be separated by calling the event#getInventoryName()
         * function on the body of the constructor.
         *
         * @return the {@link Inventory}
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public Inventory getClickedInventory() {
            return inv;
        }

        /**
         * Get the name of the {@link Inventory}.
         * <p>
         * get the name with color code translation and full name.
         *
         * @return the name of the inventory
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public String getInventoryName() {
            return inventoryName;
        }

        /**
         * Get the rawSlot in the {@link Inventory}.
         * <p>
         * if the {@link Player} click a slot it returns it.
         *
         * @return the rawSlot int
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public Integer getRawSlot() {
            return rawSlot;
        }

        /**
         * Get the {@link ClickType} from the current {@link Inventory}.
         *
         * @return the {@link ClickType}
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public ClickType getClickType() {
            return clickType;
        }

        /**
         * Return if {@link Player} left-click the slot.
         * <p>
         * Cannot be applied on a slot with no {@link ItemStack} in it.
         * If the slot is null a {@link NullPointerException}
         * will be thrown.
         *
         * @return if {@link Player} left-clicks the slot
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public boolean isLeftClick() {
            return clickType == ClickType.LEFT;
        }

        /**
         * Return if {@link Player} right-click the slot.
         * <p>
         * Cannot be applied on a slot with no {@link ItemStack} in it.
         * If the slot is null a {@link NullPointerException}
         * will be thrown.
         *
         * @return if {@link Player} right-clicks the slot
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public boolean isRightClick() {
            return clickType == ClickType.RIGHT;
        }

        /**
         * Return if {@link Player} middle-click the slot.
         * <p>
         * Cannot be applied on a slot with no {@link ItemStack} in it.
         * If the slot is null a {@link NullPointerException}
         * will be thrown.
         *
         * @return if {@link Player} middle-clicks the slot
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public boolean isMiddleClick() {
            return clickType == ClickType.MIDDLE;
        }

        /**
         * Get the safe slot in the correct {@link Inventory}.
         * <p>
         * If the Slot is not safe it returns null.
         *
         * @return the slot in the correct {@link Inventory}
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public Integer getSafeSlot() {
            if (getInventory() == inv) {
                return rawSlot;
            }
            return null;
        }

        /**
         * Get the current {@link ItemStack}.
         * <p>
         * Getting the current {@link ItemStack} in the slot that is clicked.
         *
         * @return the current {@link ItemStack}
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public ItemStack getCurrentItem() {
            return inv.getItem(rawSlot);
        }

        /**
         * set the {@link ItemStack} that will be replaced with the current.
         *
         * @param itemStack set the {@link ItemStack}.
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public void setCurrentItem(ItemStack itemStack) {
            inv.setItem(rawSlot, itemStack);
        }

        /**
         * Set the close state of the {@link Inventory}.
         * <p>
         * The state must be true or false.
         * If the event#setWillClose() is true,
         * it will close the {@link Inventory} by calling
         * event#getWillClose or will return true.
         * Otherwise, it will return false.
         *
         * @param close set the boolean to true or false
         * @author Splatcrafter
         * @since InventoryManager 1.0
         */
        public void setWillClose(boolean close) {
            this.close = close;
        }

    }

    private String name;
    private int size;
    private Player player;

    private Boolean canceled;
    @SuppressWarnings("unused")
    private InventoryManager.InventoryClickEventHandler handler;
    private Inventory inv;
    private Listener listener;

    public InventoryManager(Player player, int inventorySize, String inventoryName, final InventoryClickEventHandler handler, Boolean isCanceled) {
        this.name = inventoryName;
        this.size = inventorySize;
        this.player = player;
        this.handler = handler;
        this.canceled = isCanceled;
        inv = Bukkit.createInventory(null, size, name);
        player.openInventory(inv);

        this.listener = new Listener() {
            @EventHandler
            private void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
                if (event.getWhoClicked() instanceof Player) {

                    if (event.getInventory().equals(inv)) {
                        if (canceled) {
                            event.setCancelled(true);
                        }
                        ItemStack item = event.getCurrentItem();
                        int slot = event.getRawSlot();
                        ClickType clickType = event.getClick();

                        String name = "";
                        if (item != null) {
                            if (item.hasItemMeta()) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta.hasDisplayName()) {
                                    name = meta.getDisplayName();
                                }
                            }
                        }

                        InventoryClickEvent clickEvent = new InventoryClickEvent(inventoryName, slot, item, clickType, name, player);

                        handler.onInventoryClick(clickEvent);
                    }
                }
            }

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

    public InventoryManager(Player owner, Player player, int inventorySize, String inventoryName, final InventoryClickEventHandler handler, Boolean isCanceled) {
        this.name = inventoryName;
        this.size = inventorySize;
        this.player = player;
        this.handler = handler;
        this.canceled = isCanceled;
        inv = Bukkit.createInventory(owner, size, name);
        player.openInventory(inv);

        this.listener = new Listener() {
            @EventHandler
            private void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
                if (event.getWhoClicked() instanceof Player) {

                    if (event.getInventory().equals(inv)) {
                        if (canceled) {
                            event.setCancelled(true);
                        }
                        ItemStack item = event.getCurrentItem();
                        int slot = event.getRawSlot();
                        ClickType clickType = event.getClick();

                        String name = "";
                        if (item != null) {
                            if (item.hasItemMeta()) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta.hasDisplayName()) {
                                    name = meta.getDisplayName();
                                }
                            }
                        }

                        InventoryClickEvent clickEvent = new InventoryClickEvent(owner, inventoryName, slot, item, clickType, name, player);

                        handler.onInventoryClick(clickEvent);
                    }
                }
            }

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
     */

    public Player getPlayer() {
        return player;
    }

    /**
     * Destroys the active {@link Inventory}.
     * <p>
     * Unregister the {@link InventoryClickEvent} and all
     * other {@link Listener} in the active session.
     * Can only apply manually.
     * Changes the variables to null.
     */
    public void destroy() {
        player = null;
        handler = null;
        size = 0;
        name = null;

        HandlerList.unregisterAll(listener);

        listener = null;
    }
}