package org.example.Client;

import org.json.simple.JSONObject;

public class ClientServerLimits {
    private final Long memory;
    private final Long swap;
    private final Long disk;
    private final Long io;
    private final Long cpu;
    private final Long threads;
    private final Boolean oomDisabled;

    public ClientServerLimits(JSONObject object) {
        memory = (Long) object.get("memory");
        swap = (Long) object.get("swap");
        disk = (Long) object.get("disk");
        io = (Long) object.get("io");
        cpu = (Long) object.get("cpu");
        threads = (Long) object.get("threads");
        oomDisabled = (Boolean) object.get("oom_disabled");
    }

    public Long getMemory() {
        return memory;
    }

    public Long getSwap() {
        return swap;
    }

    public Long getDisk() {
        return disk;
    }

    public Long getIo() {
        return io;
    }

    public Long getCpu() {
        return cpu;
    }

    public Long getThreads() {
        return threads;
    }

    public Boolean isOomDisabled() {
        return oomDisabled;
    }
}
