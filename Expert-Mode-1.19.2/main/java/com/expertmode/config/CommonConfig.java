package com.expertmode.config;

import com.expertmode.Main;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	
	final ForgeConfigSpec.BooleanValue modifyNegativePotions;
	final ForgeConfigSpec.BooleanValue modifyBurnTime;
	final ForgeConfigSpec.BooleanValue modifyExplosionDamage;
	final ForgeConfigSpec.BooleanValue bleedingEffect;
	final ForgeConfigSpec.IntValue bleedingEffectChance;
	final ForgeConfigSpec.BooleanValue woundedEffect;
	final ForgeConfigSpec.IntValue woundedEffectChance;
	final ForgeConfigSpec.BooleanValue infectionEffect;
	final ForgeConfigSpec.BooleanValue ultraHard;
	final ForgeConfigSpec.BooleanValue noDamageMode;
	final ForgeConfigSpec.BooleanValue modifyProtectionEnchantments;
	final ForgeConfigSpec.BooleanValue enableMilk;
	final ForgeConfigSpec.BooleanValue harderWitherBoss;
	final ForgeConfigSpec.BooleanValue explodingMobs;
	final ForgeConfigSpec.BooleanValue noXP;
	final ForgeConfigSpec.BooleanValue healthRegeneration;
	
	//v0.4
	final ForgeConfigSpec.BooleanValue harderNights;
	final ForgeConfigSpec.BooleanValue hostileCreatures;
	final ForgeConfigSpec.BooleanValue dangerousVillages;
	
	final ForgeConfigSpec.BooleanValue healthierMonsters;
	final ForgeConfigSpec.IntValue healthierMonstersModifier;
	final ForgeConfigSpec.BooleanValue buffedMonsters;
	
	final ForgeConfigSpec.BooleanValue championMonsters;
	final ForgeConfigSpec.BooleanValue enchantingOverhaul;
	final ForgeConfigSpec.BooleanValue lootOverhaul;
	
	//v0.5
	final ForgeConfigSpec.BooleanValue curses;
	final ForgeConfigSpec.BooleanValue environmentalEffects;
	
	CommonConfig(final ForgeConfigSpec.Builder builder) {

		builder.push("GENERAL");
		
			ultraHard = builder
				.comment("Enable ultra hard mode?")
				.translation(Main.MODID + "config.ultraHard")
				.define("ultraHard", true);
			noXP = builder
					.comment("Disable XP orbs?")
					.translation(Main.MODID + "config.noXP")
					.define("noXP", false);
			harderNights = builder
					.comment("Monsters are tougher at night?")
					.translation(Main.MODID + "config.harderNights")
					.define("harderNights", true);
			healthRegeneration = builder
					.comment("Should the mod auto set natural health regeneration gamerule?")
					.translation(Main.MODID + "config.healthRegeneration")
					.define("healthRegeneration", false);
			championMonsters = builder
					.comment("(WIP) Do special variants of monsters spawn?")
					.translation(Main.MODID + "config.championMonsters")
					.define("championMonsters", false);
			enchantingOverhaul = builder
					.comment("(WIP) Is the vanilla enchanting system replaced with a custom system?")
					.translation(Main.MODID + "config.enchantingOverhaul")
					.define("enchantingOverhaul", false);
			lootOverhaul = builder
					.comment("(WIP) Is the vanilla loot system replaced with a custom system?")
					.translation(Main.MODID + "config.lootOverhaul")
					.define("lootOverhaul", false);
			curses = builder
					.comment("Do the new curses have any effect?")
					.translation(Main.MODID + "config.curses")
					.define("curses", true);
		
		builder.pop();
		
		builder.push("TWEAKS");
		
			modifyBurnTime = builder
				.comment("Should burning last indefinitely?")
				.translation(Main.MODID + "config.modifyBurnTime")
				.define("modifyBurnTime", true);
			modifyProtectionEnchantments = builder
				.comment("Change armor protection enchantments?")
				.translation(Main.MODID + "config.modifyProtectionEnchantments")
				.define("modifyProtectionEnchantments", true);
			modifyExplosionDamage = builder
				.comment("Increase explosion damage?")
				.translation(Main.MODID + "config.modifyExplosionDamage")
				.define("modifyExplosionDamage", true);
			harderWitherBoss = builder
					.comment("Modify the wither boss?")
					.translation(Main.MODID + "config.harderWitherBoss")
					.define("harderWitherBoss", true);
			dangerousVillages = builder
					.comment("(WIP) Looting villages is riskier?")
					.translation(Main.MODID + "config.dangerousVillages")
					.define("dangerousVillages", false);
			healthierMonsters = builder
					.comment("Monsters have a health multiplier applied?")
					.translation(Main.MODID + "config.healthierMonsters")
					.define("healthierMonsters", true);
			healthierMonstersModifier = builder
					.comment("Monster health multiplier (Up To 2x Modifier).")
					.translation(Main.MODID + "config.healthierMonstersModifier")
					.defineInRange("healthierMonstersModifier", 25, 0, 100);
			buffedMonsters = builder
					.comment("Monsters have extra abilities and stronger features?")
					.translation(Main.MODID + "config.buffedMonsters")
					.define("buffedMonsters", true);
			environmentalEffects = builder
					.comment("Environmental dangers are more deadly?")
					.translation(Main.MODID + "config.environmentalEffects")
					.define("environmentalEffects", true);
		
		builder.pop();
		
		builder.push("EFFECTS");
		
			enableMilk = builder
				.comment("Re-enable milk buckets?")
				.translation(Main.MODID + "config.enableMilk")
				.define("enableMilk", false);
			modifyNegativePotions = builder
				.comment("Should negative potion effects last indefinitely?")
				.translation(Main.MODID + "config.modifyNegativePotions")
				.define("modifyNegativePotions", true);
			bleedingEffect = builder
				.comment("Enable bleeding effect?")
				.translation(Main.MODID + "config.bleedingEffect")
				.define("bleedingEffect", true);
			bleedingEffectChance = builder
				.comment("Bleeding effect % chance to occur.")
				.translation(Main.MODID + "config.bleedingEffectChance")
				.defineInRange("bleedingEffectChance", 10, 0, 100);
			woundedEffect = builder
				.comment("Enable wounded effect?")
				.translation(Main.MODID + "config.woundedEffect")
				.define("woundedEffect", true);
			woundedEffectChance = builder
				.comment("Wounded effect % chance to occur.")
				.translation(Main.MODID + "config.woundedEffectChance")
				.defineInRange("woundedEffectChance", 10, 0, 100);
			infectionEffect = builder
				.comment("Enable infection effect?")
				.translation(Main.MODID + "config.infectionEffect")
				.define("infectionEffect", true);
		
		builder.pop();
		
		builder.push("BONUS_FEATURES");

			noDamageMode = builder
				.comment("Enable instant death? (Any damage kills the player)")
				.translation(Main.MODID + "config.noDamageMode")
				.define("noDamageMode", false);
			explodingMobs = builder
					.comment("Mobs explode?")
					.translation(Main.MODID + "config.explodingMobs")
					.define("explodingMobs", false);
			hostileCreatures = builder
					.comment("(WIP) Make all docile creatures immediately hostile?")
					.translation(Main.MODID + "config.hostileCreatures")
					.define("hostileCreatures", false);
		
		builder.pop();
		
	}
	
}
