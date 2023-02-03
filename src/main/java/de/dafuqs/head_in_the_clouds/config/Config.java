package de.dafuqs.head_in_the_clouds.config;

import me.shedaniel.autoconfig.*;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.*;

import java.util.*;

@me.shedaniel.autoconfig.annotation.Config(name = "HeadInTheClouds")
public class Config implements ConfigData {

	@Comment("")
	public Map<String, Float> WorldCloudHeights = new HashMap<>();

	@Override
	public void validatePostLoad() {
		if(WorldCloudHeights.isEmpty()) {
			WorldCloudHeights.put("minecraft:overworld", 182.0F);
		}
	}

}
