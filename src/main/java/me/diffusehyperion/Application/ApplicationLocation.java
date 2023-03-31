package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

public class ApplicationLocation {
    private int id;
    private String shortName;
    private String longName;
    private String updatedAt;
    private String createdAt;

    public ApplicationLocation(JSONObject object) {
        this.id = ((Long) object.get("id")).intValue();
        this.shortName = (String) object.get("short");
        this.longName = (String) object.get("long");
        this.updatedAt = (String) object.get("updated_at");
        this.createdAt = (String) object.get("created_at");
    }
}
