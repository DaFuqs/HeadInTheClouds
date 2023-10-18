package de.dafuqs.head_in_the_clouds;

import de.dafuqs.head_in_the_clouds.config.*;
import me.shedaniel.autoconfig.*;
import me.shedaniel.autoconfig.serializer.*;
import net.fabricmc.api.*;
import net.minecraft.client.*;
import net.minecraft.client.world.*;
import net.minecraft.world.*;

public class HeadInTheClouds implements ModInitializer {
    
    // since cloud height information is only present on clients, servers sadly have to get it added via config
    public static Config CONFIG;

    @Override
    public void onInitialize() {
        AutoConfig.register(Config.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(Config.class).getConfig();
    }

    public static float getCloudHeight(World world) {
        if(world.isClient) {
            return getCloudHeightClient(world);
        } else {
            String regKey = world.getRegistryKey().getValue().toString();
            return CONFIG.WorldCloudHeights.getOrDefault(regKey, Float.MAX_VALUE);
        }
    }

    @Environment(EnvType.CLIENT)
    private static float getCloudHeightClient(World world) {
        if(world instanceof ClientWorld clientWorld) {
            return clientWorld.getDimensionEffects().getCloudsHeight();
        }
        return world.getBottomY();
    }

    private static final float ADDITIONAL_CLOUD_HEIGHT = 3.0F;
    private static final float GRADIENT_HEIGHT = 6.0F;
    private static final float INVERTED_GRADIENT_HEIGHT = 1.0F / GRADIENT_HEIGHT;

    @Environment(EnvType.CLIENT)
    public static float getRainGradient(World world, float original) {
        if (MinecraftClient.getInstance().cameraEntity != null) {
            double playerY = MinecraftClient.getInstance().cameraEntity.getPos().y;
            float cloudY = HeadInTheClouds.getCloudHeight(world) + ADDITIONAL_CLOUD_HEIGHT;

            if (playerY < cloudY - GRADIENT_HEIGHT) {
                // normal
            } else if (playerY < cloudY) {
                return (float) ((cloudY - playerY) * INVERTED_GRADIENT_HEIGHT) * original;
            } else {
                return 0.0F;
            }

        }
        return original;
    }

}
