package me.diffusehyperion.Client;

import me.diffusehyperion.PterodactylAPI;
import me.diffusehyperion.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class Client extends PterodactylAPI {
    public Client(String apiKey, String host) {
        super(apiKey, host);
    }

    public List<ClientServer> getServerList(){
        Pair<Integer, JSONObject> request = handleRequest(makeRequest(getHost() + "api/client",
                "GET", getParameters(), null));

        JSONArray serversArray = (JSONArray) request.getValue2().get("data");

        List<ClientServer> serverList = new ArrayList<>();
        for (Object server : serversArray.toArray()) {
            serverList.add(new ClientServer(this, (JSONObject) ((JSONObject) server).get("attributes")));
        }
        return serverList;
    }

    public ClientServer getServer(String identifier) {
        Pair<Integer, JSONObject> request = handleRequest(makeRequest(getHost() + "api/client/servers/" + identifier,
                "GET", getParameters(), null));
        return new ClientServer(this, (JSONObject) request.getValue2().get("attributes"));
    }
}
