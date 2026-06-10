package de.itsgraphax.fgtDiscovery.listeners;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import de.itsgraphax.fgtDiscovery.util.PlayerTransporter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

public class StopListener implements Listener {
    @EventHandler
    void onDisable(PluginDisableEvent e) {
        FgtDiscovery plugin = FgtDiscovery.getInstance();
        if (e.getPlugin() != plugin ||
                plugin.getServer().isStopping() ||
                !e.getPlugin().getConfig().getBoolean("hub-on-stop", false)) return;

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            PlayerTransporter.hub(p);
        }
    }
}
