package me.diffusehyperion.Client;

import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;

import java.util.Objects;

public class ClientServerLimits {
    private final int memory;
    private final int swap;
    private final int disk;
    private final int io;
    private final int cpu;
    @Nullable private final Integer threads;
    private final Boolean oomDisabled;

    public ClientServerLimits(JSONObject object) {
        memory = ((Long) object.get("memory")).intValue();
        swap = ((Long) object.get("swap")).intValue();
        disk = ((Long) object.get("disk")).intValue();
        io = ((Long) object.get("io")).intValue();
        cpu = ((Long) object.get("cpu")).intValue();
        if (Objects.isNull(object.get("threads"))) {
            threads = null;
        } else {
            threads = ((Long) object.get("threads")).intValue();
        }
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

    public @Nullable Integer getThreads() {
        return threads;
    }

    public Boolean isOomDisabled() {
        return oomDisabled;
    }
}
