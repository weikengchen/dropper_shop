package com.piggest.minecraft.bukkit.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Nameable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.piggest.minecraft.bukkit.dropper_shop.Dropper_shop_plugin;
import com.piggest.minecraft.bukkit.teleport_machine.Element;
import com.piggest.minecraft.bukkit.teleport_machine.Elements_container;
import com.piggest.minecraft.bukkit.teleport_machine.Unique;
import com.piggest.minecraft.bukkit.utils.Chunk_location;

public abstract class Structure {
	protected String world_name = null;
	protected int x;
	protected int y;
	protected int z;

	@SuppressWarnings("unchecked")
	protected void set_from_save(Map<?, ?> save) {
		if (this instanceof Unique) {
			Unique unique = (Unique) this;
			UUID uuid = UUID.fromString((String) save.get("UUID"));
			unique.set_uuid(uuid);
		}
		this.set_location((String) save.get("world"), (int) save.get("x"), (int) save.get("y"), (int) save.get("z"));
		this.init_after_set_location();
		if (this instanceof Ownable) {
			Ownable ownable = (Ownable) this;
			Object owner_name = save.get("owner");
			if (owner_name == null) {
				ownable.set_owner(null);
			} else if (owner_name.equals("null")) {
				ownable.set_owner(null);
			} else {
				ownable.set_owner((String) owner_name);
			}
		}
		if (this instanceof Capacity_upgradable) {
			Capacity_upgradable upgradable = (Capacity_upgradable) this;
			upgradable.set_capacity_level((int) save.get("structure-level"));
		}
		if (this instanceof Nameable) {
			Nameable nameable = (Nameable) this;
			nameable.setCustomName((String) save.get("name"));
		}
		if (this instanceof Elements_container) {
			Elements_container elements_container = (Elements_container) this;
			HashMap<String, Integer> elements_map = (HashMap<String, Integer>) save.get("elements");
			for (Element element : Element.values()) {
				elements_container.set_amount(element, elements_map.get(element.name()));
			}
		}
	}

	public void set_location(Location loc) {
		this.set_location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	public void set_location(String world_name, int x, int y, int z) {
		this.world_name = world_name;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location get_location() {
		return new Location(Bukkit.getWorld(this.world_name), this.x, this.y, this.z);
	}

	public int get_chunk_x() {
		return this.x >> 4;
	}

	public int get_chunk_z() {
		return this.z >> 4;
	}

	public Chunk_location get_chunk_location() {
		return new Chunk_location(this.world_name, this.x >> 4, this.z >> 4);
	}

	/**
	 * 判断结构所在区块是否被加载
	 */
	public boolean is_loaded() {
		return this.get_chunk_location().is_loaded();
	}

	protected HashMap<String, Object> get_save() {
		HashMap<String, Object> save = new HashMap<String, Object>();
		save.put("world", this.world_name);
		save.put("x", this.x);
		save.put("y", this.y);
		save.put("z", this.z);
		if (this instanceof Ownable) {
			Ownable ownable = (Ownable) this;
			save.put("owner", ownable.get_owner_name());
		}
		if (this instanceof Unique) {
			save.put("UUID", ((Unique) this).get_uuid().toString());
		}
		if (this instanceof Capacity_upgradable) {
			Capacity_upgradable upgradable = (Capacity_upgradable) this;
			save.put("structure-level", upgradable.get_capacity_level());
		}
		if (this instanceof Nameable) {
			Nameable nameable = (Nameable) this;
			save.put("name", nameable.getCustomName());
		}
		if (this instanceof Elements_container) {
			Elements_container elements_container = (Elements_container) this;
			HashMap<String, Integer> elements_map = new HashMap<String, Integer>();
			for (Element element : Element.values()) {
				elements_map.put(element.name(), elements_container.get_amount(element));
			}
			save.put("elements", elements_map);
		}
		return save;
	}

	public Structure_manager<? extends Structure> get_manager() {
		return Dropper_shop_plugin.instance.get_structure_manager().get(this.getClass());
	}

	/**
	 * 创建前调用的函数，返回true表明创建成功。
	 */
	public abstract boolean create_condition(Player player);

	/**
	 * 在设定好位置后、读取存档之前进行的初始化操作。
	 */
	protected abstract void init_after_set_location();

	public void remove() {
		this.get_manager().remove(this);
	}

	/**
	 * 破坏方块时调用的函数，返回true表面允许破坏。
	 */
	protected abstract boolean on_break(Player player);

	@Override
	public String toString() {
		return this.getClass().getName() + "(" + this.world_name + "," + this.x + "," + this.y + "," + this.z + ")";
	}

	protected abstract boolean completed();

	public String get_display_name() {
		return this.getClass().getSimpleName();
	}
	
	public abstract ItemStack[] get_drop_items();
}
