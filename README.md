# InventoryManager

A simple InventoryManager with integrated InventoryClickEvent.

## Installation

Drop the InventoryManager.java in a package and change all import errors.

## API-Usage

Main-Class:
```java
package YourPackage;

import de.splatgames.identification.api.Identification;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class yourMainClass extends JavaPlugin {

    private static yourMainClass plugin;
    
    @Override
    public void onEnable() {
        this = plugin;
        
        //your code...
        
        getCommand("CommandName").setExecutor(new yourCommand());
    }

    @Override
    public void onDisable() {
        //your code...
    }

    public yourMainClass getInstance() {
        return plugin;
    }
}
```

CMD-Class:
```java
package YourPackage;

import YourPackage.yourMain;
import YourPackage.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class yourCMD implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        InventoryManager inventory = new InventoryManager(player, size, name, new InventoryManager.InventoryClickEventHandler() {
            @Override
            public void onInventoryClick(InventoryManager.InventoryClickEvent event) {
                //YourCode
            }
        }, true);
        
        return false;
    }
}
```
