package com.expertmode.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CurseEnchantments extends Enchantment {

	protected CurseEnchantments(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slot) {
		
		super(rarity, category, slot);

	}
	
	/*@Override
	public int getMinCost(int i) {
		
		return 25;
		
	}
	
	@Override
	public int getMaxCost(int i) {
		
		return 50;
		
	}*/
	
	@Override
	public int getMaxLevel() {
		
		return 1;
		
	}
	
	@Override
	public boolean isTreasureOnly() {
		
		return true;
		
	}
	
	@Override
	public boolean isCurse() {
		
		return true;
		
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		
		return false;
		
	}

}
