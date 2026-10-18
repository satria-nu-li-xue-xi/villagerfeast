package com.qin.villagerfeast.client;

import com.qin.villagerfeast.VillagerFeast;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.network.chat.Component;

import java.util.Map;

public class VillagerFeastModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Component.literal("Villager Feast"));

            ConfigCategory foods = builder.getOrCreateCategory(Component.literal("Foods"));
            ConfigEntryBuilder entries = builder.entryBuilder();

            // One number field per food in the config
            for (Map.Entry<String, Integer> food : VillagerFeast.CONFIG.foods.entrySet()) {
                String itemId = food.getKey();
                foods.addEntry(entries.startIntField(Component.literal(itemId), food.getValue())
                        .setMin(0)
                        .setTooltip(Component.literal("Villager food points. 0 disables it. Restart to apply."))
                        .setSaveConsumer(value -> VillagerFeast.CONFIG.foods.put(itemId, value))
                        .build());
            }

            builder.setSavingRunnable(() -> VillagerFeast.CONFIG.save());
            return builder.build();
        };
    }
}