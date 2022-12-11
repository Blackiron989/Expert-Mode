package com.expertmode.potions;

import net.minecraft.world.effect.MobEffectCategory;

public class WoundedEffect extends PotionEffect {

	protected WoundedEffect(MobEffectCategory type, int liquidcolor) {
		
		super(type, liquidcolor);

	}
	
	@Override
	public boolean isDurationEffectTick(int a, int b) {
		
		if(this == PotionLoader.WOUNDED_POTION.get()) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

}
