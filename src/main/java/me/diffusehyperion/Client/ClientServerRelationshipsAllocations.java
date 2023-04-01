package me.diffusehyperion.Client;

import org.json.simple.JSONObject;

public class ClientServerRelationshipsAllocations {
    private int id;
    private String ip;
    private String ipAlias;
    private int port;
    private String notes;
    private boolean isDefault;

    public ClientServerRelationshipsAllocations(JSONObject object) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public void setPort(int port) {
        this.port = port;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
