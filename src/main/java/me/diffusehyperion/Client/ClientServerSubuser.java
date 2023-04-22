package me.diffusehyperion.Client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientServerSubuser {
    private String uuid;
    private String username;
    private String email;
    private String image;
    private boolean is2FAEnabled;
    private String createdAt;

    // perhaps create a new classes for permissions?
    private List<String> permissions;

    public ClientServerSubuser(JSONObject jsonObject) {
        this.uuid = (String) jsonObject.get("uuid");
        this.username = (String) jsonObject.get("username");
        this.email = (String) jsonObject.get("email");
        this.image = (String) jsonObject.get("image");
        this.is2FAEnabled = (boolean) jsonObject.get("2fa_enabled");
        this.createdAt = (String) jsonObject.get("created_at");

        JSONArray permissions = (JSONArray) jsonObject.get("permissions");
        this.permissions = new ArrayList<>();
        for (Object permission : permissions) {
            this.permissions.add((String) permission);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public boolean is2FAEnabled() {
        return is2FAEnabled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}