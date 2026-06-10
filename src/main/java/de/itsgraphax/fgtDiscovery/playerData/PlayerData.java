package de.itsgraphax.fgtDiscovery.playerData;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import de.itsgraphax.fgtDiscovery.util.Namespaces;
import org.bukkit.persistence.PersistentDataContainer;

public abstract class PlayerData {
    protected final PersistentDataContainer pdc;
    protected final Namespaces namespaces;

    public PlayerData(PersistentDataContainer pdc) {
        this.pdc = pdc;
        this.namespaces = FgtDiscovery.getInstance().getNamespaces();
    }
}
