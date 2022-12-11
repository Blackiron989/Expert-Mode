package com.expertmode.util;

import java.util.NavigableMap;
import java.util.TreeMap;

import com.expertmode.config.Config;

public class SettingMap {
	
	public enum Settings {
		
		MODIFY_NEGATIVE_POTIONS("modify_negative_potions", Config.Holder.modifyNegativePotions),
		MODIFY_BURN_TIME("modify_burn_time", Config.Holder.modifyBurnTime),
		MODIFY_EXPLOSION_DAMAGE("modify_explosion_damage", Config.Holder.modifyExplosionDamage),
		WOUNDED_EFFECT("wounded_effect", Config.Holder.woundedEffect),
		BLEEDING_EFFECT("bleeding_effect", Config.Holder.bleedingEffect),
		INFECTION_EFFECT("infection_effect", Config.Holder.infectionEffect),
		ZERO_DAMAGE_MODE("zero_damage_mode", Config.Holder.noDamageMode),
		MODIFY_PROTECTION_ENCHANTMENTS("modify_protection_enchantments", Config.Holder.modifyProtectionEnchantments),
		MODIFY_MILK("modify_milk", Config.Holder.enableMilk),
		HARDER_WITHER_BOSS("harder_wither_boss", Config.Holder.harderWitherBoss),
		EXPLODING_MOBS("exploding_mobs", Config.Holder.explodingMobs),
		NO_XP("no_xp", Config.Holder.noXP),
		HEALTH_REGENERATION("health_regeneration", Config.Holder.healthRegeneration),
		HARDER_NIGHTS("harder_nights", Config.Holder.harderNights),
		HOSTILE_CREATURES("hostile_creatures", Config.Holder.hostileCreatures),
		DANGEROUS_VILLAGES("dangerous_villages", Config.Holder.dangerousVillages),
		HEALTHIER_MONSTERS("healthier_monsters", Config.Holder.healthierMonsters),
		BUFFED_MONSTERS("buffed_monsters", Config.Holder.buffedMonsters),
		CHAMPION_MONSTERS("champion_monsters", Config.Holder.championMonsters),
		CURSES("curses", Config.Holder.curses),
		ENVIRONMENTAL_EFFECTS("environmental_effects", Config.Holder.environmentalEffects);
		
		private final String setting;
		private final Boolean state;
		
		private Settings(String string, Boolean bool) {
			
			this.setting = string;
			this.state = bool;
			
		}
		
		public String getSetting() {
			
			return setting;
			
		}
		
		public Boolean getBool() {
			
			return state;
			
		}
		
	}

	public static final NavigableMap<String, Boolean> map = new TreeMap<>();
	
	public static final NavigableMap<String, Boolean> settings() {
		
		map.put("modify_negative_potions", Config.Holder.modifyNegativePotions);
		map.put("modify_burn_time", Config.Holder.modifyBurnTime);
		map.put("modify_explosion_damage", Config.Holder.modifyExplosionDamage);
		map.put("wounded_effect", Config.Holder.woundedEffect);
		map.put("bleeding_effect", Config.Holder.bleedingEffect);
		map.put("infection_effect", Config.Holder.infectionEffect);
		map.put("zero_damage_mode", Config.Holder.noDamageMode);
		map.put("modify_protection_enchantments", Config.Holder.modifyProtectionEnchantments);
		map.put("modify_milk", Config.Holder.enableMilk);
		map.put("harder_wither_boss", Config.Holder.harderWitherBoss);
		map.put("exploding_mobs", Config.Holder.explodingMobs);
		map.put("no_xp", Config.Holder.noXP);
		map.put("health_regeneration", Config.Holder.healthRegeneration);
		
		//0.4
		map.put("harder_nights", Config.Holder.harderNights);
		map.put("hostile_creatures", Config.Holder.hostileCreatures);
		map.put("dangerous_villages", Config.Holder.dangerousVillages);
		
		map.put("healthier_monsters", Config.Holder.healthierMonsters);
		map.put("buffed_monsters", Config.Holder.buffedMonsters);
		map.put("champion_monsters", Config.Holder.championMonsters);

		map.put("curses", Config.Holder.curses);
		map.put("environmental_effects", Config.Holder.environmentalEffects);
		
		return map;
		
	};
	
}
