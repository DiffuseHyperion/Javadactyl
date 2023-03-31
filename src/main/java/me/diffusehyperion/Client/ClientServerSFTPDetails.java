package me.diffusehyperion.Client;

import org.json.simple.JSONObject;

public class ClientServerSFTPDetails {
    private final String ip;
    private final int port;

    public ClientServerSFTPDetails(JSONObject object) {
        ip = (String) object.get("ip");
        port = ((Long) object.get("port")).intValue();
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
