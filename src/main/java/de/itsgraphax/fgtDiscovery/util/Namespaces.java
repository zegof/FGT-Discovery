package de.itsgraphax.fgtDiscovery.util;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import org.bukkit.NamespacedKey;

public class Namespaces {
    private final FgtDiscovery plugin = FgtDiscovery.getInstance();

    public NamespacedKey customServerIp(int slot) {
        return new NamespacedKey(plugin, String.format("customServer.%s.ip", slot));
    }

    public NamespacedKey customServerPort(int slot) {
        return new NamespacedKey(plugin, String.format("customServer.%s.port", slot));
    }
}
