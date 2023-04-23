package me.diffusehyperion.Application;

import org.json.simple.JSONObject;

import java.time.ZonedDateTime;

public class ApplicationNestEgg {
    private int id;
    private String uuid;
    private String name;
    private int nest;
    private String author;
    private String description;
    private String dockerImage;
    private ApplicationNestEggConfig config;
    private String startup;
    private ApplicationNestEggScript script;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public ApplicationNestEgg(JSONObject json) {
        id = ((Long) json.get("id")).intValue();
        uuid = (String) json.get("uuid");
        name = (String) json.get("name");
        nest = ((Long) json.get("nest")).intValue();
        author = (String) json.get("author");
        description = (String) json.get("description");
        dockerImage = (String) json.get("docker_image");
        config = new ApplicationNestEggConfig((JSONObject) json.get("config"));
        startup = (String) json.get("startup");
        script = new ApplicationNestEggScript((JSONObject) json.get("script"));
        createdAt = ZonedDateTime.parse((String) json.get("created_at"));
        updatedAt = ZonedDateTime.parse((String) json.get("updated_at"));
    }

    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getNest() {
        return nest;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getDockerImage() {
        return dockerImage;
    }

    public ApplicationNestEggConfig getConfig() {
        return config;
    }

    public String getStartup() {
        return startup;
    }

    public ApplicationNestEggScript getScript() {
        return script;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
