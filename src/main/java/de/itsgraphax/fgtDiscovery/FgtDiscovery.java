package de.itsgraphax.fgtDiscovery;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FgtDiscovery extends JavaPlugin {
    private static FgtDiscovery instance;

    public ComponentLogger logger;

    public void registerCommands() {
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event -> {

        }));
    }

    public void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
    }

    @Override
    public void onEnable() {
        instance = this;
        logger = getComponentLogger();

        registerCommands();
        registerListeners();
    }

    public static FgtDiscovery getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
