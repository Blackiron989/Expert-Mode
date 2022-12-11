package com.expertmode.potions;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BleedingEffect extends PotionEffect {

	protected BleedingEffect(MobEffectCategory type, int liquidcolor) {
		
		super(type, liquidcolor);
	
	}
	
	@Override
	public void applyEffectTick(LivingEntity living, int p_76394_2_) {
		
		if(this == PotionLoader.BLEEDING_POTION.get()) {
			
			living.hurt(DamageSource.MAGIC, 1.0F);
			
		}
		
	}
	
	@Override
	public boolean isDurationEffectTick(int a, int b) {
		
		if(this == PotionLoader.BLEEDING_POTION.get()) {
			
			int j = 25 >> b;
			
			if (j > 0) {
				
				return a % j == 0;
				
			} else {
				
				return true;
				
			}
			
		} else {
			
			return false;
			
		}
		
	}

}
