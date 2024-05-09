package de.dafuqs.head_in_the_clouds.api;

import de.dafuqs.head_in_the_clouds.*;
import net.fabricmc.api.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class HeadInTheCloudsAPI {

	/**
	 * Returns the current rain/snow gradient that is rendered in-world
	 * This is the vanilla rain gradient (0.0 for clear sky, 1.0 if fully raining)
	 * diminished when the camera entity approaches cloud level
	 * Above cloud level, the return value will always be 0.0 (no rain)
	 *
	 * @param world     The world
	 * @param tickDelta The current tick delta
	 * @return the rain gradient from 0.0-1.0
	 */
	@Environment(EnvType.CLIENT)
	public float getRainGradient(World world, float tickDelta) {
		return HeadInTheClouds.getRainGradient(world, world.getRainGradient(tickDelta));
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
	public boolean canRainAtPos(World world, BlockPos pos) {
		return pos.getY() > HeadInTheClouds.getCloudHeight(world);
	}

}
