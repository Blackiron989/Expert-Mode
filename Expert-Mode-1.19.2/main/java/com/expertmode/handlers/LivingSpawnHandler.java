package com.expertmode.handlers;

import java.util.UUID;

import com.expertmode.config.Config;
import com.expertmode.potions.PotionLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingSpawnHandler {
	
	private static final int modifier = Config.Holder.healthierMonstersModifier;
	
	@SubscribeEvent
	public static void livingSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
		
		if(event.getLevel().isClientSide()) return;
		
		ServerLevel server = event.getLevel().getServer().getLevel(ServerLevel.OVERWORLD);
		
		if(ExpertModeFunctions.isSettingEnabled(server, "harder_wither_boss")) {
		
			if(event.getEntity() instanceof WitherBoss) {
				
				event.getEntity().forceAddEffect(new MobEffectInstance(PotionLoader.SOUL_SLOW_POTION.get(), 200, 0), null);
				
			}
		
		}
		
		if(ExpertModeFunctions.isSettingEnabled(server, "healthier_monsters")) {
			
			if(modifier > 0) {

				double multiplier = 1 + (modifier / 100.0);
				if(event.getEntity() instanceof Monster) {
					
					Monster monster = ((Monster)event.getEntity());
					
					double health = monster.getMaxHealth();
					double add = (health * multiplier) - health;
					
					AttributeModifier attribute = new AttributeModifier(UUID.fromString("045d92d6-7c26-4ffb-bfbc-e2b0857d5d89"), "expertmode_maxhealth", add, AttributeModifier.Operation.ADDITION);
					if(!monster.getAttribute(Attributes.MAX_HEALTH).hasModifier(attribute)) {
						
						monster.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(attribute);
					
					}
					
				}
				
			}
			
		}
		
		if(ExpertModeFunctions.isSettingEnabled(server, "harder_nights")) {

			if(event.getLevel().dayTime() > 12999) {
				
				if(event.getEntity() instanceof Monster) {
					
					Monster monster = ((Monster)event.getEntity());
					
					int timeRemaining = 24000 - ((int)event.getLevel().dayTime());
					
					if(monster instanceof Creeper) {
						
						monster.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, timeRemaining, 0));
						
					} else {
						
						monster.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, timeRemaining, 0));
						
					}
					
					monster.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, timeRemaining, 0));

				}
				
			}
			
		}
		
	}

}
