package com.minecraft.builder.product;

import java.util.Collections;
import java.util.List;


public final class MinecraftWorld {

    private final String worldName;
    private final GameMode gameMode;
    private final Difficulty difficulty;
    private final long seed;
    private final boolean cheatsEnabled;
    private final boolean mobSpawningEnabled;
    private final boolean fallDamageEnabled;
    private final boolean naturalRegenerationEnabled;
    private final int worldBorderSizeInBlocks;
    private final List<Structure> generatedStructures;

    public MinecraftWorld(String worldName,
                          GameMode gameMode,
                          Difficulty difficulty,
                          long seed,
                          boolean cheatsEnabled,
                          boolean mobSpawningEnabled,
                          boolean fallDamageEnabled,
                          boolean naturalRegenerationEnabled,
                          int worldBorderSizeInBlocks,
                          List<Structure> generatedStructures) {
        this.worldName = worldName;
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.seed = seed;
        this.cheatsEnabled = cheatsEnabled;
        this.mobSpawningEnabled = mobSpawningEnabled;
        this.fallDamageEnabled = fallDamageEnabled;
        this.naturalRegenerationEnabled = naturalRegenerationEnabled;
        this.worldBorderSizeInBlocks = worldBorderSizeInBlocks;
        this.generatedStructures = Collections.unmodifiableList(generatedStructures);
    }

    public String getWorldName() {
        return worldName;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public long getSeed() {
        return seed;
    }

    public boolean isCheatsEnabled() {
        return cheatsEnabled;
    }

    public boolean isMobSpawningEnabled() {
        return mobSpawningEnabled;
    }

    public boolean isFallDamageEnabled() {
        return fallDamageEnabled;
    }

    public boolean isNaturalRegenerationEnabled() {
        return naturalRegenerationEnabled;
    }

    public int getWorldBorderSizeInBlocks() {
        return worldBorderSizeInBlocks;
    }

    public List<Structure> getGeneratedStructures() {
        return generatedStructures;
    }


    public void printSummary() {
        System.out.println("=== World: " + worldName + " ===");
        System.out.println("Game mode:            " + gameMode);
        System.out.println("Difficulty:           " + difficulty);
        System.out.println("Seed:                 " + seed);
        System.out.println("Cheats enabled:       " + cheatsEnabled);
        System.out.println("Mob spawning:         " + mobSpawningEnabled);
        System.out.println("Fall damage:          " + fallDamageEnabled);
        System.out.println("Natural regeneration: " + naturalRegenerationEnabled);
        System.out.println("World border (blocks):" + worldBorderSizeInBlocks);
        System.out.println("Structures:           " + generatedStructures);
        System.out.println();
    }
}