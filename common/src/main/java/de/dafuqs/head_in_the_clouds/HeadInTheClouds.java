package de.dafuqs.head_in_the_clouds;

import net.minecraft.world.World;

import java.util.Optional;

public class HeadInTheClouds {
    private static final double ADDITIONAL_CLOUD_HEIGHT = 3.0;
    private static final double GRADIENT_HEIGHT = 6.0;
    private static final double INVERTED_GRADIENT_HEIGHT = 1.0 / GRADIENT_HEIGHT;

    public static Optional<Integer> getCloudHeight(World world) {
        return world.getDimension().cloudHeight();
    }

    public static float getRainGradient(World world, double y, float original) {
        Optional<Integer> cloudY = HeadInTheClouds.getCloudHeight(world);
        if (cloudY.isEmpty())
            return original;

        double maxY = cloudY.get() + ADDITIONAL_CLOUD_HEIGHT;
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
