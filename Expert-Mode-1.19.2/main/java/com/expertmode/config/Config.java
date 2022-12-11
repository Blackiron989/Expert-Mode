package com.expertmode.config;

import java.nio.file.Path;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.expertmode.Main;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

	public static final ForgeConfigSpec COMMON_SPEC;

	static final CommonConfig COMMON;

	static {
		
		{
			
			final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
			COMMON = specPair.getLeft();
			COMMON_SPEC = specPair.getRight();
			
		}
		
	}
	
	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		
		final CommentedFileConfig configdata = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();

		configdata.load();
		spec.setConfig(configdata);
		
	}
	
	public static final class Holder {
		
		public static boolean modifyNegativePotions;
		public static boolean modifyBurnTime;
		public static boolean modifyExplosionDamage;
		public static boolean woundedEffect;
		public static int woundedEffectChance;
		public static boolean bleedingEffect;
		public static int bleedingEffectChance;
		public static boolean infectionEffect;
		public static boolean ultraHard;
		public static boolean noDamageMode;
		public static boolean modifyProtectionEnchantments;
		public static boolean enableMilk;
		public static boolean harderWitherBoss;
		public static boolean explodingMobs;
		public static boolean noXP;
		public static boolean healthRegeneration;
		
		//v0.4
		public static boolean harderNights;
		public static boolean hostileCreatures;
		public static boolean dangerousVillages;
		
		public static boolean healthierMonsters;
		public static int healthierMonstersModifier;
		public static boolean buffedMonsters;
		
		public static boolean championMonsters;
		public static boolean enchantingOverhaul;
		public static boolean lootOverhaul;

		public static boolean curses;
		public static boolean environmentalEffects;
		
		public static void load() {
			
			//Fields
			modifyNegativePotions = COMMON.modifyNegativePotions.get();
			modifyBurnTime = COMMON.modifyBurnTime.get();
			modifyExplosionDamage = COMMON.modifyExplosionDamage.get();
			woundedEffect = COMMON.woundedEffect.get();
			woundedEffectChance = COMMON.woundedEffectChance.get();
			bleedingEffect = COMMON.bleedingEffect.get();
			bleedingEffectChance = COMMON.bleedingEffectChance.get();
			infectionEffect = COMMON.infectionEffect.get();
			ultraHard = COMMON.ultraHard.get();
			noDamageMode = COMMON.noDamageMode.get();
			modifyProtectionEnchantments = COMMON.modifyProtectionEnchantments.get();
			enableMilk = COMMON.enableMilk.get();
			harderWitherBoss = COMMON.harderWitherBoss.get();
			explodingMobs = COMMON.explodingMobs.get();
			noXP = COMMON.noXP.get();
			healthRegeneration = COMMON.healthRegeneration.get();
			
			//v0.4
			harderNights = COMMON.harderNights.get();
			hostileCreatures = COMMON.hostileCreatures.get();
			dangerousVillages = COMMON.dangerousVillages.get();
			
			healthierMonsters = COMMON.healthierMonsters.get();
			healthierMonstersModifier = COMMON.healthierMonstersModifier.get();
			buffedMonsters = COMMON.buffedMonsters.get();
			
			championMonsters = COMMON.championMonsters.get();
			enchantingOverhaul = COMMON.enchantingOverhaul.get();
			lootOverhaul = COMMON.lootOverhaul.get();

			curses = COMMON.curses.get();
			environmentalEffects = COMMON.environmentalEffects.get();
			
			//Logs
			logger("Do negative potion effects last indefinitely", modifyNegativePotions);
			logger("Does burning last indefinitely", modifyBurnTime);
			logger("Is the wounded effect enabled", woundedEffect);
			logger("Chance for wounded effect to occur", woundedEffectChance);
			logger("Is the bleeding effect enabled", bleedingEffect);
			logger("Chance for bleeding effect to occur", bleedingEffectChance);
			logger("Is the infection effect enabled", infectionEffect);
			logger("Is ultra hard enabled", ultraHard);
			logger("Is no damage mode enabled", noDamageMode);
			logger("Are protection enchantments modified", modifyProtectionEnchantments);
			logger("Is milk enabled", enableMilk);
			logger("Is wither boss harder", harderWitherBoss);
			logger("Do mobs explode", explodingMobs);
			logger("Do XP orbs spawn", noXP);
			logger("Is Gamerule natural health regeneration set by mod", healthRegeneration);
			
			//v0.4
			logger("Are nights harder", harderNights);
			logger("Are docile creatures set hostile", hostileCreatures);
			logger("Are villages more dangerous", dangerousVillages);
			
			logger("Do monsters have a health modifier", healthierMonsters);
			logger("Multiplier for monster health", healthierMonstersModifier);
			logger("Do monsters have extra abilities and stronger features", buffedMonsters);
			
			logger("Do special champion variants of monsters spawn", championMonsters);
			logger("Is the vanilla enchanting system replaced", enchantingOverhaul);
			logger("Is the vanilla loot system replaced", lootOverhaul);

			logger("Are the new curse enchantments active", curses);
			logger("Are more dangerous environmental effects active", environmentalEffects);
			
		}
		
		private static void logger(String log, Object field) {
			
			Main.LOGGER.info("Expert Mode : " + log + " : " + field);
			
		}
		
	}
	
}
