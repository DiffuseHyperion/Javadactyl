package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

public class ApplicationNodeAllocations {
    private int id;
    private String ip;
    private String alias;
    private int port;
    private String notes;
    private boolean assigned;

    public ApplicationNodeAllocations(JSONObject object) {
        this.id = ((Long) object.get("id")).intValue();
        this.ip = (String) object.get("ip");
        this.alias = (String) object.get("alias");
        this.port = ((Long) object.get("port")).intValue();
        this.notes = (String) object.get("notes");
        this.assigned = (Boolean) object.get("assigned");
    }
}
