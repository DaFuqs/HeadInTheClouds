package de.dafuqs.head_in_the_clouds.api;

import de.dafuqs.head_in_the_clouds.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;

public class HeadInTheCloudsAPI {

	/**
	 * Returns the current rain/snow gradient that is rendered in-level
	 * This is the vanilla rain gradient (0.0 for clear sky, 1.0 if fully raining)
	 * diminished when the given y value approaches cloud level
	 * Above cloud level, the return value will always be 0.0 (no rain)
	 *
	 * @param level The level
	 * @param pos The position to check
	 * @param tickProgress The current tick delta
	 * @return the rain gradient from 0.0 (no rain) to 1.0 (full rain)
	 */
	public static float getRainGradient(Level level, Vec3 pos, float tickProgress) {
		return HeadInTheClouds.getRainGradient(level, pos, level.getRainLevel(tickProgress));
	}

	/**
	 * If it can possibly rain/snow at the given pos
	 * True if below cloud height, false if above the clouds
	 * This does not mean that it is currently raining at that pos,
	 * but it is an override for the vanilla logic.
	 * If allowRainAtPos() returns true, query the actual weather at the pos, using level.allowRainAtPos(pos)
	 *
	 * @param level The level
	 * @param pos   The position to test
	 * @return If it can possibly rain/snow at the given pos
	 */
	public static boolean allowRainAtPos(Level level, BlockPos pos) {
		float cloudY = HeadInTheClouds.getCloudHeight(level, Vec3.atCenterOf(pos));
		return pos.getY() < cloudY;
	}
}
