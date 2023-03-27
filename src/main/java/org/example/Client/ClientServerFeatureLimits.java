package org.example.Client;

import org.json.simple.JSONObject;

public class ClientServerFeatureLimits {
    private final Long databases;
    private final Long allocations;
    private final Long backups;

    public ClientServerFeatureLimits(JSONObject object) {
        databases = (Long) object.get("databases");
        allocations = (Long) object.get("allocations");
        backups = (Long) object.get("backups");
    }

    public Long getDatabases() {
        return databases;
    }

    public Long getAllocations() {
        return allocations;
    }

    public Long getBackups() {
        return backups;
    }
}
