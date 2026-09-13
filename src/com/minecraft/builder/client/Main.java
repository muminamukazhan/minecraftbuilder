package com.minecraft.builder.client;

import com.minecraft.builder.builder.CreativeWorldBuilder;
import com.minecraft.builder.builder.SurvivalWorldBuilder;
import com.minecraft.builder.builder.WorldBuilder;
import com.minecraft.builder.director.WorldDirector;
import com.minecraft.builder.product.Structure;

import java.util.Scanner;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        new WorldDirector()
                .buildShowcaseFlatCreativeWorld(new CreativeWorldBuilder(), "Showcase Island")
                .printSummary();

        boolean keepGoing = true;
        while (keepGoing) {
            buildWorld();
            System.out.print("Build another world? (y/n): ");
            keepGoing = SCANNER.nextLine().trim().equalsIgnoreCase("y");
        }
        System.out.println("Done. See you next time!");
    }

    private static void buildWorld() {
        System.out.print("\nMode (1-Creative, 2-Survival): ");
        WorldBuilder builder = SCANNER.nextLine().trim().equals("2")
                ? new SurvivalWorldBuilder()
                : new CreativeWorldBuilder();

        System.out.print("World name: ");
        builder.withName(SCANNER.nextLine().trim());

        System.out.print("Seed (blank for 0): ");
        builder.withSeed(parseSeed(SCANNER.nextLine().trim()));

        System.out.print("Enable cheats? (y/n): ");
        builder.withCheats(SCANNER.nextLine().trim().equalsIgnoreCase("y"));

        Structure[] all = Structure.values();
        System.out.print("Structures 1-" + all.length + " (comma-separated, or blank): ");
        for (String token : SCANNER.nextLine().trim().split(",")) {
            token = token.trim();
            if (token.isEmpty()) {
                continue;
            }
            try {
                builder.addStructure(all[Integer.parseInt(token) - 1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                System.out.println("Skipped invalid structure: " + token);
            }
        }

        try {
            builder.build().printSummary();
        } catch (IllegalStateException exception) {
            System.out.println("Could not build the world: " + exception.getMessage());
        }
    }

    private static long parseSeed(String input) {
        try {
            return input.isEmpty() ? 0L : Long.parseLong(input);
        } catch (NumberFormatException exception) {
            return 0L;
        }
    }
}