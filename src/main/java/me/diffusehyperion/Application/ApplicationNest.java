package me.diffusehyperion.Application;

import me.diffusehyperion.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApplicationNest {

    private Application application;
    private int id;
    private String uuid;
    private String author;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;

    public ApplicationNest(Application application, JSONObject jsonObject) {
        this.application = application;
        this.id = ((Long) jsonObject.get("id")).intValue();
        this.uuid = (String) jsonObject.get("uuid");
        this.author = (String) jsonObject.get("author");
        this.name = (String) jsonObject.get("name");
        this.description = (String) jsonObject.get("description");
        this.createdAt = (String) jsonObject.get("created_at");
        this.updatedAt = (String) jsonObject.get("updated_at");
    }

    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    // TODO: Add include parameters
    public List<ApplicationNestEgg> getEggs() {
        Pair<Integer, JSONObject> request = application.handleRequest(application.makeRequest(application.getHost() + "api/application/nests/" + this.id + "/eggs",
                "GET", application.getParameters(), null));
        JSONArray eggsArray = (JSONArray) request.getValue2().get("data");
        List<ApplicationNestEgg> eggsList = new ArrayList<>();
        for (Object egg : eggsArray) {
            JSONObject eggObject = (JSONObject) egg;
            eggsList.add(new ApplicationNestEgg((JSONObject) eggObject.get("attributes")));
        }
        return eggsList;
    }

    public ApplicationNestEgg getEgg(int ID) {
        Pair<Integer, JSONObject> request = application.handleRequest(application.makeRequest(application.getHost() + "api/application/nests/" + this.id + "/eggs/" + ID,
                "GET", application.getParameters(), null));
        return new ApplicationNestEgg((JSONObject) request.getValue2().get("attributes"));
    }
}
