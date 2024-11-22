package de.dafuqs.head_in_the_clouds.config;

import me.shedaniel.autoconfig.*;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.*;

import java.util.*;

@me.shedaniel.autoconfig.annotation.Config(name = "HeadInTheClouds")
public class Config implements ConfigData {
	
	@Comment("Cloud height for use for server logic. On clients the cloud height is queried directly from the worlds dimension effects")
	public Map<String, Float> ServerWorldCloudHeights = new HashMap<>();

	@Override
	public void validatePostLoad() {
		if(ServerWorldCloudHeights.isEmpty()) {
			ServerWorldCloudHeights.put("minecraft:overworld", 182.0F);
		}
	}

}
