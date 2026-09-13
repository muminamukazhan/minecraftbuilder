package com.minecraft.builder.builder;

import com.minecraft.builder.product.Difficulty;
import com.minecraft.builder.product.MinecraftWorld;
import com.minecraft.builder.product.Structure;


public interface WorldBuilder {

    WorldBuilder withName(String worldName);

    WorldBuilder withSeed(long seed);

    WorldBuilder withDifficulty(Difficulty difficulty);

    WorldBuilder withCheats(boolean cheatsEnabled);

    WorldBuilder addStructure(Structure structure);


    MinecraftWorld build();
}