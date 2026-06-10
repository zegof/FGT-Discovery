package de.itsgraphax.fgtDiscovery.commands;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import de.itsgraphax.fgtDiscovery.playerData.PlayerCustomServerData;
import de.itsgraphax.fgtDiscovery.util.PlayerTransporter;
import de.itsgraphax.fgtDiscovery.util.ServerConnectionData;
import jdk.jfr.Description;
import net.strokkur.commands.Aliases;
import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;
import net.strokkur.commands.arguments.StringArg;
import net.strokkur.commands.paper.Executor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@Command("custom")
@Aliases("c")
@Description("Join or set a Custom server")

public class Custom {

    @Executes
    void join(CommandSender ignoredSender, @Executor Player player, int slot) {
        FileConfiguration config = FgtDiscovery.getInstance().getConfig();
        PlayerCustomServerData playerData = new PlayerCustomServerData(player, slot);

        if (!playerData.hasData()) {
            player.sendRichMessage(config.getString("strings.custom-no-data", ""));
            return;
        }

        ServerConnectionData connectionData = playerData.server();
        assert connectionData != null;
        PlayerTransporter.join(player, connectionData);
    }

    @Executes("remove")
    void remove(CommandSender ignoredSender, @Executor Player player, int slot) {
        FileConfiguration config = FgtDiscovery.getInstance().getConfig();
        PlayerCustomServerData playerData = new PlayerCustomServerData(player, slot);

        if (!playerData.hasData()) {
            player.sendRichMessage(config.getString("strings.custom-no-data", ""));
            return;
        }

        playerData.server(null, null);

        player.sendRichMessage(config.getString("strings.custom-deleted", ""));
    }

    @Executes("add")
    void add(CommandSender ignoredSender, @Executor Player player, @StringArg() String ip, int port, int slot) {
        FileConfiguration config = FgtDiscovery.getInstance().getConfig();
        PlayerCustomServerData playerData = new PlayerCustomServerData(player, slot);

        if (playerData.hasData()) {
            player.sendRichMessage(config.getString("strings.custom-already-data", ""));
            return;
        }

        playerData.server(ip, port);

        player.sendRichMessage(config.getString("strings.custom-added", ""));
    }

}
