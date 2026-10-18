package com.qin.villagerfeast;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.VillagerFood;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VillagerFeast implements ModInitializer {
    public static final String MOD_ID = "villagerfeast";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static VillagerFeastConfig CONFIG;

    @Override
    public void onInitialize() {
        CONFIG = VillagerFeastConfig.load();
        LOGGER.info("Villager Feast loaded {} foods from config", CONFIG.foods.size());

        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(
                    item -> getPoints(BuiltInRegistries.ITEM.getKey(item).toString()) > 0,
                    (builder, item) -> {
                        int points = getPoints(BuiltInRegistries.ITEM.getKey(item).toString());
                        builder.set(DataComponents.VILLAGER_FOOD, new VillagerFood(points));
                    }
            );
        });
    }

    private static int getPoints(String itemId) {
        Integer points = CONFIG.foods.get(itemId);
        return points == null ? 0 : points;
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}