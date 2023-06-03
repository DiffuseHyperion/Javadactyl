package me.diffusehyperion.Application;

import me.diffusehyperion.HttpMethods;
import me.diffusehyperion.Pair;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ApplicationUser {

    private final Application application;
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
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public ApplicationUser(Application application, JSONObject object) {
        this.application = application;
        this.id = ((Long) object.get("id")).intValue();
        this.externalId = (String) object.get("external_id");
        this.uuid = (String) object.get("uuid");
        this.username = (String) object.get("username");
        this.email = (String) object.get("email");
        this.firstName = (String) object.get("first_name");
        this.lastName =(String)  object.get("last_name");
        this.language = (String) object.get("language");
        this.rootAdmin = (boolean) object.get("root_admin");
        this.twoFactorAuthentication = (boolean) object.get("2fa");
        this.createdAt = ZonedDateTime.parse((String) object.get("created_at"));
        this.updatedAt = ZonedDateTime.parse((String) object.get("updated_at"));
    }

    public Application getApplication() {
        return application;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    private int updateUser(String email, String username, String firstName, String lastName, @Nullable String language, @Nullable String password, int id) {
        JSONObject output = new JSONObject();
        output.put("email", email);
        output.put("username", username);
        output.put("first_name", firstName);
        output.put("last_name", lastName);
        if (Objects.nonNull(language)) {
            output.put("language", language);
        }
        if (Objects.nonNull(password)) {
            output.put("password", password);
        }

        Pair<Integer, JSONObject> request = application.request(application.getHost() + "api/application/users/" + id,
        HttpMethods.PATCH, application.getParameters(), output.toString());
        return request.getValue1();
    }

    public int setEmail(String email) {
        this.email = email;
        
        return updateUser(email, this.username, this.firstName, this.lastName, this.language, null, this.id);
    }

    public int setUsername(String username) {
        this.username = username;
        
        return updateUser(this.email, username, this.firstName, this.lastName, this.language, null, this.id);
    }

    public int setFirstName(String firstName) {
        this.firstName = firstName;
        
        return updateUser(this.email, this.username, firstName, this.lastName, this.language, null, this.id);
    }

    public int setLastName(String lastName) {
        this.lastName = lastName;
        
        return updateUser(this.email, this.username, this.firstName, lastName, this.language, null, this.id);
    }

    public int setLanguage(String language) {
        this.language = language;
        
        return updateUser(this.email, this.username, this.firstName, this.lastName, language, null, this.id);
    }

    public int setPassword(String password) {
        return updateUser(this.email, this.username, this.firstName, lastName, this.language, password, this.id);
    }

    public int deleteUser() {
        Pair<Integer, JSONObject> request = application.request(application.getHost() + "api/application/users" + this.id,
                HttpMethods.DELETE, application.getParameters(), null);
        return request.getValue1();
    }
}
