package com.expertmode.potions;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import javax.annotation.Nullable;

public class PotionEffect extends MobEffect {

	protected PotionEffect(MobEffectCategory type, int liquidcolor) {
		
		super(type, liquidcolor);

	}
	
	@Override
	public void applyEffectTick(LivingEntity living, int p_76394_2_) {
	}
	
	@Override
	public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectsource, LivingEntity livingentity, int amplifier, double health) { //Instance Effects
	}
	
	@Override
	public boolean isDurationEffectTick(int a, int b) {
			
			return true;
		
	}

}
