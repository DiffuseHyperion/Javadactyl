package me.diffusehyperion.Application;

import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApplicationServer {
    private final int id;
    private final String externalId;
    private final String uuid;
    private final String identifier;
    private final String name;
    private final String description;
    private final boolean suspended;
    private final Limits limits;
    private final FeatureLimits featureLimits;
    private final int user;
    private final int node;
    private final int allocation;
    private final int nest;
    private final int egg;
    //what is pack???
    //private final Object pack;
    private final Container container;
    private final ZonedDateTime updatedAt;
    private final ZonedDateTime createdAt;

    public ApplicationServer(JSONObject object) {
        id = ((Long) object.get("id")).intValue();
        externalId = (String) object.get("external_id");
        uuid = (String) object.get("uuid");
        identifier = (String) object.get("identifier");
        name = (String) object.get("name");
        description = (String) object.get("description");
        suspended = (Boolean) object.get("suspended");

        JSONObject limitsObject = (JSONObject) object.get("limits");
        limits = new Limits(limitsObject);

        JSONObject featureLimitsObject = (JSONObject) object.get("feature_limits");
        featureLimits = new FeatureLimits(featureLimitsObject);

        user = ((Long) object.get("user")).intValue();
        node = ((Long) object.get("node")).intValue();
        allocation = ((Long) object.get("allocation")).intValue();
        nest = ((Long) object.get("nest")).intValue();
        egg = (Integer) object.get("egg");

        JSONObject containerObject = (JSONObject) object.get("container");
        container = new Container(containerObject);

        updatedAt = ZonedDateTime.parse((String) object.get("updated_at"));
        createdAt = ZonedDateTime.parse((String) object.get("created_at"));
    }

    public int getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getUuid() {
        return uuid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public Limits getLimits() {
        return limits;
    }

    public FeatureLimits getFeatureLimits() {
        return featureLimits;
    }

    public int getUser() {
        return user;
    }

    public int getNode() {
        return node;
    }

    public int getAllocation() {
        return allocation;
    }

    public int getNest() {
        return nest;
    }

    public int getEgg() {
        return egg;
    }

    public Container getContainer() {
        return container;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static class Limits {
        private final int memory;
        private final int swap;
        private final int disk;
        private final int io;
        private final int cpu;
        @Nullable
        private final Integer threads;

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

        public Integer getThreads() {
            return threads;
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

    public static class Container {
        private final String startupCommand;
        private final String image;
        private final boolean installed;
        private final Environment environment;

        public Container(JSONObject object) {
            startupCommand = (String) object.get("startup_command");
            image = (String) object.get("image");
            installed = (Boolean) object.get("installed");
            environment = new Environment((JSONObject) object.get("environment"));
        }

        public String getStartupCommand() {
            return startupCommand;
        }

        public String getImage() {
            return image;
        }

        public boolean isInstalled() {
            return installed;
        }

        public Environment getEnvironment() {
            return environment;
        }
    }

    public static class Environment {
        private final String serverJarfile;
        private final String vanillaVersion;
        private final String startup;
        private final String pServerLocation;
        private final String pServerUuid;

        public Environment(JSONObject jsonObject) {
            serverJarfile = (String) jsonObject.get("SERVER_JARFILE");
            vanillaVersion = (String) jsonObject.get("VANILLA_VERSION");
            startup = (String) jsonObject.get("STARTUP");
            pServerLocation = (String) jsonObject.get("P_SERVER_LOCATION");
            pServerUuid = (String) jsonObject.get("P_SERVER_UUID");
        }

        public String getServerJarfile() {
            return serverJarfile;
        }

        public String getVanillaVersion() {
            return vanillaVersion;
        }

        public String getStartup() {
            return startup;
        }

        public String getPServerLocation() {
            return pServerLocation;
        }

        public String getPServerUuid() {
            return pServerUuid;
        }
    }


    // it seems that relationships are only sent when listed all servers - not when listing individual servers. Not using this for now
    public static class Relationships {
        private final List<ApplicationServerDatabase> databases = new ArrayList<>();

        public Relationships(JSONObject jsonObject) {
            JSONObject databasesObj = (JSONObject) jsonObject.get("databases");
            JSONArray databasesArray = (JSONArray) databasesObj.get("data");
            for (Object database : databasesArray) {
                JSONObject databaseObj = (JSONObject) database;
                databases.add(new ApplicationServerDatabase(databaseObj));
            }
        }

        public List<ApplicationServerDatabase> getDatabases() {return databases;}
    }
}

