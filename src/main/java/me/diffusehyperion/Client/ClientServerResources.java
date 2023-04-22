package me.diffusehyperion.Client;

import org.json.simple.JSONObject;

public class ClientServerResources {
    private String currentState;
    private boolean isSuspended;
    private long memoryBytes;
    private int cpuAbsolute;
    private long diskBytes;
    private long networkRxBytes;
    private long networkTxBytes;

    public ClientServerResources(JSONObject json) {
        JSONObject attributes = (JSONObject) json.get("attributes");
        this.currentState = (String) attributes.get("current_state");
        this.isSuspended = (boolean) attributes.get("is_suspended");

        JSONObject resources = (JSONObject) json.get("resources");
        this.memoryBytes = (long) json.get("memory_bytes");
        this.cpuAbsolute = (int) (long) json.get("cpu_absolute");
        this.diskBytes = (long) json.get("disk_bytes");
        this.networkRxBytes = (long) json.get("network_rx_bytes");
        this.networkTxBytes = (long) json.get("network_tx_bytes");
    }

    public String getCurrentState() {
        return currentState;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public long getMemoryBytes() {
        return memoryBytes;
    }

    public int getCpuAbsolute() {
        return cpuAbsolute;
    }

    public long getDiskBytes() {
        return diskBytes;
    }

    public long getNetworkRxBytes() {
        return networkRxBytes;
    }

    public long getNetworkTxBytes() {
        return networkTxBytes;
    }

}
