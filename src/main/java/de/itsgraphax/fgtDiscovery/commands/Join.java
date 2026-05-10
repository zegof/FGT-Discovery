package de.itsgraphax.fgtDiscovery.commands;

import de.itsgraphax.fgtDiscovery.util.PlayerTransporter;
import jdk.jfr.Description;
import net.strokkur.commands.Aliases;
import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;
import net.strokkur.commands.arguments.StringArg;
import net.strokkur.commands.paper.Executor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("join")
@Aliases("j")
@Description("Join another server")
public class Join {
    @Executes()
    void join(CommandSender ignoredSender, @Executor Player player, @StringArg String server) {
        PlayerTransporter.join(player, server);
    }
}
