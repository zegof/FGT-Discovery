package de.itsgraphax.fgtDiscovery.util;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlayerTransporter {
    public static void join(Player player, String server) {
        FgtDiscovery plugin = FgtDiscovery.getInstance();
        FileConfiguration config = plugin.getConfig();

        ConfigurationSection serverConfig = config.getConfigurationSection(String.format("servers.%s", server));
        if (serverConfig == null) {
            player.sendMessage(plugin.richText().fromConfig("server-not-exist"));
            return;
        }

        String ip = serverConfig.getString("ip");
        Integer port = serverConfig.getInt("port");
        String name = serverConfig.getString("name", server);
        ServerConnectionData connectionData = new ServerConnectionData(ip, port, name);
        if (connectionData.isEmpty()) {
            player.sendMessage(plugin.richText().fromConfig("server-config-incorrect"));
            return;
        }

        join(player, connectionData);
    }

    public static void join(Player player, ServerConnectionData connectionData) {
        FgtDiscovery plugin = FgtDiscovery.getInstance();

        player.sendMessage(plugin.richText().fromConfig("transferring", "SERVER", connectionData.name()));

        player.transfer(connectionData.ip(), connectionData.port());

        plugin.getServer().sendMessage(
                plugin.richText().fromConfig("transferred", "PLAYER", player.getName(), "SERVER", connectionData.name())
        );
    }

    public static void hub(Player player) {
        join(player, "hub");
    }
}
