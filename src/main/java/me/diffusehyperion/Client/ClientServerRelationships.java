package me.diffusehyperion.Client;

import me.diffusehyperion.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientServerRelationships {

    private final ClientServer server;
    private List<ClientServerAllocations> allocationsList = new ArrayList<>();

    public ClientServerRelationships(ClientServer server, JSONObject object) {
        this.server = server;
        JSONArray allocationsArray = (JSONArray) ((JSONObject) object.get("allocations")).get("data");

        // Iterate over each server object in the array and create a new Server object
        for (Object allocationObject : allocationsArray) {
            JSONObject allocationJSONObject = (JSONObject) allocationObject;
            ClientServerAllocations allocation = new ClientServerAllocations(server, (JSONObject) allocationJSONObject.get("attributes"));
            allocationsList.add(allocation);
        }
    }

    public List<ClientServerAllocations> getAllocationsList() {
        return allocationsList;
    }


    /**
     * Requires auto-assign to be enabled for the server.
     * @return If the auto assign was successful.
     */
    public boolean assignAllocation() {
        Pair<Integer, JSONObject> request = server.getClient().handleRequest(server.getClient().makeRequest(
                server.getClient().getHost() + "api/client/servers/" + server.getIdentifier() + "/network/allocations",
                "POST", server.getClient().getParameters(), null));

        if (request.getValue1() != 200) {
            return false;
        }
        allocationsList.add(new ClientServerAllocations(server, (JSONObject) request.getValue2().get("attributes")));
        return true;
    }

    public int deleteAllocation(int id) {
        Pair<Integer, JSONObject> request = server.getClient().handleRequest(server.getClient().makeRequest(
                server.getClient().getHost() + "api/client/servers/" + server.getIdentifier() + "/network/allocations/" + id,
                "DELETE", server.getClient().getParameters(), null));

        return request.getValue1();
    }
}
