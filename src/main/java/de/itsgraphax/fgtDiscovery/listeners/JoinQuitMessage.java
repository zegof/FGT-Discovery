package de.itsgraphax.fgtDiscovery.listeners;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitMessage implements Listener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        if (FgtDiscovery.getInstance().getConfig().getBoolean("remove-join-msg", false)) {
            event.joinMessage(null);
        }
    }

    @EventHandler
    private void onPlayerLeave(PlayerQuitEvent event) {
        if (FgtDiscovery.getInstance().getConfig().getBoolean("remove-join-msg", false)) {
            event.quitMessage(null);
        }
    }
}
