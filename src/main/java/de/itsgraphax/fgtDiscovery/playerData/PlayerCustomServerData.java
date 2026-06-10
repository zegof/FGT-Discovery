package de.itsgraphax.fgtDiscovery.playerData;

import de.itsgraphax.fgtDiscovery.util.ServerConnectionData;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

public class PlayerCustomServerData extends PlayerData {
    protected final int slot;
    protected @Nullable String ip;
    protected @Nullable Integer port;

    public PlayerCustomServerData(PersistentDataContainer pdc, int slot) {
        super(pdc);

        this.slot = slot;

        getValues();
    }

    public PlayerCustomServerData(Player p, int slot) {
        this(p.getPersistentDataContainer(), slot);
    }

    protected void getValues() {
        this.ip = pdc.get(this.namespaces.customServerIp(this.slot), PersistentDataType.STRING);
        this.port = pdc.get(this.namespaces.customServerPort(this.slot), PersistentDataType.INTEGER);
    }

    protected void setValues() {
        if (hasData()) {
            assert ip != null && port != null;
            pdc.set(this.namespaces.customServerIp(slot), PersistentDataType.STRING, ip);
            pdc.set(this.namespaces.customServerPort(slot), PersistentDataType.INTEGER, port);
        } else {
            pdc.remove(this.namespaces.customServerIp(slot));
            pdc.remove(this.namespaces.customServerPort(slot));
        }
    }

    public void server(@Nullable String ip, @Nullable Integer port) {
        this.ip = ip;
        this.port = (ip != null) ? port : null;
        setValues();
    }

    public @Nullable ServerConnectionData server() {
        getValues();

        if (hasData()) {
            assert port != null;
            return new ServerConnectionData(ip, port, String.format("Custom%s", slot));
        } else {
            return null;
        }
    }

    public boolean hasData() {
        return (ip != null && port != null);
    }
}
