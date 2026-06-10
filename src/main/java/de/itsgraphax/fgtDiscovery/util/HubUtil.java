package de.itsgraphax.fgtDiscovery.util;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

public class HubUtil {
    public static boolean hubEnabled() {
        return FgtDiscovery.getInstance().getConfig().getBoolean("hub.enabled", false);
    }

    public static boolean isAdventureEvent(PlayerEvent event) {
        return isAdventure(event.getPlayer());
    }

    public static boolean isAdventure(Player player) {
        return player.getGameMode() == GameMode.ADVENTURE;
    }
}
