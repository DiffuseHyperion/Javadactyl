package me.diffusehyperion.Client;

import org.json.simple.JSONObject;

import java.util.List;

public class ClientServer {
    private Client client;

    private Boolean serverOwner;

    private String identifier;

    private int internalID;

    private String uuid;

    private String name;

    // node;

    private Boolean nodeUnderMaintenance;

    private ClientServerSFTPDetails sftpDetails;

    private String description;

    private ClientServerLimits limits;

    private String invocation;

    private String dockerImage;

    private List<String> eggFeatures;

    private ClientServerFeatureLimits featureLimits;

    // tf is this??? private boolean status;

    private Boolean isSuspended;

    private Boolean isInstalling;

    private Boolean isTransferring;

    // relationships


    public ClientServer(Client client, JSONObject object) {
        this.client = client;
        serverOwner = (Boolean) object.get("server_owner");
        identifier = (String) object.get("identifier");
        internalID = ((Long) object.get("internal_id")).intValue();
        uuid = (String) object.get("uuid");
        name = (String) object.get("name");
        //node
        nodeUnderMaintenance = (Boolean) object.get("is_node_under_maintenance");
        sftpDetails = new ClientServerSFTPDetails((JSONObject) object.get("sftp_details"));
        description = (String) object.get("description");
        limits = new ClientServerLimits((JSONObject) object.get("limits"));
        invocation = (String) object.get("invocation");
        dockerImage = (String) object.get("docker_image");
        eggFeatures = (List<String>) object.get("egg_features");
        featureLimits = new ClientServerFeatureLimits((JSONObject) object.get("feature_limits"));
        //status = (boolean) object.get("status");
        isSuspended = (Boolean) object.get("is_suspended");
        isInstalling = (Boolean) object.get("is_installing");
        isTransferring = (Boolean) object.get("is_transferring");
        // relationships
    }

    public Client getClient() {
        return client;
    }
    public Boolean isServerOwner() {
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
    public Boolean isNodeUnderMaintenance() {
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
    public Boolean isInstalling() {
        return isInstalling;
    }

    public void sendCommand(String command) {

    }
}
