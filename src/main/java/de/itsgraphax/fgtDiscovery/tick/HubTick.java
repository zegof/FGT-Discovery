package de.itsgraphax.fgtDiscovery.tick;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import de.itsgraphax.fgtDiscovery.util.HubUtil;
import org.bukkit.entity.Player;

public class HubTick {
    public static void tick() {
        FgtDiscovery.getInstance().getServer().getOnlinePlayers().forEach((Player p) -> {
            if (HubUtil.isAdventure(p) && HubUtil.hubEnabled()) {
                p.getInventory().clear();
                p.setHealth(20);
                p.setSaturation(20);
                p.setFoodLevel(20);

                if (p.getLocation().getY() < -64) p.setHealth(0);
            }
        });
    }
}
