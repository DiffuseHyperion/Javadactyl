package me.diffusehyperion.Client;

import me.diffusehyperion.HttpMethods;
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

    public List<ClientServer> getServers(){
        Pair<Integer, JSONObject> request = request(getHost() + "api/client",
                HttpMethods.GET, getParameters(), null);
        JSONArray serversArray = (JSONArray) request.getValue2().get("data");

        List<ClientServer> serverList = new ArrayList<>();
        for (Object server : serversArray.toArray()) {
            serverList.add(new ClientServer(this, (JSONObject) ((JSONObject) server).get("attributes")));
        }
        return serverList;
    }

    public ClientServer getServer(String identifier) {
        Pair<Integer, JSONObject> request = request(getHost() + "api/client/servers/" + identifier,
                HttpMethods.GET, getParameters(), null);
        return new ClientServer(this, (JSONObject) request.getValue2().get("attributes"));
    }

    public ClientAccount getAccount() {
        Pair<Integer, JSONObject> request = request(getHost() + "api/client/account",
                HttpMethods.GET, getParameters(), null);
        return new ClientAccount(this, (JSONObject) request.getValue2().get("attributes"));
    }
}
