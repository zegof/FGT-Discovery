package de.itsgraphax.fgtDiscovery.util;

import de.itsgraphax.fgtDiscovery.FgtDiscovery;
import de.itsgraphax.grphxLib.utils.PdcDataBase;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

public class PdcData extends PdcDataBase {
    private final Namespaces namespaces = FgtDiscovery.getInstance().namespaces();

    public PdcData() {
        super();
    }

    public void server(PersistentDataHolder holder, ServerConnectionData data, int slot) {
        PersistentDataContainer pdc = pdc(holder);
        pdc.set(namespaces.customServerIp(slot), PersistentDataType.STRING, data.ip());
        pdc.set(namespaces.customServerPort(slot), PersistentDataType.INTEGER, data.port());
    }

    public void deleteServer(PersistentDataHolder holder, int slot) {
        PersistentDataContainer pdc = pdc(holder);
        pdc.remove(namespaces.customServerIp(slot));
        pdc.remove(namespaces.customServerPort(slot));
    }

    public ServerConnectionData server(PersistentDataHolder holder, int slot) {
        PersistentDataContainer pdc = pdc(holder);
        return new ServerConnectionData(
                pdc.get(namespaces.customServerIp(slot), PersistentDataType.STRING),
                pdc.get(namespaces.customServerPort(slot), PersistentDataType.INTEGER),
                String.format("Custom%s", slot)
        );
    }
}
