package com.expertmode.handlers;

import com.expertmode.enchantments.EnchantmentLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDeathHandler {
	
	@SubscribeEvent
	public static void livingDeathEvent(LivingDeathEvent event) {
		
		if(!event.getEntity().getLevel().isClientSide()) {
		
			ServerLevel server = event.getEntity().getLevel().getServer().getLevel(ServerLevel.OVERWORLD);
			
			if(ExpertModeFunctions.isSettingEnabled(server, "exploding_mobs")) {
				
				DamageSource source = event.getSource();
				LivingEntity entity = event.getEntity();
				
				if(!(entity instanceof Player)) {
					
					if(!source.isExplosion() && !source.isBypassInvul()) {
						
						Level level = entity.getLevel();
						Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(level, entity) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
						float explosionRadius = Mth.clamp((entity.getMaxHealth() / 2) - 1, 1, 9);
						level.explode(entity, entity.getX(), entity.getY(), entity.getZ(), explosionRadius, explosion$blockinteraction);
						
					}
					
				}
			
			}
			
			if(ExpertModeFunctions.isSettingEnabled(server, "curses")) {

				if(EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.CURSE_OF_DETONATION.get(), event.getEntity()) > 0) {
				
					Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(event.getEntity().getLevel(), event.getEntity()) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
					event.getEntity().getLevel().explode(null, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), 2.0F, explosion$blockinteraction);
				
				}
				
			}
		
		}
		
	}

}
