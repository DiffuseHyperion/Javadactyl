package me.diffusehyperion.Application;

import me.diffusehyperion.PterodactylAPI;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;

public class ApplicationUsers {

    private Application application;
    private int id;
    private String externalId;
    private String uuid;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String language;
    private boolean rootAdmin;
    private boolean twoFactorAuthentication;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ApplicationUsers(Application application, JSONObject object) {
        this.application = application;
        this.id = (int) object.get("id");
        this.externalId = (String) object.get("external_id");
        this.uuid = (String) object.get("uuid");
        this.username = (String) object.get("username");
        this.email = (String) object.get("email");
        this.firstName = (String) object.get("first_name");
        this.lastName =(String)  object.get("last_name");
        this.language = (String) object.get("language");
        this.rootAdmin = (boolean) object.get("root_admin");
        this.twoFactorAuthentication = (boolean) object.get("2fa");
        this.createdAt = LocalDateTime.parse((String) object.get("created_at"));
        this.updatedAt = LocalDateTime.parse((String) object.get("updated_at"));
    }

    public int getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isRootAdmin() {
        return rootAdmin;
    }

    public boolean isTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
