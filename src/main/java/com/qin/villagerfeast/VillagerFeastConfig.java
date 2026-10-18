package com.qin.villagerfeast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class VillagerFeastConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("villagerfeast.json");

    // Item id (like "minecraft:apple") -> villager food points
    public Map<String, Integer> foods = new LinkedHashMap<>();

    public static VillagerFeastConfig load() {
        if (Files.exists(PATH)) {
            try (Reader reader = Files.newBufferedReader(PATH)) {
                VillagerFeastConfig config = GSON.fromJson(reader, VillagerFeastConfig.class);
                if (config != null && config.foods != null) {
                    return config;
                }
            } catch (Exception e) {
                // Don't overwrite a broken file, so the player can fix it.
                VillagerFeast.LOGGER.error("Could not read {}, using default foods", PATH, e);
                return createDefault();
            }
        }

        VillagerFeastConfig config = createDefault();
        config.save();
        return config;
    }

    public void save() {
        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            VillagerFeast.LOGGER.error("Could not save {}", PATH, e);
        }
    }

    private static VillagerFeastConfig createDefault() {
        VillagerFeastConfig config = new VillagerFeastConfig();

        // Fruits and plants
        config.add(Items.APPLE, Foods.APPLE.nutrition());
        config.add(Items.BAKED_POTATO, Foods.BAKED_POTATO.nutrition());
        config.add(Items.MELON_SLICE, Foods.MELON_SLICE.nutrition());
        config.add(Items.SWEET_BERRIES, Foods.SWEET_BERRIES.nutrition());
        config.add(Items.GLOW_BERRIES, Foods.GLOW_BERRIES.nutrition());
        config.add(Items.DRIED_KELP, Foods.DRIED_KELP.nutrition());

        // Meat and fish
        config.add(Items.BEEF, Foods.BEEF.nutrition());
        config.add(Items.COOKED_BEEF, Foods.COOKED_BEEF.nutrition());
        config.add(Items.PORKCHOP, Foods.PORKCHOP.nutrition());
        config.add(Items.COOKED_PORKCHOP, Foods.COOKED_PORKCHOP.nutrition());
        config.add(Items.MUTTON, Foods.MUTTON.nutrition());
        config.add(Items.COOKED_MUTTON, Foods.COOKED_MUTTON.nutrition());
        config.add(Items.COOKED_CHICKEN, Foods.COOKED_CHICKEN.nutrition());
        config.add(Items.RABBIT, Foods.RABBIT.nutrition());
        config.add(Items.COOKED_RABBIT, Foods.COOKED_RABBIT.nutrition());
        config.add(Items.COD, Foods.COD.nutrition());
        config.add(Items.COOKED_COD, Foods.COOKED_COD.nutrition());
        config.add(Items.SALMON, Foods.SALMON.nutrition());
        config.add(Items.COOKED_SALMON, Foods.COOKED_SALMON.nutrition());
        config.add(Items.TROPICAL_FISH, Foods.TROPICAL_FISH.nutrition());

        // Treats
        config.add(Items.COOKIE, Foods.COOKIE.nutrition());
        config.add(Items.PUMPKIN_PIE, Foods.PUMPKIN_PIE.nutrition());

        // Fancy food
        config.add(Items.GOLDEN_APPLE, Foods.GOLDEN_APPLE.nutrition());
        config.add(Items.GOLDEN_CARROT, Foods.GOLDEN_CARROT.nutrition());
        config.add(Items.GLISTERING_MELON_SLICE, 6);

        return config;
    }

    private void add(Item item, int nutrition) {
        foods.put(BuiltInRegistries.ITEM.getKey(item).toString(), nutrition);
    }
}