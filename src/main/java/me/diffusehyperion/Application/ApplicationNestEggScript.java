package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

public class ApplicationNestEggScript {
    private boolean privileged;
    private String install;
    private String entry;
    private String container;
    private String extendsValue;

    public ApplicationNestEggScript(JSONObject json) {
        this.privileged = (boolean) json.get("privileged");
        this.install = (String) json.get("install");
        this.entry = (String) json.get("entry");
        this.container = (String) json.get("container");
        this.extendsValue = (String) json.get("extends");
    }

    public boolean isPrivileged() {
        return privileged;
    }

    public String getInstall() {
        return install;
    }

    public String getEntry() {
        return entry;
    }

    public String getContainer() {
        return container;
    }

    public String getExtendsValue() {
        return extendsValue;
    }
}
