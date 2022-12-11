package com.expertmode.handlers;

import com.expertmode.potions.PotionLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HealingHandler {
	
	@SubscribeEvent
	public static void healEvent(LivingHealEvent event) {
		
		if(event.getEntity().getLevel().isClientSide()) return;
		
		LivingEntity living = event.getEntity();
		
		if(living instanceof Player) {
			
			boolean woundedEffect = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "wounded_effect");
			if(woundedEffect) {
				
				if(living.getEffect(PotionLoader.WOUNDED_POTION.get()) != null) {
					
					event.setCanceled(true);
					
				}
				
			}	
			
		}
		
	}

}
