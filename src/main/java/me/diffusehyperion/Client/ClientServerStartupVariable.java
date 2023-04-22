package me.diffusehyperion.Client;

import me.diffusehyperion.Pair;
import org.json.simple.JSONObject;

public class ClientServerStartupVariable {

    private ClientServer server;
    private String name;
    private String description;
    private String envVariable;
    private String defaultValue;
    private String serverValue;
    private boolean isEditable;
    private String rules;

    public ClientServerStartupVariable(ClientServer server, JSONObject jsonObject) {
        this.server = server;
        this.name = (String) jsonObject.get("name");
        this.description = (String) jsonObject.get("description");
        this.envVariable = (String) jsonObject.get("env_variable");
        this.defaultValue = (String) jsonObject.get("default_value");
        this.serverValue = (String) jsonObject.get("server_value");
        this.isEditable = (boolean) jsonObject.get("is_editable");
        this.rules = (String) jsonObject.get("rules");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEnvVariable() {
        return envVariable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getServerValue() {
        return serverValue;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public String getRules() {
        return rules;
    }

    /**
     * Will not update serverValue if the request fails.
     */
    public int updateValue(String newValue) {
        JSONObject output = new JSONObject();
        output.put("key", envVariable);
        output.put("value", newValue);

        Pair<Integer, JSONObject> request = server.getClient().handleRequest(server.getClient().makeRequest(
                server.getClient().getHost() + "api/client/servers/" + server.getIdentifier() + "/startup/variable",
                "PUT", server.getClient().getParameters(), output.toString()));

        if (request.getValue1() == 200) {
            this.serverValue = newValue;
        }

        return request.getValue1();
    }
}