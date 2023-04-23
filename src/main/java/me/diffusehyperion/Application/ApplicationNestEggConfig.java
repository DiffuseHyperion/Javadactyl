package me.diffusehyperion.Application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationNestEggConfig {
    private HashMap<String, Files> files = new HashMap<>();
    private Startup startup;
    private String stop;
    private Logs logs;
    private String extendsValue;

    public ApplicationNestEggConfig(JSONObject json) {
        for (Object objKey : ((JSONObject) json.get("files")).keySet()) {
            String key = objKey.toString();
            ApplicationNestEggConfig.Files value = new Files((JSONObject) ((JSONObject) json.get("files")).get(key));
            files.put(key, value);
        }
        this.startup = new Startup((JSONObject) json.get("startup"));
        this.stop = (String) json.get("stop");
        System.out.println(json);
        if (json.get("logs") instanceof JSONArray) {
            // looks like if there are no custom settings logs, it's an empty array for some reason
            this.logs = null;
        } else {
            this.logs = new Logs((JSONObject) json.get("logs"));
        }
        this.extendsValue = (String) json.get("extends");
        // there also seems to be a key called file_denylist with an empty array, it is not stated anywhere in the docs, tf
    }

    public HashMap<String, Files> getFiles() {
        return files;
    }

    public Startup getStartup() {
        return startup;
    }

    public String getStop() {
        return stop;
    }

    public Logs getLogs() {
        return logs;
    }

    public String getExtends() {
        return extendsValue;
    }

    public static class Files {
        private String parser;
        private HashMap<String, String> find = new HashMap<>();

        public Files(JSONObject object) {
            this.parser = (String) object.get("parser");
            for (Object entryObj : ((JSONObject) object.get("find")).entrySet()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) entryObj;
                find.put(entry.getKey(), entry.getValue());
                //TODO: implement support for placeholders (e.g. {{server.build.default.port}})
            }
        }

        public String getParser() {
            return parser;
        }

        public HashMap<String, String> getFind() {
            return find;
        }
    }

    public static class Startup {
        private String done;
        private List<String> userInteraction;

        public Startup(JSONObject object) {
            this.done = (String) object.get("done");
            this.userInteraction = (JSONArray) object.get("userInteraction");
        }

        public String getDone() {
            return done;
        }

        public List<String> getUserInteraction() {
            return userInteraction;
        }
    }

    public static class Logs {
        private boolean custom;
        private String location;

        public Logs(JSONObject object) {
            this.custom = (boolean) object.get("custom");
            this.location = (String) object.get("location");
        }

        public boolean isCustom() {
            return custom;
        }

        public String getLocation() {
            return location;
        }
    }
}
