package me.diffusehyperion.Application;

import me.diffusehyperion.HttpMethods;
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
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/users",
                HttpMethods.GET, getParameters(), null);

        JSONArray userArray = (JSONArray) request.getValue2().get("data");

        List<ApplicationUser> userList = new ArrayList<>();
        for (Object server : userArray.toArray()) {
            userList.add(new ApplicationUser(this, (JSONObject) ((JSONObject) server).get("attributes")));
        }
        return userList;
    }

    public ApplicationUser getUser(int ID) {
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/users/" + ID,
                HttpMethods.GET, getParameters(), null);
        return new ApplicationUser(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public ApplicationUser getExternalUser(int ExternalID) {
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/users/external/" + ExternalID,
                HttpMethods.GET, getParameters(), null);
        return new ApplicationUser(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public ApplicationUser createUser(String email, String username, String firstName, String lastName) {
        JSONObject output = new JSONObject();
        output.put("email", email);
        output.put("username", username);
        output.put("first_name", firstName);
        output.put("last_name", lastName);
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/users",
                HttpMethods.POST, getParameters(), output.toJSONString());

        return new ApplicationUser(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public List<ApplicationLocation> getLocations() {
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/locations",
                HttpMethods.GET, getParameters(), null);
        JSONArray locationArray = (JSONArray) request.getValue2().get("data");

        List<ApplicationLocation> locationList = new ArrayList<>();
        for (Object server : locationArray.toArray()) {
            locationList.add(new ApplicationLocation(this, (JSONObject) ((JSONObject) server).get("attributes")));
        }

        return locationList;
    }

    public ApplicationLocation getLocation(int ID) {
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/locations/" + ID,
                HttpMethods.GET, getParameters(), null);
        return new ApplicationLocation(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public Pair<Integer, ApplicationLocation> createLocation(String identifier, String location) {
        JSONObject output = new JSONObject();
        output.put("short", identifier);
        output.put("long", location);
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/locations",
                HttpMethods.POST, getParameters(), output.toJSONString());

        return new Pair<>(request.getValue1(), new ApplicationLocation(this, (JSONObject) request.getValue2().get("attributes")));
    }

    public int deleteLocation(int ID) {
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/locations/" + ID,
                HttpMethods.DELETE, getParameters(), null);
        return request.getValue1();
    }

    public int deleteLocation(ApplicationLocation location) {
        return deleteLocation(location.getId());
    }

    public List<ApplicationNest> getNests() {
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/nests",
                HttpMethods.GET, getParameters(), null);
        JSONArray nestArray = (JSONArray) request.getValue2().get("data");
        List<ApplicationNest> nestList = new ArrayList<>();

        for (Object nest : nestArray) {
            JSONObject nestObject = (JSONObject) nest;
            nestList.add(new ApplicationNest(this, (JSONObject) nestObject.get("attributes")));
        }

        return nestList;
    }

    public ApplicationNest getNest(int ID) {
        Pair<Integer, JSONObject> request = request(getHost() + "api/application/nests/" + ID,
                HttpMethods.GET, getParameters(), null);
        return new ApplicationNest(this, (JSONObject) request.getValue2().get("attributes"));
    }
}
