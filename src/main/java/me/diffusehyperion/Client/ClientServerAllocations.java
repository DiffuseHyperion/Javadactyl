package me.diffusehyperion.Client;

import me.diffusehyperion.Pair;
import org.json.simple.JSONObject;

public class ClientServerAllocations {
    private final ClientServer server;
    private final int id;
    private String ip;
    private String ipAlias;
    private int port;
    private String notes;
    private boolean isDefault;

    public ClientServerAllocations(ClientServer server, JSONObject object) {
        this.server = server;
        this.id = ((Long) object.get("id")).intValue();
        this.ip = (String) object.get("ip");
        this.ipAlias = (String) object.get("ip_alias");
        this.port = ((Long) object.get("port")).intValue();
        this.notes = (String) object.get("notes");
        this.isDefault = (Boolean) object.get("is_default");
    }

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getIpAlias() {
        return ipAlias;
    }

    public void setIpAlias(String ipAlias) {
        this.ipAlias = ipAlias;
    }

    public int getPort() {
        return port;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public int setNotes(String notes) {
        this.notes = notes;

        JSONObject output = new JSONObject();
        output.put("notes", notes);

        Pair<Integer, JSONObject> request = server.getClient().handleRequest(server.getClient().makeRequest(
                server.getClient().getHost() + "api/client/servers/" + server.getIdentifier() + "/network/allocations/" + id,
                "POST", server.getClient().getParameters(), output.toString()));
        return request.getValue1();
    }

    public int setPrimary() {
        this.isDefault = true;

        Pair<Integer, JSONObject> request = server.getClient().handleRequest(server.getClient().makeRequest(
                server.getClient().getHost() + "api/client/servers/" + server.getIdentifier() + "/network/allocations/" + id + "/primary",
                "POST", server.getClient().getParameters(), null));
        return request.getValue1();
    }
}
