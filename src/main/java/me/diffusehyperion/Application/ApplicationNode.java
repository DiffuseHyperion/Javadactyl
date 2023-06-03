package me.diffusehyperion.Application;

import me.diffusehyperion.HttpMethods;
import me.diffusehyperion.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationNode {

    private Application application;
    private int id;
    private String uuid;
    private boolean isPublic;
    private String name;
    private String description;
    private int locationId;
    private String fqdn;
    private String scheme;
    private boolean behindProxy;
    private boolean maintenanceMode;
    private int memory;
    private int memoryOverallocate;
    private int disk;
    private int diskOverallocate;
    private int uploadSize;
    private int daemonListen;
    private int daemonSftp;
    private String daemonBase;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public ApplicationNode(Application application, JSONObject object) {
        this.application = application;
        this.id = ((Long) object.get("id")).intValue();
        this.uuid = (String) object.get("uuid");
        this.isPublic = (Boolean) object.get("public");
        this.name = (String) object.get("name");
        this.description = (String) object.get("description");
        this.locationId = ((Long) object.get("location_id")).intValue();
        this.fqdn = (String) object.get("fqdn");
        this.scheme = (String) object.get("scheme");
        this.behindProxy = (Boolean) object.get("behind_proxy");
        this.maintenanceMode = (Boolean) object.get("maintenance_mode");
        this.memory = ((Long) object.get("memory")).intValue();
        this.memoryOverallocate = ((Long) object.get("memory_overallocate")).intValue();
        this.disk = ((Long) object.get("disk")).intValue();
        this.diskOverallocate = ((Long) object.get("disk_overallocate")).intValue();
        this.uploadSize = ((Long) object.get("upload_size")).intValue();
        this.daemonListen = ((Long) object.get("daemon_listen")).intValue();
        this.daemonSftp = ((Long) object.get("daemon_sftp")).intValue();
        this.daemonBase = (String) object.get("daemon_base");
        this.createdAt = ZonedDateTime.parse((String) object.get("created_at"));
        this.updatedAt = ZonedDateTime.parse((String) object.get("updated_at"));
    }

    public Application getApplication() {
        return application;
    }

    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getFqdn() {
        return fqdn;
    }

    public String getScheme() {
        return scheme;
    }

    public boolean isBehindProxy() {
        return behindProxy;
    }

    public boolean isMaintenanceMode() {
        return maintenanceMode;
    }

    public int getMemory() {
        return memory;
    }

    public int getMemoryOverallocate() {
        return memoryOverallocate;
    }

    public int getDisk() {
        return disk;
    }

    public int getDiskOverallocate() {
        return diskOverallocate;
    }

    public int getUploadSize() {
        return uploadSize;
    }

    public int getDaemonListen() {
        return daemonListen;
    }

    public int getDaemonSftp() {
        return daemonSftp;
    }

    public String getDaemonBase() {
        return daemonBase;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<ApplicationNodeAllocations> getAllocations() {
        Pair<Integer, JSONObject> request = application.request(application.getHost() + "api/application/nodes/" + id + "/allocations",
                HttpMethods.GET, application.getParameters(), null);
        JSONArray allocationsArray = (JSONArray) request.getValue2().get("data");
        List<ApplicationNodeAllocations> allocationsList = new ArrayList<>();
        for (Object object : allocationsArray) {
            JSONObject jsonObject = (JSONObject) object;
            allocationsList.add(new ApplicationNodeAllocations(this, (JSONObject) jsonObject.get("attributes")));
        }
        return allocationsList;
    }

    public int createAllocation(String ip, List<Integer> ports) {
        JSONObject output = new JSONObject();
        output.put("ip", ip);
        output.put("ports", ports.toArray());
        Pair<Integer, JSONObject> request = application.request(application.getHost() + "api/application/nodes/" + id + "/allocations",
                HttpMethods.POST, application.getParameters(), output.toString());

        return request.getValue1();
    }

    public int deleteAllocation(int allocationID) {
        Pair<Integer, JSONObject> request = application.request(application.getHost() + "api/application/nodes/" + id + "/allocations/" + allocationID,
                HttpMethods.DELETE, application.getParameters(), null);
        return request.getValue1();
    }
}
