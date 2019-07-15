package com.piggest.minecraft.bukkit.advanced_furnace;

import org.bukkit.NamespacedKey;

public interface Chemical {

	public String get_displayname();

	public String get_name();
	
	public NamespacedKey get_namespacedkey();
	
	public static Chemical get_chemical(String name) {
		if (name.contains("_log")) {
			name = "log";
		}
		if (name.contains("water")) {
			name = "water";
		}
		Solid solid = Solid.get_solid(name);
		if (solid != null) {
			return solid;
		}
		Gas gas = Gas.get_gas(name);
		if (gas != null) {
			return gas;
		}
		return null;
	}
}
