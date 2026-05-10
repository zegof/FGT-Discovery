package de.itsgraphax.fgtDiscovery.commands;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import jdk.jfr.Description;
import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;
import net.strokkur.commands.paper.RequiresOP;
import org.bukkit.command.CommandSender;

@Command("discovery")
@Description("Operator Commands")
@RequiresOP
public class Main {
    @Executes("reload")
    void reload(CommandSender sender) {
        FgtDiscovery plugin = FgtDiscovery.getInstance();

        sender.sendRichMessage(plugin.getConfig().getString("strings.config-reloaded", ""));
        plugin.reloadConfig();
    }
}
