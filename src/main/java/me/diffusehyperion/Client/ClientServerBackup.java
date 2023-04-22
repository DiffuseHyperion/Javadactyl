package me.diffusehyperion.Client;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.json.simple.JSONObject;

public class ClientServerBackup {

    private UUID uuid;
    private String name;
    private String[] ignoredFiles;
    private String sha256Hash;
    private long bytes;
    private ZonedDateTime createdAt;
    private ZonedDateTime completedAt;

    public ClientServerBackup(JSONObject json) {
        this.uuid = UUID.fromString((String) json.get("uuid"));
        this.name = (String) json.get("name");

        Object ignoredFilesObj = json.get("ignored_files");
        if (ignoredFilesObj instanceof String) {
            // If the ignored_files is a string, split it by commas and store as an array
            this.ignoredFiles = ((String) ignoredFilesObj).split(",");
        } else {
            // Otherwise, leave it as null
            this.ignoredFiles = null;
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

    public String[] getIgnoredFiles() {
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