package de.itsgraphax.fgtDiscovery.commands;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import de.itsgraphax.fgtDiscovery.serverInfo.PlayerSample;
import de.itsgraphax.fgtDiscovery.serverInfo.ServerInfo;
import de.itsgraphax.fgtDiscovery.util.ServerConnectionData;
import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;
import net.strokkur.commands.paper.Description;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Command("playerlist")
@Description("Get the list of online players on a server")
public class PlayerList {
    @Executes()
    void playerList(CommandSender sender, @Join.JoinServerSuggestions String server) {
        FgtDiscovery plugin = FgtDiscovery.getInstance();
        FileConfiguration config = plugin.getConfig();

        ConfigurationSection serverConfig = config.getConfigurationSection(String.format("servers.%s", server));
        if (serverConfig == null) {
            sender.sendMessage(plugin.richText().fromConfig("server-not-exist"));
            return;
        }

        String ip = serverConfig.getString("ip");
        Integer port = serverConfig.getInt("port");
        String name = serverConfig.getString("name", server);
        ServerConnectionData connectionData = new ServerConnectionData(ip, port, name);
        if (connectionData.isEmpty()) {
            sender.sendMessage(plugin.richText().fromConfig("server-config-incorrect"));
            return;
        }

        ServerInfo serverInfo;
        try {
            serverInfo = ServerInfo.request(connectionData);
        } catch (IOException e) {
            sender.sendMessage(plugin.richText().fromConfig("playerlist-no-respond"));
            plugin.getComponentLogger().warn("error requesting server status: {}", String.valueOf(e));
            return;

        }
        List<PlayerSample> sample = serverInfo.sample();
        if (sample == null) {
            sample = new ArrayList<>();
        }


        sender.sendMessage(plugin.richText().fromConfig("playerlist-list",
                "SERVER", connectionData.name(),
                "PLAYERS", serverInfo.players().toString(),
                "ONLINE_PLAYERS", Objects.requireNonNull(
                        sample.stream()
                        .map(PlayerSample::name)
                        .collect(Collectors.joining("\n    ")),
                        "")
        ));



    }

}
