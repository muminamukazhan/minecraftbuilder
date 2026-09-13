# Minecraft World Builder

A Java implementation of the **Builder** creational design pattern, applied to configuring and
generating Minecraft worlds. The project was built for Assignment #1 (ShP-2216 — Software Design
Patterns, Astana IT University).

## What this project is

A Minecraft world is configured from many independent, optional settings — name, seed,
difficulty, cheats, mob spawning, fall damage, natural regeneration, world-border size, and a
list of generated structures. Building it with one giant constructor call would be unreadable and
error-prone, and different game modes need meaningfully different defaults and rules.

This project models that with the Builder pattern:

| Role | Class | Responsibility |
|---|---|---|
| **Product** | `MinecraftWorld` | Object designed to be immutable, holding the final, fully-configured world. |
| **Builder** | `WorldBuilder` | Interface declaring the fluent construction steps. |
| **Abstract Builder** | `AbstractWorldBuilder` | Shares common fields, fluent setters, and validation between concrete builders. |
| **Concrete Builder** | `CreativeWorldBuilder` | Produces a Creative-mode world (cheats on, flat terrain option, small border). |
| **Concrete Builder** | `SurvivalWorldBuilder` | Produces a Survival-mode world (mob spawning / fall damage on, optional hardcore mode). |
| **Director** | `WorldDirector` | Orchestrates two ready-made, reusable configurations. |
| **Client** | `Main` | Interactive console demo that drives the builders. |

Every fluent setter returns the builder itself (method chaining), and `build()` validates state
and throws a clear `IllegalStateException` if the configuration is invalid (e.g. missing world
name, or a hardcore Survival world with cheats enabled).

## Project structure

```
src/main/java/com/minecraft/builder/
├── product/
│   ├── MinecraftWorld.java      # the Product
│   ├── GameMode.java
│   ├── Difficulty.java
│   └── Structure.java
├── builder/
│   ├── WorldBuilder.java        # the Builder interface
│   ├── AbstractWorldBuilder.java
│   ├── CreativeWorldBuilder.java
│   └── SurvivalWorldBuilder.java
├── director/
│   └── WorldDirector.java       # the (optional) Director
└── client/
    └── Main.java                # the Client / demo entry point
```

## Requirements

- JDK 17
- IntelliJ IDEA (recommended) or any Java 17-compatible build tool

## How to build each representation

### Creative world

```java
MinecraftWorld world = new CreativeWorldBuilder()
        .withName("Showcase Island")
        .withSuperFlatTerrain(true)
        .addStructure(Structure.VILLAGE)
        .build();
```

Defaults: cheats enabled, mob spawning disabled, fall damage disabled, natural regeneration disabled,
and a small world border (5,000 blocks).

### Survival world

```java
MinecraftWorld world = new SurvivalWorldBuilder()
        .withName("Hardcore Run")
        .withSeed(123456789L)
        .withHardcoreMode(true)
        .addStructure(Structure.STRONGHOLD)
        .addStructure(Structure.MINESHAFT)
        .build();
```

Defaults: cheats disabled, mob spawning and fall damage enabled. `withHardcoreMode(true)` forces
`Difficulty.HARD` and makes `build()` reject the configuration if cheats were also enabled.

### Using the Director (known/reusable configurations)

```java
MinecraftWorld showcase = new WorldDirector()
        .buildShowcaseFlatCreativeWorld(new CreativeWorldBuilder(), "Showcase Island");

MinecraftWorld hardcore = new WorldDirector()
        .buildHardcoreSurvivalWorld(new SurvivalWorldBuilder(), "Hardcore Run", 123456789L);
```

## How to run

1. Open the project in IntelliJ IDEA (or import as a plain JDK 17 project).
2. Run `com.minecraft.builder.client.Main`.
3. The demo first prints a showcase Creative world built via `WorldDirector`, then prompts you
   interactively in the console to build additional worlds:
   - choose Creative or Survival mode,
   - enter a world name and seed,
   - enable/disable cheats,
   - pick structures by number from the printed list.
4. Each world's final configuration is printed with `printSummary()`. Invalid configurations
   (e.g. a blank name, or hardcore + cheats) are caught and reported instead of crashing.

Command-line alternative:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.minecraft.builder.client.Main
```

## Clean Code notes

Validation, shared fluent setters, and named constants (e.g. `DEFAULT_WORLD_BORDER_BLOCKS`,
`FLAT_WORLD_BORDER_BLOCKS`) live in `AbstractWorldBuilder` and its subclasses to avoid duplication
and magic numbers. See the accompanying report for a full breakdown of the five Clean Code
principles applied, each with before/after code excerpts.
