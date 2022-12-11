package com.expertmode.potions;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class WitherResistanceEffect extends PotionEffect {

	protected WitherResistanceEffect(MobEffectCategory type, int liquidcolor) {
		
		super(type, liquidcolor);

	}
	
	@Override
	public void applyEffectTick(LivingEntity living, int p_76394_2_) {

		if(this == PotionLoader.WITHER_RESISTANCE_POTION.get()) {
			
			if(living.getEffect(MobEffects.WITHER) != null) {
				
				living.removeEffect(MobEffects.WITHER);
				
			}
			
		}
		
	}
	
	@Override
	public boolean isDurationEffectTick(int a, int b) {
		
		if(this == PotionLoader.WITHER_RESISTANCE_POTION.get()) {
			
			return true;

		} else {
			
			return false;
			
		}
		
	}

}
