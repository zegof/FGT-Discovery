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
            player.sendRichMessage(config.getString("strings.server-not-exist", ""));
            return;
        }

        String ip = serverConfig.getString("ip");
        Integer port = serverConfig.getInt("port");
        String name = serverConfig.getString("name", server);
        if (ip == null || port == null) {
            player.sendRichMessage(config.getString("strings.server-config-incorrect", ""));
            return;
        }
        ServerConnectionData connectionData = new ServerConnectionData(ip, port, name);

        join(player, connectionData);
    }

    public static void join(Player player, ServerConnectionData connectionData) {
        FgtDiscovery plugin = FgtDiscovery.getInstance();
        FileConfiguration config = plugin.getConfig();

        player.sendRichMessage(config.getString("strings.transferring", "")
                .replace("{{SERVER}}", connectionData.name()));

        player.transfer(connectionData.ip(), connectionData.port());

        plugin.getServer().sendRichMessage(
                config.getString("strings.transferred", "")
                        .replace("{{PLAYER}}", player.getName())
                        .replace("{{SERVER}}", connectionData.name())
        );
    }

    public static void hub(Player player) {
        join(player, "hub");
    }
}
