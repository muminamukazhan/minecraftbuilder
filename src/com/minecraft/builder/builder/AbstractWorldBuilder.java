package com.minecraft.builder.builder;

import com.minecraft.builder.product.Difficulty;
import com.minecraft.builder.product.GameMode;
import com.minecraft.builder.product.Structure;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractWorldBuilder implements WorldBuilder {

    protected static final int DEFAULT_WORLD_BORDER_BLOCKS = 60_000_000;

    protected String worldName;
    protected Difficulty difficulty = Difficulty.NORMAL;
    protected long seed;
    protected boolean cheatsEnabled;
    protected int worldBorderSizeInBlocks = DEFAULT_WORLD_BORDER_BLOCKS;
    protected final List<Structure> generatedStructures = new ArrayList<>();

    @Override
    public WorldBuilder withName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    @Override
    public WorldBuilder withSeed(long seed) {
        this.seed = seed;
        return this;
    }

    @Override
    public WorldBuilder withDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    @Override
    public WorldBuilder withCheats(boolean cheatsEnabled) {
        this.cheatsEnabled = cheatsEnabled;
        return this;
    }

    @Override
    public WorldBuilder addStructure(Structure structure) {
        generatedStructures.add(structure);
        return this;
    }

    protected void validateCommonState() {
        if (worldName == null || worldName.isBlank()) {
            throw new IllegalStateException(
                    "Cannot build a MinecraftWorld without a name. Call withName(...) first.");
        }
    }


    protected abstract GameMode gameMode();
}