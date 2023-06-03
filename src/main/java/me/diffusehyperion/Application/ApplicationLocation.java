package me.diffusehyperion.Application;

import me.diffusehyperion.HttpMethods;
import me.diffusehyperion.Pair;
import org.json.simple.JSONObject;

public class ApplicationLocation {

    private final Application application;
    private final int id;
    private String shortName;
    private String longName;
    private final String updatedAt;
    private final String createdAt;

    public ApplicationLocation(Application application, JSONObject object) {
        this.application = application;
        this.id = ((Long) object.get("id")).intValue();
        this.shortName = (String) object.get("short");
        this.longName = (String) object.get("long");
        this.updatedAt = (String) object.get("updated_at");
        this.createdAt = (String) object.get("created_at");
    }

    public Application getApplication() {
        return application;
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }


    /**
     * @return Hpst response code
     */
    public int setShortName(String shortName) {
        JSONObject output = new JSONObject();
        output.put("short", shortName);
        Pair<Integer, JSONObject> request = application.request(application.getHost() + "api/application/locations/" + this.id,
                HttpMethods.POST, application.getParameters(), output.toJSONString());

        if (request.getValue1() == 200) {
            this.shortName = shortName;
        }
        return request.getValue1();
    }

    /**
     * @return Hpst response code
     */
    public int setLongName(String longName) {
        JSONObject output = new JSONObject();
        output.put("long", longName);
        Pair<Integer, JSONObject> request = application.request(application.getHost() + "api/application/locations/" + this.id,
                HttpMethods.POST, application.getParameters(), output.toJSONString());

        if (request.getValue1() == 200) {
            this.longName = longName;
        }
        return request.getValue1();
    }
}
