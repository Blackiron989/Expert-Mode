package com.expertmode.handlers;

import java.util.List;

import com.expertmode.potions.PotionLoader;
import com.expertmode.util.DamageSources;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingUpdateHandler {

	@SubscribeEvent
	public static void livingUpdate(LivingTickEvent event) {
		
		if(event.getEntity() instanceof Player) {
			
			Player player = (Player)event.getEntity();
			
			if(!player.getLevel().isClientSide()) {
				
				if(player.getLevel().getBlockState(player.blockPosition()).getBlock() == Blocks.STONECUTTER) {
					
					player.hurt(DamageSources.STONECUTTER, 1.0F);
					
				}
				
			}
			
		}
		
		if(event.getEntity() instanceof WitherBoss) {
			
			WitherBoss wither = (WitherBoss)event.getEntity();
			
			if(!wither.getLevel().isClientSide()) {
				
				if(ExpertModeFunctions.isSettingEnabled(wither.getServer().getLevel(ServerLevel.OVERWORLD), "harder_wither_boss")) {

					AABB aabb = new AABB(wither.blockPosition()).inflate(80).setMinY(wither.getLevel().getMinBuildHeight()).setMaxY(wither.getLevel().getMaxBuildHeight());
					List<Player> list = wither.getLevel().getEntitiesOfClass(Player.class, aabb);
					
					boolean hasEffect = wither.hasEffect(PotionLoader.SOUL_SLOW_POTION.get());
					if(list.size() > 0) {
						
						if(hasEffect) {
							
							for(Player player : list) {
								
								Inventory inventory = player.getInventory();
								ItemStack itemstack = inventory.getArmor(0);
								int level = itemstack.getEnchantmentLevel(Enchantments.SOUL_SPEED);
				
								if(wither.isPowered()) {
									
									switch(level) {
									
										case 3:
											//No slowdown
										break;
										
										case 2:
											player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
										break;
										
										default:
											player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1));
										break;
									
									}
									
								} else {
									
									if(level < 1) {
										
										player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
										
									}
									
								}
								
							}
							
						} else {

							wither.forceAddEffect(new MobEffectInstance(PotionLoader.SOUL_SLOW_POTION.get(), 200, 0), null);
							
						}
						
					} else {

						if(!hasEffect) {
							
							wither.discard();
							
						}
						
					}
					
					if(wither.isInWall()) {

						if(wither.getLevel().getBlockState(new BlockPos(wither.getEyePosition())) == Blocks.BEDROCK.defaultBlockState()) {
							
							wither.discard();
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}

}
