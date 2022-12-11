package com.expertmode.handlers;

import java.util.Random;

import com.expertmode.enchantments.EnchantmentLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event.Result;

public class EntityItemPickupHandler {
	
	@SubscribeEvent
	public static void entityItemPickupEvent(EntityItemPickupEvent event) {
		
		if(!event.getEntity().getLevel().isClientSide()) {
			
			ServerLevel server = event.getEntity().getLevel().getServer().getLevel(ServerLevel.OVERWORLD);
			
			if(ExpertModeFunctions.isSettingEnabled(server, "curses")) {
				
				if((Player)event.getEntity() != null) {
					
					Player player = event.getEntity();
					
					int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.CURSE_OF_AVARICE.get(), player);
					if(level > 0) {
						
						Random random = new Random();
						Iterable<ItemStack> stack = player.getArmorSlots();
						if(event.getItem().getItem().is(net.minecraftforge.common.Tags.Items.INGOTS_GOLD)) {
							
							fixArmor(stack, 3, random);
							event.setResult(Result.ALLOW);
							
						} else if(event.getItem().getItem().is(Items.GOLD_BLOCK)) {
							
							fixArmor(stack, 27, random);
							event.setResult(Result.ALLOW);
							
						}

					}
					
				}
				
			}
			
		}
		
	}
	
	private static void fixArmor(Iterable<ItemStack> stack, int damageRange, Random random) {
		
		for(ItemStack itemstack : stack) {
			
			if(itemstack.isDamaged() && itemstack.getEnchantmentLevel(EnchantmentLoader.CURSE_OF_AVARICE.get()) > 0) {
				
				int damage = random.nextInt(damageRange) + 1;
				if(damage < itemstack.getDamageValue()) {
					
					itemstack.setDamageValue(itemstack.getDamageValue() - damage);
					
				} else {
					
					itemstack.setDamageValue(0);
					
				}
				
				break;
				
			}
			
		}
		
	}

}
