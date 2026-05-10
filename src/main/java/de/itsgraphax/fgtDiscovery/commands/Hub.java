package de.itsgraphax.fgtDiscovery.commands;

import de.itsgraphax.fgtDiscovery.util.PlayerTransporter;
import jdk.jfr.Description;
import net.strokkur.commands.Aliases;
import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;
import net.strokkur.commands.paper.Executor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("hub")
@Aliases("h")
@Description("Teleport back to the server hub")
public class Hub {
    @Executes()
    void hub(CommandSender ignoredSender, @Executor Player player) {
        PlayerTransporter.hub(player);
    }
}
