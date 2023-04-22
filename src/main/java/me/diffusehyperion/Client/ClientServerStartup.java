package me.diffusehyperion.Client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientServerStartup {

    private ClientServer server;
    private String startupCommand;
    private List<ClientServerStartupVariable> variables = new ArrayList<>();

    public ClientServerStartup(ClientServer server, JSONObject object) {
        this.server = server;
        startupCommand = (String) ((JSONObject) object.get("meta")).get("startup_command");
        for (Object objVariable : (JSONArray) object.get("data")) {
            JSONObject jsonVariable = (JSONObject) objVariable;
            variables.add(new ClientServerStartupVariable(server, (JSONObject) jsonVariable.get("attributes")));
        }
    }

    public String getStartupCommand() {
        return startupCommand;
    }

    public List<ClientServerStartupVariable> getStartupVariables() {
        return variables;
    }
}