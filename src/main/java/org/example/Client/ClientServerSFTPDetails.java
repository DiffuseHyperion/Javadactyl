package org.example.Client;

import org.json.simple.JSONObject;

public class ClientServerSFTPDetails {
    private final String ip;
    private final Long port;

    public ClientServerSFTPDetails(JSONObject object) {
        ip = (String) object.get("ip");
        port = (Long) object.get("port");
    }

    public String getIp() {
        return ip;
    }

    public Long getPort() {
        return port;
    }
}
