package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

public class ApplicationLocation {

    private final Application application;
    private int id;
    private String shortName;
    private String longName;
    private String updatedAt;
    private String createdAt;

    public ApplicationLocation(Application application, JSONObject object) {
        this.application = application;
        this.id = ((Long) object.get("id")).intValue();
        this.shortName = (String) object.get("short");
        this.longName = (String) object.get("long");
        this.updatedAt = (String) object.get("updated_at");
        this.createdAt = (String) object.get("created_at");
    }

    public Application getApplication() {
        return application;
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
