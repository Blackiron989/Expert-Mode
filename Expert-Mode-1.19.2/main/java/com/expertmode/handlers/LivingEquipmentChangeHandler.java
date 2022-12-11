package com.expertmode.handlers;

import com.expertmode.enchantments.EnchantmentLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingEquipmentChangeHandler {

	/*
	 *  NOTE:
	 *  
	 *  	Potentially will need to add a check for armor in hands (and dropped) to remove the custom enchantment
	 *  	in case the player would like to place the armor on a non-player entity that protection should work on.
	 *  	As well if dropped on death and picked up by a zombie (or other mob) they will not be able to take advantage
	 *   	of the custom enchantment so they would need the enchantment changed back to default. This could be done
	 *   	by either adding this same function to mob equips just in one direction or before it is dropped from the
	 *   	player.
	 */
	
	@SubscribeEvent
	public static void livingEquipmentChangeEvent(LivingEquipmentChangeEvent event) {
		
		if(event.getEntity().getLevel().isClientSide()) return;
		
		if(event.getSlot() == EquipmentSlot.MAINHAND || event.getSlot() == EquipmentSlot.OFFHAND) return;
		
		if(!event.getTo().isEnchanted()) return;
		
		if(!(event.getEntity() instanceof Player)) return;
		
		boolean modifyProtectionEnchantments = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "modify_protection_enchantments");
		if(modifyProtectionEnchantments) {			
				
			for(int i = 0; i < event.getTo().getEnchantmentTags().size(); i++) {
				
				CompoundTag nbt = event.getTo().getEnchantmentTags().getCompound(i);
				
				if(nbt.get("id").toString().contains("minecraft:protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(EnchantmentLoader.PROTECTION.get(), nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("minecraft:blast_protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(EnchantmentLoader.BLAST_PROTECTION.get(), nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("minecraft:fire_protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(EnchantmentLoader.FIRE_PROTECTION.get(), nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("minecraft:projectile_protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(EnchantmentLoader.PROJECTILE_PROTECTION.get(), nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("minecraft:feather_falling")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(EnchantmentLoader.FEATHER_FALLING.get(), nbt.getInt("lvl"));
					
				}
				
			}
			
		} else {
			
			for(int i = 0; i < event.getTo().getEnchantmentTags().size(); i++) {
				
				CompoundTag nbt = event.getTo().getEnchantmentTags().getCompound(i);
				
				if(nbt.get("id").toString().contains("expertmode:protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(Enchantments.ALL_DAMAGE_PROTECTION, nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("expertmode:blast_protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(Enchantments.BLAST_PROTECTION, nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("expertmode:fire_protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(Enchantments.FIRE_PROTECTION, nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("expertmode:projectile_protection")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(Enchantments.PROJECTILE_PROTECTION, nbt.getInt("lvl"));
					
				} else if(nbt.get("id").toString().contains("expertmode:feather_falling")) {
					
					event.getTo().getEnchantmentTags().remove(i);
					event.getTo().enchant(Enchantments.FALL_PROTECTION, nbt.getInt("lvl"));
					
				}
				
			}
			
		}
		
	}

}
