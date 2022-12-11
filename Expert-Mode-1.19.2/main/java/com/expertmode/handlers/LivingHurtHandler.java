package com.expertmode.handlers;

import com.expertmode.enchantments.EnchantmentLoader;
import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingHurtHandler {
	
	@SubscribeEvent
	public static void livingHurtEvent(LivingHurtEvent event) {
		
		if(event.getEntity().getLevel().isClientSide()) return;
		
		LivingEntity living = event.getEntity();
		ServerLevel server = living.getServer().getLevel(ServerLevel.OVERWORLD);
		
		if(living instanceof Player) {

			boolean modifyExplosionDamage = ExpertModeFunctions.isSettingEnabled(server, "modify_explosion_damage");
			if(modifyExplosionDamage) {
			
				modifyExplosionDamage(event);
			
			}

			boolean modifyProtectionEnchantments = ExpertModeFunctions.isSettingEnabled(server, "modify_protection_enchantments");
			if(modifyProtectionEnchantments) {
			
				DamageSource source = event.getSource();
				String str = sourceToProtectionId(source);
				int protectionLevel = getProtectionLevel(living.getArmorSlots(), source);
				
				float adjustedAmount = applyProtection(event.getAmount(), protectionLevel, str);
				
				if(str.contains("blast_protection")) {
				
					float additionalAdjustment = 0;

					int coverPercent = 0;
					for(ItemStack itemstack : living.getArmorSlots()) {
						
						if(itemstack.getEnchantmentLevel(EnchantmentLoader.BLAST_PROTECTION.get()) > 0) {
							
							coverPercent += 1;
							
						}
						
					}
					
					for(ItemStack itemstack : living.getArmorSlots()) {
						
						int enchantmentLevel = itemstack.getEnchantmentLevel(EnchantmentLoader.BLAST_PROTECTION.get());
						if(enchantmentLevel > 0) {
							
							if(itemstack.isDamageableItem()) {

								float newDamage = (adjustedAmount/coverPercent)/(enchantmentLevel*0.125F);

								if(newDamage > 15.0F) {
									
									if(newDamage < itemstack.getMaxDamage()) {
										
										itemstack.setDamageValue(itemstack.getDamageValue() + ((int)newDamage - 5));
										
									} else {
										
										additionalAdjustment += Math.abs(itemstack.getDamageValue() + ((int)newDamage - 5));
										itemstack.setDamageValue(itemstack.getMaxDamage());
										
									}
									
								} else {
									
									additionalAdjustment += newDamage;
									
								}
								
							}
							
						}
						
					}
					
					if(additionalAdjustment > 0) {
						
						adjustedAmount = additionalAdjustment;
						
					}
					
				} else if(adjustedAmount != event.getAmount()) {
					
					degradeArmor(living.getArmorSlots(), str);

				}
				
				event.setAmount(adjustedAmount);
				
			}
			
			boolean buffedMonsters = ExpertModeFunctions.isSettingEnabled(server, "buffed_monsters");
			if(buffedMonsters) {
				
				ItemStack itemstack = ((Player)living).getInventory().getArmor(3);
				if(itemstack.getEnchantmentLevel(Enchantments.RESPIRATION) == 0) {
				
					if(event.getSource().getDirectEntity() instanceof LivingEntity) {
					
						LivingEntity entity = (LivingEntity) event.getSource().getDirectEntity();
						if(entity != null && living.getAirSupply() > 0) {
							
							if(entity instanceof Guardian) {
	
								int air = (int) Math.floor(living.getAirSupply() * 0.9);
								((Player)living).setAirSupply(air);
								
							} else if(entity instanceof ElderGuardian) {
								
								int air = Mth.clamp(living.getAirSupply() - 40, -20, 300);
								((Player)living).setAirSupply(air);
								
							}
							
						}
					
					}
					
				}
				
			}
			
		}
		
	}
	
	private static void degradeArmor(Iterable<ItemStack> iterable, String string) {
		
		if(string.isEmpty()) return;
		
		if(string.contains("blast_protection") || string.contains("fire_protection") || string.contains("projectile_protection")) {
			
			for(ItemStack itemstack : iterable) {
				
				for(int i = 0; i < itemstack.getEnchantmentTags().size(); i++) {
					
					CompoundTag compoundnbt = itemstack.getEnchantmentTags().getCompound(i);
					
					if(compoundnbt.get("id").toString().contains("expertmode:" + string)) {

						if(itemstack.isDamageableItem()) {
							
							int rand = (int)Math.floor(Math.random() * 3.0D);
							itemstack.setDamageValue(itemstack.getDamageValue() + rand);
							
						}
						
					}
					
				}
				
			}
		
		}
		
	}
	
	private static float applyProtection(float amount, float level, String string) {

		if(string.contains("fire_protection") || string.contains("projectile_protection")) {
			return amount * (1F - Mth.clamp((float)(level / 20), 0F, 0.8F));
		} else if(string.contains("feather_falling")) {
			return amount * (1F - Mth.clamp((float)(level / 10), 0F, 0.4F));
		} else if(string.contains("protection")) {
			return amount * (1F - Mth.clamp((float)(level / 32), 0F, 0.5F));
		} else {
			return amount;
		}
		
	}
	
	private static int getProtectionLevel(Iterable<ItemStack> iterable, DamageSource source) {
		
		int level = 0;
		
		String str = sourceToProtectionId(source);
		
		if(str.isEmpty()) return level;
		
		for(ItemStack itemstack : iterable) {
			
			for(int i = 0; i < itemstack.getEnchantmentTags().size(); i++) {
				
				CompoundTag compoundnbt = itemstack.getEnchantmentTags().getCompound(i);
				
				if(compoundnbt.get("id").toString().contains("expertmode:" + str)) {
					
					level += compoundnbt.getInt("lvl");
					
				}
				
			}
			
		}

		return Mth.clamp(level, 0, 16);
		
	}
	
	private static String sourceToProtectionId(DamageSource source) {

		if(source.isBypassInvul()) {
			return "";
		} else if(source.isExplosion()) {
			return "blast_protection";
		} else if(source.isFire() || source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE || source == DamageSource.LAVA) {
			return "fire_protection";
		} else if(source.isProjectile()) {
			return "projectile_damage";
		} else if(source == DamageSource.FALL) {
			return "feather_falling";
		} else if(source != DamageSource.OUT_OF_WORLD) {
			return "protection";
		}
		
		return "";
		
	}
	
	private static void modifyExplosionDamage(LivingHurtEvent event) {
		
		boolean modifyExplosionDamage = ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "modify_explosion_damage");
		if(!modifyExplosionDamage) return;
		
		LivingEntity living = event.getEntity();
		
		if(living instanceof Player) {
			
			DamageSource source = event.getSource();
			
			if(source.isExplosion()) {
				
				Vec3 vector = source.getSourcePosition();
				
				if(vector != null) {
					
					double distanceTo = vector.distanceTo(living.position());
					
					double adjustedAmount = ((double)event.getAmount()) * Math.min((1.0D + 2.0D/distanceTo), 2.0D);
					event.setAmount((float) adjustedAmount);
					
				} else {

					double adjustedAmount = ((double)event.getAmount()) * (1.0D + Math.min(Math.random(), 0.5D));
					event.setAmount((float) adjustedAmount);
					
				}
				
			}
			
		}
		
	}

}
