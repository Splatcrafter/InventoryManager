package de.splatgames.clansystem.api.inventorymanager;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItem {

    public final int slot;
    public final ItemStack itemStack;

    public CustomItem(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }

    public abstract void onClick(InventoryClickEvent event);

}
