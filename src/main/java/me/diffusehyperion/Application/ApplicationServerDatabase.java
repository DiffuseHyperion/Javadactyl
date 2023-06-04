package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

public class ApplicationServerDatabase {
    private final int id;
    private final int server;
    private final int host;
    private final String database;
    private final String username;
    private final String remote;
    private final int maxConnections;
    private final String createdAt;
    private final String updatedAt;

    public ApplicationServerDatabase(JSONObject jsonObject) {
        id = ((Long) jsonObject.get("id")).intValue();
        server = ((Long) jsonObject.get("server")).intValue();
        host = ((Long) jsonObject.get("host")).intValue();
        database = (String) jsonObject.get("database");
        username = (String) jsonObject.get("username");
        remote = (String) jsonObject.get("remote");
        maxConnections = ((Long) jsonObject.get("max_connections")).intValue();
        createdAt = (String) jsonObject.get("created_at");
        updatedAt = (String) jsonObject.get("updated_at");
    }

    public int getId() {
        return id;
    }

    public int getServer() {
        return server;
    }

    public int getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getRemote() {
        return remote;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
