package me.diffusehyperion.Client;

import me.diffusehyperion.Pair;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private SFTPDetails sftpDetails;
    private String description;
    private Limits limits;
    private String invocation;
    private String dockerImage;
    private List<String> eggFeatures;
    private FeatureLimits featureLimits;
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
        sftpDetails = new SFTPDetails((JSONObject) object.get("sftp_details"));
        description = (String) object.get("description");
        limits = new Limits((JSONObject) object.get("limits"));
        invocation = (String) object.get("invocation");
        dockerImage = (String) object.get("docker_image");
        eggFeatures = (List<String>) object.get("egg_features");
        featureLimits = new FeatureLimits((JSONObject) object.get("feature_limits"));
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
    public SFTPDetails getSftpDetails() {
        return sftpDetails;
    }
    public String getDescription() {
        return description;
    }
    public Limits getLimits() {
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
    public FeatureLimits getFeatureLimits() {
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

    public static class SFTPDetails {
        private final String ip;
        private final int port;
        public SFTPDetails(JSONObject object) {
            ip = (String) object.get("ip");
            port = ((Long) object.get("port")).intValue();
        }
        public String getIp() {
            return ip;
        }
        public int getPort() {
            return port;
        }
    }
    public static class FeatureLimits {
        private final int databases;
        private final int allocations;
        private final int backups;
        public FeatureLimits(JSONObject object) {
            databases = ((Long) object.get("databases")).intValue();
            allocations = ((Long) object.get("allocations")).intValue();
            backups = ((Long) object.get("backups")).intValue();
        }
        public int getDatabases() {
            return databases;
        }
        public int getAllocations() {
            return allocations;
        }
        public int getBackups() {
            return backups;
        }
    }
    public static class Limits {
        private final int memory;
        private final int swap;
        private final int disk;
        private final int io;
        private final int cpu;
        @Nullable
        private final Integer threads;
        private final Boolean oomDisabled;

        public Limits(JSONObject object) {
            memory = ((Long) object.get("memory")).intValue();
            swap = ((Long) object.get("swap")).intValue();
            disk = ((Long) object.get("disk")).intValue();
            io = ((Long) object.get("io")).intValue();
            cpu = ((Long) object.get("cpu")).intValue();
            if (Objects.isNull(object.get("threads"))) {
                threads = null;
            } else {
                threads = ((Long) object.get("threads")).intValue();
            }
            oomDisabled = (Boolean) object.get("oom_disabled");
        }

        public int getMemory() {
            return memory;
        }

        public int getSwap() {
            return swap;
        }

        public int getDisk() {
            return disk;
        }

        public int getIo() {
            return io;
        }

        public int getCpu() {
            return cpu;
        }

        public @Nullable Integer getThreads() {
            return threads;
        }

        public Boolean isOomDisabled() {
            return oomDisabled;
        }
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

    public List<ClientServerDatabase> getDatabases() {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/databases",
                "GET", client.getParameters(), null));
        JSONArray databaseArray = (JSONArray) request.getValue2().get("data");
        List<ClientServerDatabase> databaseList = new ArrayList<>();
        for (Object obj : databaseArray) {
            JSONObject jsonObject = (JSONObject) obj;
            databaseList.add(new ClientServerDatabase(this, (JSONObject) jsonObject.get("attributes")));
        }
        return databaseList;
    }

    public Pair<Integer, Pair<ClientServerDatabase, String>> createDatabase(String database, String remote) {
        JSONObject output = new JSONObject();
        output.put("database", database);
        output.put("remote", remote);
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/databases",
                "POST", client.getParameters(), output.toString()));
        return new Pair<>(request.getValue1(), new Pair<>(new ClientServerDatabase(this, (JSONObject) request.getValue2().get("attributes")),
                ((String) (((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject)
                        request.getValue2().get("attributes")).get("relationships")).get("password")).get("attributes")).get("password")))));
    }

    public int deleteDatabase(String id) {
        Pair<Integer, JSONObject> request = client.handleRequest(client.makeRequest(client.getHost() + "api/client/servers/" + identifier + "/databases/" + id,
                "DELETE", client.getParameters(), null));
        return request.getValue1();
    }
}
