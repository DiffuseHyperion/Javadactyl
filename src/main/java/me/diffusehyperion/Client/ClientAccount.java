package me.diffusehyperion.Client;

import me.diffusehyperion.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public int setEmail(String email, String existingPassword) {
        JSONObject output = new JSONObject();
        output.put("email", email);
        output.put("password", existingPassword);

        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/account/email",
                "PUT", client.getParameters(), output.toString()));
        return request.getValue1();
    }

    public int setPassword(String existingPassword, String newPassword) {
        JSONObject output = new JSONObject();
        output.put("current_password", existingPassword);
        output.put("password", newPassword);
        output.put("password_confirmation", newPassword);

        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/account/password",
                "PUT", client.getParameters(), output.toString()));
        return request.getValue1();
    }

    public int setPassword(String existingPassword, String newPassword, String confirmPassword) {
        JSONObject output = new JSONObject();
        output.put("current_password", existingPassword);
        output.put("password", newPassword);
        output.put("password_confirmation", confirmPassword);

        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/account/password",
                "PUT", client.getParameters(), output.toString()));
        return request.getValue1();
    }

    public List<ClientAccountAPIKey> getAPIKeys() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/account/api-keys",
                "GET", client.getParameters(), null));
        JSONArray keyArray = (JSONArray) request.getValue2().get("data");
        List<ClientAccountAPIKey> keyList = new ArrayList<>();
        for (Object object : keyArray) {
            JSONObject jsonObject = (JSONObject) object;
            keyList.add(new ClientAccountAPIKey((JSONObject) jsonObject.get("attributes")));
        }
        return keyList;
    }
}
