package de.itsgraphax.fgtDiscovery.util;

public record ServerConnectionData(String ip, Integer port, String name) {
    public boolean isEmpty() {
        return (ip == null) || (port == null);
    }
}
