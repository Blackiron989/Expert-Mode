package com.expertmode.enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class ProtectionEnchantments extends Enchantment {

	protected ProtectionEnchantments(Rarity rarity, EnchantmentCategory type, EquipmentSlot[] slot) {
		
		super(rarity, type, slot);

	}
	
	@Override
	public int getMaxLevel() {
		
		return 4;
		
	}

}
