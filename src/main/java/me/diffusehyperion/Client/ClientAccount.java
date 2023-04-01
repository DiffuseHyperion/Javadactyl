package me.diffusehyperion.Client;

import org.json.simple.JSONObject;

public class ClientAccount {
    private Client client;
    private int id;
    private boolean admin;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String language;

    public ClientAccount(Client client, JSONObject json) {
        this.client = client;
        this.id = ((Long) json.get("id")).intValue();
        this.admin = (boolean) json.get("admin");
        this.username = (String) json.get("username");
        this.email = (String) json.get("email");
        this.firstName = (String) json.get("first_name");
        this.lastName = (String) json.get("last_name");
        this.language = (String) json.get("language");
    }

    public Client getClient() {
        return client;
    }

    public int getId() {
        return id;
    }

    public Boolean getAdmin() {
        return admin;
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
}
