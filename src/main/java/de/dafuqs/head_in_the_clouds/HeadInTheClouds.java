package de.dafuqs.head_in_the_clouds;

import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.attribute.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;

public class HeadInTheClouds {
    private static final double ADDITIONAL_CLOUD_HEIGHT = 3.0;
    private static final double GRADIENT_HEIGHT = 6.0;
    private static final double INVERTED_GRADIENT_HEIGHT = 1.0 / GRADIENT_HEIGHT;
    
    public static float getCloudHeight(Level level, Vec3 pos) {
        return level.environmentAttributes().getValue(EnvironmentAttributes.CLOUD_HEIGHT, pos);
    }
    
    public static float getRainGradient(Level world, Vec3 pos, float original) {
        float cloudY = HeadInTheClouds.getCloudHeight(world, pos);

        double y = pos.y();
        double maxY = cloudY + ADDITIONAL_CLOUD_HEIGHT;
        if (y < maxY - GRADIENT_HEIGHT) {
            // no override here
            return original;
        } else if (y < maxY) {
            // exactly in the clouds
            return (float)((maxY - y) * INVERTED_GRADIENT_HEIGHT) * original;
        } else {
            // we're above clouds
            return 0F;
        }
    }
}
