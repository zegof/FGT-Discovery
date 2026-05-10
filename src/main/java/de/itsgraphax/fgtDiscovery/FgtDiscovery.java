package de.itsgraphax.fgtDiscovery;

import de.itsgraphax.fgtDiscovery.commands.HubBrigadier;
import de.itsgraphax.fgtDiscovery.commands.JoinBrigadier;
import de.itsgraphax.fgtDiscovery.commands.MainBrigadier;
import de.itsgraphax.fgtDiscovery.listeners.JoinQuitMessage;
import de.itsgraphax.fgtDiscovery.util.Namespaces;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FgtDiscovery extends JavaPlugin {
    private static FgtDiscovery instance;

    private ComponentLogger logger;
    private Namespaces namespaces;

    public void registerCommands() {
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event -> {
            Commands registrar = event.registrar();

            MainBrigadier.register(registrar);
            JoinBrigadier.register(registrar);

            if (getConfig().getBoolean("enable-hub", false)) {
                HubBrigadier.register(registrar);
            }
        }));
    }

    public void registerListeners() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinQuitMessage(), this);
    }

    @Override
    public void onEnable() {
        instance = this;
        logger = getComponentLogger();
        namespaces = new Namespaces(this);

        saveDefaultConfig();

        registerCommands();
        registerListeners();
    }

    public static FgtDiscovery getInstance() {
        return instance;
    }

    public ComponentLogger logger() {
        return logger;
    }

    public Namespaces getNamespaces() {
        return namespaces;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
