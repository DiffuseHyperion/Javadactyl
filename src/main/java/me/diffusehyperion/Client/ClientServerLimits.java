package me.diffusehyperion.Client;

import org.json.simple.JSONObject;

public class ClientServerLimits {
    private final int memory;
    private final int swap;
    private final int disk;
    private final int io;
    private final int cpu;
    private final int threads;
    private final Boolean oomDisabled;

    public ClientServerLimits(JSONObject object) {
        memory = ((Long) object.get("memory")).intValue();
        swap = ((Long) object.get("swap")).intValue();
        disk = ((Long) object.get("disk")).intValue();
        io = ((Long) object.get("io")).intValue();
        cpu = ((Long) object.get("cpu")).intValue();
        threads = ((Long) object.get("threads")).intValue();
        oomDisabled = (Boolean) object.get("oom_disabled");
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

    public int getThreads() {
        return threads;
    }

    public Boolean isOomDisabled() {
        return oomDisabled;
    }
}
