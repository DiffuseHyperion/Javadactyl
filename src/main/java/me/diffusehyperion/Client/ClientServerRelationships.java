package me.diffusehyperion.Client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientServerRelationships {

    private List<ClientServerAllocations> allocationsList = new ArrayList<>();

    public ClientServerRelationships(JSONObject object) {
        JSONArray allocationsArray = (JSONArray) ((JSONObject) object.get("allocations")).get("data");

        // Iterate over each server object in the array and create a new Server object
        for (Object allocationObject : allocationsArray) {
            JSONObject allocationJSONObject = (JSONObject) allocationObject;
            ClientServerAllocations server = new ClientServerAllocations((JSONObject) allocationJSONObject.get("attributes"));
            allocationsList.add(server);
        }
    }

    public List<ClientServerAllocations> getAllocationsList() {
        return allocationsList;
    }
}
