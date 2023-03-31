package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

import java.time.ZonedDateTime;

public class ApplicationUser {

    private final Application application;
    private Long id;
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
        this.id = (Long) object.get("id");
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

    public Long getId() {
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

    private void updateUser(String email, String username, String firstName, String lastName, String language, String password, int id) {
        JSONObject output = new JSONObject();
        output.put("email", email);
        output.put("username", username);
        output.put("first_name", firstName);
        output.put("last_name", lastName);
        output.put("language", language);
        output.put("password", password);
    
        Pair<Integer, JSONObject> request = handleRequest(makeOutputRequest(getHost() + "api/application/users/" + id,
        "PATCH", getParameters(), output.toJSONString()));
    }

    public void setEmail(String email) {
        this.email = email;
        
        updateUser(email, this.username, this.firstName, this.lastName, this.language, this.password, this.id);
    }

    public void setUsername(String username) {
        this.username = username;
        
        updateUser(this.email, username, this.firstName, this.lastName, this.language, this.password, this.id);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        
        updateUser(this.email, this.username, firstName, this.lastName, this.language, this.password, this.id);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        
        updateUser(this.email, this.username, this.firstName, lastName, this.language, this.password, this.id);
    }

    public void setLanguage(String language) {
        this.language = language;
        
        updateUser(this.email, this.username, this.firstName, this.lastName, language, this.password, this.id);
    }

    public void setPassword(String password) {
        this.password = password;
        
        updateUser(this.email, this.username, this.firstName, lastName, this.language, password, this.id);
    }

    public void deleteUser() {
        Pair<Integer, JSONObject> request = handleRequest(makeOutputRequest(getHost() + "api/application/users" + this.id,
        "DELETE", getParameters(), output.toJSONString()));
    }
}
