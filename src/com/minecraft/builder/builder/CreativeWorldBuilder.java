package com.minecraft.builder.builder;

import com.minecraft.builder.product.GameMode;
import com.minecraft.builder.product.MinecraftWorld;
import com.minecraft.builder.product.Structure;


public class CreativeWorldBuilder extends AbstractWorldBuilder {

    private static final int FLAT_WORLD_BORDER_BLOCKS = 5_000;

    private boolean superFlatTerrain;

    public CreativeWorldBuilder() {
        this.cheatsEnabled = true;
        this.worldBorderSizeInBlocks = FLAT_WORLD_BORDER_BLOCKS;
    }

    public CreativeWorldBuilder withSuperFlatTerrain(boolean superFlatTerrain) {
        this.superFlatTerrain = superFlatTerrain;
        if (superFlatTerrain) {
            addStructure(Structure.SUPERFLAT_PLATFORM);
        }
        return this;
    }

    @Override
    protected GameMode gameMode() {
        return GameMode.CREATIVE;
    }

    @Override
    public MinecraftWorld build() {
        validateCommonState();
        return new MinecraftWorld(
                worldName,
                gameMode(),
                difficulty,
                seed,
                cheatsEnabled,
                false,
                false,
                false,
                worldBorderSizeInBlocks,
                generatedStructures
        );
    }
}