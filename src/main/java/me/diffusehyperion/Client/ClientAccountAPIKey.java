package me.diffusehyperion.Client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class ClientAccountAPIKey {
    private String identifier;
    private String description;
    private List<String> allowedIps;
    private String lastUsedAt;
    private String createdAt;

    public ClientAccountAPIKey(JSONObject jsonObject) {
        this.identifier = (String) jsonObject.get("identifier");
        this.description = (String) jsonObject.get("description");
        JSONArray allowedIpsObject = (JSONArray) jsonObject.get("allowed_ips");
        this.allowedIps = allowedIpsObject.stream().toList();
        this.lastUsedAt = (String) jsonObject.get("last_used_at");
        this.createdAt = (String) jsonObject.get("created_at");
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAllowedIps() {
        return allowedIps;
    }

    public String getLastUsedAt() {
        return lastUsedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
