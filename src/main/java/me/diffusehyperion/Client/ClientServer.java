package me.diffusehyperion.Client;

import me.diffusehyperion.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientServer {
    private Client client;

    private boolean serverOwner;

    private String identifier;

    private int internalID;

    private String uuid;

    private String name;

    private String nodeName;

    private boolean nodeUnderMaintenance;

    private ClientServerSFTPDetails sftpDetails;

    private String description;

    private ClientServerLimits limits;

    private String invocation;

    private String dockerImage;

    private List<String> eggFeatures;

    private ClientServerFeatureLimits featureLimits;

    // tf is this??? private boolean status;

    private boolean isSuspended;

    private boolean isInstalling;

    private boolean isTransferring;

    private ClientServerRelationships relationships;


    public ClientServer(Client client, JSONObject object) {
        this.client = client;
        serverOwner = (boolean) object.get("server_owner");
        identifier = (String) object.get("identifier");
        internalID = ((Long) object.get("internal_id")).intValue();
        uuid = (String) object.get("uuid");
        name = (String) object.get("name");
        nodeName = (String) object.get("node");
        nodeUnderMaintenance = (boolean) object.get("is_node_under_maintenance");
        sftpDetails = new ClientServerSFTPDetails((JSONObject) object.get("sftp_details"));
        description = (String) object.get("description");
        limits = new ClientServerLimits((JSONObject) object.get("limits"));
        invocation = (String) object.get("invocation");
        dockerImage = (String) object.get("docker_image");
        eggFeatures = (List<String>) object.get("egg_features");
        featureLimits = new ClientServerFeatureLimits((JSONObject) object.get("feature_limits"));
        //status = (boolean) object.get("status");
        isSuspended = (boolean) object.get("is_suspended");
        isInstalling = (boolean) object.get("is_installing");
        isTransferring = (boolean) object.get("is_transferring");
        relationships = new ClientServerRelationships(this, (JSONObject) object.get("relationships"));
    }

    public Client getClient() {
        return client;
    }
    public boolean isServerOwner() {
        return serverOwner;
    }
    public String getIdentifier() {
        return identifier;
    }
    public int getInternalID() {
        return internalID;
    }
    public String getUUID() {
        return uuid;
    }
    public String getName() {
        return name;
    }
    public String getNodeName() {
        return nodeName;
    }
    public boolean isNodeUnderMaintenance() {
        return nodeUnderMaintenance;
    }
    public ClientServerSFTPDetails getSftpDetails() {
        return sftpDetails;
    }
    public String getDescription() {
        return description;
    }
    public ClientServerLimits getLimits() {
        return limits;
    }
    public String getInvocation() {
        return invocation;
    }
    public String getDockerImage() {
        return dockerImage;
    }
    public List<String> getEggFeatures() {
        return eggFeatures;
    }
    public ClientServerFeatureLimits getFeatureLimits() {
        return featureLimits;
    }
    //public boolean isStatus() {
    //    return status;
    //}
    public boolean isSuspended() {
        return isSuspended;
    }
    public boolean isInstalling() {
        return isInstalling;
    }
    public boolean isTransferring() {
        return isTransferring;
    }
    public ClientServerRelationships getRelationships() {
        return relationships;
    }

    public int sendCommand(String command) {
        JSONObject output = new JSONObject();
        output.put("command", command);
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/command",
                "POST", client.getParameters(), output.toString()));
        return request.getValue1();
    }

    public int changePowerState(PowerState power) {
        JSONObject output = new JSONObject();
        output.put("signal", power.toString());
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/power",
                "POST", client.getParameters(), output.toString()));
        return request.getValue1();
    }

    public enum PowerState {
        START("start"),
        STOP("stop"),
        RESTART("restart"),
        KILL("kill");

        private final String command;

        PowerState(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    public List<ClientServerBackup> getBackups() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/backups",
                "GET", client.getParameters(), null));
        JSONArray backupArray = (JSONArray) request.getValue2().get("data");
        List<ClientServerBackup> backupList = new ArrayList<>();
        for (Object obj : backupArray) {
            JSONObject jsonObject = (JSONObject) obj;
            backupList.add(new ClientServerBackup((JSONObject) jsonObject.get("attributes")));
        }
        return backupList;
    }

    // we return status code too because backup has a reasonable chance of failure
    public Pair<Integer, ClientServerBackup> createBackup() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/backups",
                "POST", client.getParameters(), null));
        return new Pair<>(request.getValue1(), new ClientServerBackup((JSONObject) request.getValue2().get("attributes")));
    }

    public ClientServerBackup getBackup(UUID uuid) {
Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/backups/" + uuid.toString(),
                "GET", client.getParameters(), null));
        return new ClientServerBackup((JSONObject) request.getValue2().get("attributes"));
    }

    public URL getBackupDownloadURL(UUID uuid) {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/backups/" + uuid.toString() + "/download",
                "GET", client.getParameters(), null));
        return (URL) request.getValue2().get("attributes");
    }

    public int deleteBackup(UUID uuid) {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/backups/" + uuid.toString(),
                "DELETE", client.getParameters(), null));
        return request.getValue1();
    }

    public List<ClientServerSubuser> getSubusers() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/users",
                "GET", client.getParameters(), null));
        JSONArray subuserArray = (JSONArray) request.getValue2().get("data");
        System.out.println(subuserArray);
        List<ClientServerSubuser> subuserList = new ArrayList<>();
        for (Object obj : subuserArray) {
            JSONObject jsonObject = (JSONObject) obj;
            subuserList.add(new ClientServerSubuser((JSONObject) jsonObject.get("attributes")));
        }
        return subuserList;
    }

    public Pair<Integer, ClientServerSubuser> createSubuser(String email, String... permissions) {
        JSONObject output = new JSONObject();
        output.put("email", email);
        output.put("permissions", permissions);
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/users",
                "POST", client.getParameters(), output.toString()));
        return new Pair<>(request.getValue1(), new ClientServerSubuser((JSONObject) request.getValue2().get("attributes")));
    }

    public Pair<Integer, ClientServerSubuser> getSubuser(UUID uuid) {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/users/" + uuid.toString(),
                "GET", client.getParameters(), null));
        return new Pair<>(request.getValue1(), new ClientServerSubuser((JSONObject) request.getValue2().get("attributes")));
    }

    public Pair<Integer, ClientServerSubuser> updateSubuser(UUID uuid, String... permissions) {
        JSONObject output = new JSONObject();
        output.put("permissions", permissions);
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/users/" + uuid.toString(),
                "POST", client.getParameters(), output.toString()));
        return new Pair<>(request.getValue1(), new ClientServerSubuser((JSONObject) request.getValue2().get("attributes")));
    }

    public int deleteSubuser(UUID uuid) {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/users/" + uuid.toString(),
                "DELETE", client.getParameters(), null));
        return request.getValue1();
    }

    public ClientServerResources getResources() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/resources",
                "GET", client.getParameters(), null));
        return new ClientServerResources((JSONObject) request.getValue2().get("attributes"));
    }

    public int renameServer(String newName) {
        JSONObject output = new JSONObject();
        output.put("name", newName);
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/settings/rename",
                "POST", client.getParameters(), output.toString()));
        return request.getValue1();
    }

    public int reinstallServer() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/settings/reinstall",
                "POST", client.getParameters(), null));
        return request.getValue1();
    }

    public ClientServerStartup getStartup() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/startup",
                "GET", client.getParameters(), null));
        return new ClientServerStartup(this, request.getValue2());
    }
}
