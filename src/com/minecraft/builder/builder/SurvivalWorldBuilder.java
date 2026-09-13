package com.minecraft.builder.builder;

import com.minecraft.builder.product.Difficulty;
import com.minecraft.builder.product.GameMode;
import com.minecraft.builder.product.MinecraftWorld;

public class SurvivalWorldBuilder extends AbstractWorldBuilder {

    private boolean hardcoreMode;

    public SurvivalWorldBuilder() {
        this.cheatsEnabled = false;
        this.difficulty = Difficulty.NORMAL;
    }


    public SurvivalWorldBuilder withHardcoreMode(boolean hardcoreMode) {
        this.hardcoreMode = hardcoreMode;
        if (hardcoreMode) {
            this.difficulty = Difficulty.HARD;
        }
        return this;
    }

    @Override
    protected GameMode gameMode() {
        return GameMode.SURVIVAL;
    }

    @Override
    public MinecraftWorld build() {
        validateCommonState();
        validateHardcoreState();
        return new MinecraftWorld(
                worldName,
                gameMode(),
                difficulty,
                seed,
                cheatsEnabled,
                true,
                true,
                !hardcoreMode,
                worldBorderSizeInBlocks,
                generatedStructures
        );
    }

    private void validateHardcoreState() {
        if (hardcoreMode && cheatsEnabled) {
            throw new IllegalStateException(
                    "Hardcore survival worlds cannot have cheats enabled.");
        }
    }
}