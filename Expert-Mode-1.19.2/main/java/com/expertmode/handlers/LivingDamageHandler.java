package com.expertmode.handlers;

import com.expertmode.config.Config;
import com.expertmode.enchantments.EnchantmentLoader;
import com.expertmode.potions.PotionLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDamageHandler {
	
	@SubscribeEvent
	public static void livingDamageEvent(LivingDamageEvent event) {
		
		if(!event.getEntity().getLevel().isClientSide()) {

			LivingEntity living = event.getEntity();
			ServerLevel server = event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD);

			applyEffects(event);
			
			if(ExpertModeFunctions.isSettingEnabled(server, "zero_damage_mode")) {
				
				if(living instanceof Player) {
					
					if(event.getAmount() > 0) {
					
						event.setAmount(living.getMaxHealth());
					
					}
					
				}
				
			}
			
			burnTime(event);
			
			if(ExpertModeFunctions.isSettingEnabled(server, "curses")) {
				
				curses(event);
				
			}
			
			if(ExpertModeFunctions.isSettingEnabled(server, "environmental_effects")) {
				
				environmentalEffects(event);
				
			}
			
		}
		
	}
	
	private static final int bleedingEffectChance = Config.Holder.bleedingEffectChance;
	private static final int woundedEffectChance = Config.Holder.woundedEffectChance;
	
	private static void applyEffects(LivingDamageEvent event) {
		
		if(!(event.getEntity() instanceof Player)) return;
		
		DamageSource source = event.getSource();

		Player player = (Player) event.getEntity();
		
		boolean bleedingEffect = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "bleeding_effect");
		if(bleedingEffect && bleedingEffectChance > 0) {

			if(source.isProjectile() || source == DamageSource.CACTUS || source == DamageSource.SWEET_BERRY_BUSH) {
				
				double chance = 1D - ((double)bleedingEffectChance/100D);
				if(Math.random() > chance) {
					
					player.addEffect(new MobEffectInstance(PotionLoader.BLEEDING_POTION.get(), 120, 0));
					
				}
				
			}
			
		}
		
		boolean woundedEffect = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "wounded_effect");
		if(woundedEffect && woundedEffectChance > 0) {
			
			if(source.isExplosion() || source == DamageSource.FALL || source == DamageSource.FLY_INTO_WALL || source == DamageSource.FALLING_BLOCK) {
				
				double chance = 1D - ((double)woundedEffectChance/100D);
				if(Math.random() > chance) {
					
					player.addEffect(new MobEffectInstance(PotionLoader.WOUNDED_POTION.get(), 2400, 0));
					
				}
				
			}
			
		}
		
		boolean infectionEffect = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "infection_effect");
		if(infectionEffect) {
			
			if(source.getEntity() instanceof Zombie) {
				
				double chance = 0;
				if(player.getHealth() < (player.getMaxHealth()/2)) chance += 0.1;
				
				double rand = Math.random() + chance;
				if(rand > 0.8) {
					
					player.addEffect(new MobEffectInstance(PotionLoader.INFECTION_POTION.get(), 6000, 0));
					
				}
				
			}
			
		}
		
	}
	
	private static void burnTime(LivingDamageEvent event) {
		
		boolean modifyBurnTime = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "modify_burn_time");
		if(!modifyBurnTime) return;
		
		LivingEntity living = event.getEntity();
		
		if(living instanceof Player) {
			
			if(!living.fireImmune()) {
				
				if(event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.LAVA || event.getSource().isFire()) {
				
					living.setRemainingFireTicks(1000);
				
				}
			
			}
			
		}
		
	}
	
	private static void curses(LivingDamageEvent event) {
		
		LivingEntity living = event.getEntity();
		
		if(event.getAmount() > 0.5F) {
			
			if(EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.CURSE_OF_DARKNESS.get(), event.getEntity()) > 0) {
				
				if(Math.random() > 0.70D) {
					
					if(living instanceof Player) {
						
						living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 300, 0));
						
					}
					
				}
				
			}
			
			if(EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.CURSE_OF_CHORUS.get(), event.getEntity()) > 0) {
				
				if(Math.random() > 0.80D) {
					
					if(living instanceof Player) {
						
						double d0 = living.getX();
				        double d1 = living.getY();
				        double d2 = living.getZ();

				        for(int i = 0; i < 16; ++i) {
				        	
				        	double d3 = living.getX() + (living.getRandom().nextDouble() - 0.5D) * 16.0D;
				            double d4 = Mth.clamp(living.getY() + (double)(living.getRandom().nextInt(16) - 8), (double)living.getLevel().getMinBuildHeight(), (double)(living.getLevel().getMinBuildHeight() + ((ServerLevel)living.getLevel()).getLogicalHeight() - 1));
				            double d5 = living.getZ() + (living.getRandom().nextDouble() - 0.5D) * 16.0D;
				            if (living.isPassenger()) {
				            	
				            	living.stopRiding();
				            	
				            }

				            net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit chorus = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(living, d3, d4, d5);
				            if (event.isCanceled()) return;
				            if (living.randomTeleport(chorus.getTargetX(), chorus.getTargetY(), chorus.getTargetZ(), true)) {

				            	living.getLevel().playSound((Player)null, d0, d1, d2, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
				            	living.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
				                break;
				                
				            }
				            
				        }
						
					}
					
				}
				
			}
		
		}
		
	}
	
	private static void environmentalEffects(LivingDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {
			
			Player player = (Player)event.getEntity();
			
			if(event.getSource() == DamageSource.DROWN) {
				
				event.setAmount(event.getAmount() * 2);
				
			} else if(event.getSource() == DamageSource.FREEZE) {
				
				event.setAmount(event.getAmount() * 2);
			
				player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
				
				if(player.getHealth() < (player.getMaxHealth() * 0.75F)) {
					
					player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
					
				}
				
				if(player.getHealth() < (player.getMaxHealth() / 2.0F)) {
					
					player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
					
				}
				
			} else if(event.getSource() == DamageSource.IN_WALL) {
				
				event.setAmount(event.getAmount() * 2);
				
			}
		
		}
		
	}

}
