package de.dafuqs.head_in_the_clouds;

import net.fabricmc.api.*;
import net.minecraft.client.*;
import net.minecraft.world.*;

import java.util.*;

public class HeadInTheClouds implements ModInitializer {

    @Override
    public void onInitialize() {
    
    }
    
    private static final double ADDITIONAL_CLOUD_HEIGHT = 3.0D;
    private static final double GRADIENT_HEIGHT = 6.0D;
    private static final double INVERTED_GRADIENT_HEIGHT = 1.0D / GRADIENT_HEIGHT;
    
    public static Optional<Integer> getCloudHeight(World world) {
        return world.getDimension().cloudHeight();
    }
    
    public static float getRainGradient(World world, float original) {
        if (MinecraftClient.getInstance().cameraEntity != null) {
            double playerY = MinecraftClient.getInstance().cameraEntity.getPos().y;
            Optional<Integer> cloudY = HeadInTheClouds.getCloudHeight(world);
            if(cloudY.isEmpty()) {
                return original;
            }
            
            double y = cloudY.get() + ADDITIONAL_CLOUD_HEIGHT;
            if (playerY < y - GRADIENT_HEIGHT) {
                // normal
            } else if (playerY < y) {
                return (float) ((y - playerY) * INVERTED_GRADIENT_HEIGHT) * original;
            } else {
                return 0.0F;
            }

        }
        return original;
    }

}
