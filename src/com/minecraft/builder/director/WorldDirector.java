package com.minecraft.builder.director;

import com.minecraft.builder.builder.CreativeWorldBuilder;
import com.minecraft.builder.builder.SurvivalWorldBuilder;
import com.minecraft.builder.product.MinecraftWorld;
import com.minecraft.builder.product.Structure;


public class WorldDirector {

    public MinecraftWorld buildShowcaseFlatCreativeWorld(CreativeWorldBuilder builder, String worldName) {
        return builder
                .withSuperFlatTerrain(true)
                .withName(worldName)
                .addStructure(Structure.VILLAGE)
                .build();
    }

    public MinecraftWorld buildHardcoreSurvivalWorld(SurvivalWorldBuilder builder, String worldName, long seed) {
        return builder
                .withHardcoreMode(true)
                .withName(worldName)
                .withSeed(seed)
                .addStructure(Structure.STRONGHOLD)
                .addStructure(Structure.MINESHAFT)
                .build();
    }
}