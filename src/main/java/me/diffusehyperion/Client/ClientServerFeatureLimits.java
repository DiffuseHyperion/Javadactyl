package me.diffusehyperion.Client;

import org.json.simple.JSONObject;

public class ClientServerFeatureLimits {
    private final int databases;
    private final int allocations;
    private final int backups;

    public ClientServerFeatureLimits(JSONObject object) {
        databases = ((Long) object.get("databases")).intValue();
        allocations = ((Long) object.get("allocations")).intValue();
        backups = ((Long) object.get("backups")).intValue();
    }

    public int getDatabases() {
        return databases;
    }

    public int getAllocations() {
        return allocations;
    }

    public int getBackups() {
        return backups;
    }
}
