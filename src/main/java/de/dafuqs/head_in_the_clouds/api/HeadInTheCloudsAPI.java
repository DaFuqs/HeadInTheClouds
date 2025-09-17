package de.dafuqs.head_in_the_clouds.api;

import de.dafuqs.head_in_the_clouds.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class HeadInTheCloudsAPI {

	/**
	 * Returns the current rain/snow gradient that is rendered in-world
	 * This is the vanilla rain gradient (0.0 for clear sky, 1.0 if fully raining)
	 * diminished when the camera entity approaches cloud level
	 * Above cloud level, the return value will always be 0.0 (no rain)
	 *
	 * @param world     The world
	 * @param tickProgress The current tick delta
	 * @return the rain gradient from 0.0-1.0
	 */
	public static float getRainGradient(World world, float tickProgress) {
		return HeadInTheClouds.getRainGradient(world, world.getRainGradient(tickProgress));
	}

	/**
	 * If it can possibly rain/snow at the given pos
	 * True if below cloud height, false if above the clouds
	 * This does not mean that it is currently raining at that pos,
	 * but it is an override for the vanilla logic.
	 * If canRainAtPos() returns true, query the actual weather at the pos, using world.hasRain(pos)
	 *
	 * @param world The world
	 * @param pos   The position to test
	 * @return If it can possibly rain/snow at the given pos
	 */
	public static boolean allowRainAtPos(World world, BlockPos pos) {
		Optional<Integer> cloudY = HeadInTheClouds.getCloudHeight(world);
		return cloudY.isPresent() && cloudY.get() > pos.getY();
	}

}
