package com.expertmode.util;

import com.expertmode.items.ItemLoader;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Group extends CreativeModeTab {
	
	public static final CreativeModeTab EXPERTMODE_GROUP = (new Group(CreativeModeTab.getGroupCountSafe(), "expertmode"));
	
	public Group(int index, String label) {
		
		super(index, label);

	}

	@Override
	public ItemStack makeIcon() {

		return new ItemStack(ItemLoader.MILKBOTTLE.get());
		
	}

}
