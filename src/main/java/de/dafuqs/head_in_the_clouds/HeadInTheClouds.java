package de.dafuqs.head_in_the_clouds;

import net.fabricmc.api.*;
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
    
    public static float getRainGradient(World world, double y, float original) {
        Optional<Integer> cloudY = HeadInTheClouds.getCloudHeight(world);
        if(cloudY.isEmpty()) {
            return original;
        }
        
        double maxY = cloudY.get() + ADDITIONAL_CLOUD_HEIGHT;
        if (y < maxY - GRADIENT_HEIGHT) {
            // no override here
            return original;
        } else if (y < maxY) {
            // exactly in the clouds
            return (float) ((maxY - y) * INVERTED_GRADIENT_HEIGHT) * original;
        } else {
            // we're above clouds
            return 0.0F;
        }
    }

}
