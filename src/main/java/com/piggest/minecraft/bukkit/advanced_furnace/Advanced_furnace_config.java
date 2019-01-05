package com.piggest.minecraft.bukkit.advanced_furnace;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;

import com.piggest.minecraft.bukkit.gui.Gui_config;
import com.piggest.minecraft.bukkit.gui.Gui_slot_type;

public class Advanced_furnace_config extends Gui_config {
	public Advanced_furnace_config() {
		this.set_gui(10, Material.BLUE_STAINED_GLASS_PANE, "§r左边放固体原料", Gui_slot_type.Indicator);
		this.set_gui(12, Material.BLUE_STAINED_GLASS_PANE, "§r左边放气体原料", Gui_slot_type.Indicator);
		this.set_gui(14, Material.BLUE_STAINED_GLASS_PANE, "§r左边放液体原料", Gui_slot_type.Indicator);
		this.set_gui(16, Material.BLUE_STAINED_GLASS_PANE, "§r右边放燃料", Gui_slot_type.Indicator);
		this.set_gui(19, Material.BLUE_STAINED_GLASS_PANE, "§r左边为固体产品", Gui_slot_type.Indicator);
		this.set_gui(21, Material.BLUE_STAINED_GLASS_PANE, "§r左边为气体产品", Gui_slot_type.Indicator);
		this.set_gui(23, Material.BLUE_STAINED_GLASS_PANE, "§r左边为液体产品", Gui_slot_type.Indicator);
		this.set_gui(25, Material.BLUE_STAINED_GLASS_PANE, "§r右边为温度", Gui_slot_type.Indicator);
		this.set_gui(0, Material.CRAFTING_TABLE, "§e内部信息", Gui_slot_type.Indicator);
		this.set_gui(2, Material.HOPPER_MINECART, "§e固体产品自动提取", Gui_slot_type.Switch);
		this.set_gui(3, Material.CHEST_MINECART, "§r立刻取出固体", Gui_slot_type.Button);
		this.set_gui(4, Material.MINECART, "§r清除全部固体", Gui_slot_type.Button);
		this.set_gui(5, Material.GLASS_BOTTLE, "§e气体自动排放", Gui_slot_type.Switch);
		this.set_gui(6, Material.DISPENSER, "§r清除全部气体", Gui_slot_type.Button);
		this.set_gui(8, Material.CHEST, "§e金币制造", Gui_slot_type.Switch);
		this.set_gui(26, Material.FURNACE, "§e信息", Gui_slot_type.Indicator);
	}

	@Override
	public String get_gui_name() {
		return "高级熔炉";
	}

	@Override
	public int get_slot_num() {
		return 27;
	}

	@Override
	public InventoryType get_inventory_type() {
		return InventoryType.CHEST;
	}

	@Override
	public int[] get_process_bar() {
		return null;
	}
}
