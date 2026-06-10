package de.itsgraphax.fgtDiscovery;

import de.itsgraphax.fgtDiscovery.commands.CustomBrigadier;
import de.itsgraphax.fgtDiscovery.commands.HubBrigadier;
import de.itsgraphax.fgtDiscovery.commands.JoinBrigadier;
import de.itsgraphax.fgtDiscovery.commands.MainBrigadier;
import de.itsgraphax.fgtDiscovery.listeners.HubListener;
import de.itsgraphax.fgtDiscovery.listeners.JoinQuitMessage;
import de.itsgraphax.fgtDiscovery.listeners.StopListener;
import de.itsgraphax.fgtDiscovery.tick.HubTick;
import de.itsgraphax.fgtDiscovery.util.Namespaces;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class FgtDiscovery extends JavaPlugin {
    private static FgtDiscovery instance;

    private Namespaces namespaces;

    private static void accept(BukkitTask ignoredTask) {

        HubTick.tick();

    }

    private void registerCommands() {
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event -> {
            Commands registrar = event.registrar();

            MainBrigadier.register(registrar);
            JoinBrigadier.register(registrar);
            CustomBrigadier.register(registrar);

            if (getConfig().getBoolean("enable-hub", false)) {
                HubBrigadier.register(registrar);
            }
        }));
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinQuitMessage(), this);
        pm.registerEvents(new HubListener(), this);
        pm.registerEvents(new StopListener(), this);
    }

    private void startTickTasks() {
        getServer().getScheduler().runTaskTimer(this, FgtDiscovery::accept, 1, 1);
    }

    @Override
    public void onEnable() {
        instance = this;
        namespaces = new Namespaces();

        saveDefaultConfig();

        registerCommands();
        registerListeners();
        startTickTasks();
    }

    public static FgtDiscovery getInstance() {
        return instance;
    }

    public Namespaces getNamespaces() {
        return namespaces;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
