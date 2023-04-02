package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

public class ApplicationNodeAllocations {

    private final ApplicationNode node;
    private final int id;
    private final String ip;
    private final String alias;
    private final int port;
    private final String notes;
    private final boolean assigned;

    public ApplicationNodeAllocations(ApplicationNode node, JSONObject object) {
        this.node = node;
        this.id = ((Long) object.get("id")).intValue();
        this.ip = (String) object.get("ip");
        this.alias = (String) object.get("alias");
        this.port = ((Long) object.get("port")).intValue();
        this.notes = (String) object.get("notes");
        this.assigned = (Boolean) object.get("assigned");
    }

    public ApplicationNode getNode() {
        return node;
    }

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getAlias() {
        return alias;
    }

    public int getPort() {
        return port;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isAssigned() {
        return assigned;
    }
}
