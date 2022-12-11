package com.expertmode.enchantments;

import com.expertmode.Main;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentLoader {
	
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Main.MODID);

	/** REPLACEMENT ENCHANTMENTS **/
	private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	public static final RegistryObject<Enchantment> PROTECTION = ENCHANTMENTS.register("protection", () -> new ProtectionEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> BLAST_PROTECTION = ENCHANTMENTS.register("blast_protection", () -> new ProtectionEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> FIRE_PROTECTION = ENCHANTMENTS.register("fire_protection", () -> new ProtectionEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> PROJECTILE_PROTECTION = ENCHANTMENTS.register("projectile_protection", () -> new ProtectionEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> FEATHER_FALLING = ENCHANTMENTS.register("feather_falling", () -> new ProtectionEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{EquipmentSlot.FEET}));

	/** CURSES **/
	public static final RegistryObject<Enchantment> CURSE_OF_AVARICE = ENCHANTMENTS.register("curse_of_avarice", () -> new CurseEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> CURSE_OF_CHORUS = ENCHANTMENTS.register("curse_of_chorus", () -> new CurseEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> CURSE_OF_DARKNESS = ENCHANTMENTS.register("curse_of_darkness", () -> new CurseEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> CURSE_OF_DETONATION = ENCHANTMENTS.register("curse_of_detonation", () -> new CurseEnchantments(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
			
}
