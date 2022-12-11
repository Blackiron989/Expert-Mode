package com.expertmode.handlers;

import com.expertmode.potions.PotionLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PotionHandler {
	
	@SubscribeEvent
	public static void potionAddedEvent(MobEffectEvent.Added event) {
		
		if(event.getEntity().getLevel().isClientSide()) return;

		LivingEntity living = event.getEntity();
		
		if(living instanceof Player) {
			
			MobEffectInstance instance = event.getEffectInstance();
			
			if(instance.getEffect() == MobEffects.HEAL || instance.getEffect() == MobEffects.REGENERATION) {
				
				if(living.getEffect(PotionLoader.WOUNDED_POTION.get()) != null) {
					
					living.removeEffect(PotionLoader.WOUNDED_POTION.get());
					
				}
				
				if(living.getEffect(PotionLoader.BLEEDING_POTION.get()) != null) {
					
					living.removeEffect(PotionLoader.BLEEDING_POTION.get());
					
				}
				
			}
			
			/* Skips all code below if negative potion effect changes are disabled in the config */
			boolean modifyNegativePotions = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "modify_negative_potions");
			if(!modifyNegativePotions) return;

			int amplifier = 0;
			
			if(instance.getEffect() == MobEffects.BLINDNESS) {
				
				amplifier = instance.getAmplifier();
				
				if(living.getEffect(MobEffects.BLINDNESS) != null) {
					
					if(living.getEffect(MobEffects.BLINDNESS).getAmplifier() > amplifier) {
						
						amplifier = living.getEffect(MobEffects.BLINDNESS).getAmplifier();
						
					}
					
				}
				
				MobEffectInstance newInstance = new MobEffectInstance(MobEffects.BLINDNESS, 2147483647, amplifier);
				
				event.getEffectInstance().update(newInstance);
				
			}
			
			if(instance.getEffect() == MobEffects.HUNGER) {
				
				amplifier = instance.getAmplifier();
				
				if(living.getEffect(MobEffects.HUNGER) != null) {
					
					if(living.getEffect(MobEffects.HUNGER).getAmplifier() > amplifier) {
						
						amplifier = living.getEffect(MobEffects.HUNGER).getAmplifier();
						
					}
					
				}
				
				MobEffectInstance newInstance = new MobEffectInstance(MobEffects.HUNGER, 2147483647, amplifier);
				
				event.getEffectInstance().update(newInstance);
				
			}
			
			if(instance.getEffect() == MobEffects.POISON) {
				
				amplifier = instance.getAmplifier();
				
				if(living.getEffect(MobEffects.POISON) != null) {
					
					if(living.getEffect(MobEffects.POISON).getAmplifier() > amplifier) {
						
						amplifier = living.getEffect(MobEffects.POISON).getAmplifier();
						
					}
					
				}
				
				MobEffectInstance newInstance = new MobEffectInstance(MobEffects.POISON, 2147483647, amplifier);
				
				event.getEffectInstance().update(newInstance);
				
			}
			
			if(instance.getEffect() == PotionLoader.POISON_RESISTANCE_POTION.get()) {
				
				if(living.getEffect(MobEffects.POISON) != null) {
					
					living.removeEffect(MobEffects.POISON);
					
				}
				
			}
			
			if(instance.getEffect() == MobEffects.WEAKNESS) {
				
				amplifier = instance.getAmplifier();
				
				if(living.getEffect(MobEffects.WEAKNESS) != null) {
					
					if(living.getEffect(MobEffects.WEAKNESS).getAmplifier() > amplifier) {
						
						amplifier = living.getEffect(MobEffects.WEAKNESS).getAmplifier();
						
					}
					
				}
				
				MobEffectInstance newInstance = new MobEffectInstance(MobEffects.WEAKNESS, 2147483647, amplifier);
				
				event.getEffectInstance().update(newInstance);
				
			}
			
			if(instance.getEffect() == MobEffects.WITHER) {

				amplifier = instance.getAmplifier();
				
				if(living.getEffect(MobEffects.WITHER) != null) {
					
					if(living.getEffect(MobEffects.WITHER).getAmplifier() > amplifier) {
						
						amplifier = living.getEffect(MobEffects.WITHER).getAmplifier();
						
					}
					
				}
				
				MobEffectInstance newInstance = new MobEffectInstance(MobEffects.WITHER, 2147483647, amplifier);
				
				event.getEffectInstance().update(newInstance);
				
			}
			
			if(instance.getEffect() == PotionLoader.WITHER_RESISTANCE_POTION.get()) {
				
				if(living.getEffect(MobEffects.WITHER) != null) {
					
					living.removeEffect(MobEffects.WITHER);
					
				}
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public static void potionExpireEvent(MobEffectEvent.Expired event) {
		
		LivingEntity living = event.getEntity();
		
		if(living instanceof Player) {
			
			if(event.getEffectInstance().getEffect() == PotionLoader.INFECTION_POTION.get()) {
				
				double rand = Math.random();
				
				if(rand >= 0.55) {
					
					living.addEffect(new MobEffectInstance(MobEffects.HUNGER, 2400, 1));
					
				} else if(rand >= 0.1) {
					
					living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 2400, 1));
					
				} else {
					
					living.hurt(DamageSource.MAGIC, living.getMaxHealth());
					
				}
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public static void potionRemoveEvent(MobEffectEvent.Remove event) {

		if(event.getEntity().getLevel().isClientSide()) return;
		
		if(event.getEntity() instanceof Player) {
			
			ItemStack itemstack = event.getEntity().getUseItem();

			if(itemstack.getItem() != Items.MILK_BUCKET) return;

			boolean modifyMilk = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "modify_milk");
			if(modifyMilk == false) {
				
				if(event.getEffectInstance() != null) {
					
					if(event.getEffectInstance().getEffect().getCategory() == MobEffectCategory.HARMFUL) {
						
						event.setCanceled(true);
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
