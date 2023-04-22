package me.diffusehyperion.Application;

import me.diffusehyperion.Pair;
import me.diffusehyperion.PterodactylAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Application extends PterodactylAPI {


    public Application(String apiKey, String host) {
        super(apiKey, host);
    }

    public List<ApplicationUser> getUsers() {
        Pair<Integer, JSONObject> request = handleRequest(makeRequest(getHost() + "api/application/users",
                "GET", getParameters(), null));

        JSONArray userArray = (JSONArray) request.getValue2().get("data");

        List<ApplicationUser> userList = new ArrayList<>();
        for (Object server : userArray.toArray()) {
            userList.add(new ApplicationUser(this, (JSONObject) ((JSONObject) server).get("attributes")));
        }
        return userList;
    }

    public ApplicationUser getUser(int ID) {
        Pair<Integer, JSONObject> request = handleRequest(makeRequest(getHost() + "api/application/users/" + ID,
                "GET", getParameters(), null));
        return new ApplicationUser(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public ApplicationUser getExternalUser(int ExternalID) {
        Pair<Integer, JSONObject> request = handleRequest(makeRequest(getHost() + "api/application/users/external/" + ExternalID,
                "GET", getParameters(), null));
        return new ApplicationUser(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public ApplicationUser createUser(String email, String username, String firstName, String lastName) {
        JSONObject output = new JSONObject();
        output.put("email", email);
        output.put("username", username);
        output.put("first_name", firstName);
        output.put("last_name", lastName);
        Pair<Integer, JSONObject> request = handleRequest(makeRequest(getHost() + "api/application/users",
                "POST", getParameters(), output.toJSONString()));

        return new ApplicationUser(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public List<ApplicationLocation> getLocations() {
        Pair<Integer, JSONObject> request = handleRequest(makeRequest(getHost() + "api/application/locations",
                "GET", getParameters(), null));
        JSONArray locationArray = (JSONArray) request.getValue2().get("data");

        List<ApplicationLocation> locationList = new ArrayList<>();
        for (Object server : locationArray.toArray()) {
            locationList.add(new ApplicationLocation(this, (JSONObject) ((JSONObject) server).get("attributes")));
        }

        return locationList;
    }
}
