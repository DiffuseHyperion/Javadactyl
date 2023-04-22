package me.diffusehyperion.Client;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ClientServerBackup {

    private UUID uuid;
    private String name;
    private List<String> ignoredFiles = new ArrayList<>();
    private String sha256Hash;
    private long bytes;
    private ZonedDateTime createdAt;
    private ZonedDateTime completedAt;

    public ClientServerBackup(JSONObject json) {
        this.uuid = UUID.fromString((String) json.get("uuid"));
        this.name = (String) json.get("name");

        JSONArray ignoredFilesArray = (JSONArray) json.get("ignored_files");
        for (Object ignoredFile : ignoredFilesArray) {
            this.ignoredFiles.add((String) ignoredFile);
        }

        this.sha256Hash = (String) json.get("sha256_hash");
        this.bytes = (Long) json.get("bytes");
        this.createdAt = ZonedDateTime.parse((String) json.get("created_at"));
        this.completedAt = ZonedDateTime.parse((String) json.get("completed_at"));
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<String> getIgnoredFiles() {
        return ignoredFiles;
    }

    public String getSha256Hash() {
        return sha256Hash;
    }

    public long getBytes() {
        return bytes;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getCompletedAt() {
        return completedAt;
    }
}