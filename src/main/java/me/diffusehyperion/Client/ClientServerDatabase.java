package me.diffusehyperion.Client;

import me.diffusehyperion.Pair;
import org.json.simple.JSONObject;

public class ClientServerDatabase {

    private ClientServer server;
    private String id;
    private Host host;
    private String name;
    private String username;
    private String connectionsFrom;
    private int maxConnections;

    public ClientServerDatabase(ClientServer server, JSONObject json) {
        this.server = server;
        this.id = (String) json.get("id");
        this.host = new Host((JSONObject) json.get("host"));
        this.name = (String) json.get("name");
        this.username = (String) json.get("username");
        this.connectionsFrom = (String) json.get("connections_from");
        this.maxConnections = (int) json.get("max_connections");
    }

    public String getId() {
        return id;
    }

    public Host getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getConnectionsFrom() {
        return connectionsFrom;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public Pair<Integer, String> rotatePassword() {
        Pair<Integer, JSONObject> request = server.getClient().handleRequest(server.getClient().makeRequest(server.getClient().getHost() + "api/client/servers/" + id + "/databases/" + server.getIdentifier() + "/rotate-password",
                "POST", server.getClient().getParameters(), null));
        return new Pair<>(request.getValue1(), ((String) (((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject)
                        request.getValue2().get("attributes")).get("relationships")).get("password")).get("attributes")).get("password"))));
    }
}

class Host {
    private String address;
    private int port;

    public Host(JSONObject json) {
        this.address = (String) json.get("address");
        this.port = (int) json.get("port");
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
