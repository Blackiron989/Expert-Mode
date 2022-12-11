package com.expertmode.potions;

import com.expertmode.Main;
import com.expertmode.items.ItemLoader;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
//import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionLoader {
	
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MODID);
	
	/* EFFECTS */
	public static final RegistryObject<MobEffect> INFECTION_POTION = EFFECTS.register("infectionpotion", () -> new PotionEffect(MobEffectCategory.HARMFUL, 0));
	public static final RegistryObject<MobEffect> BLEEDING_POTION = EFFECTS.register("bleedingpotion", () -> new BleedingEffect(MobEffectCategory.HARMFUL, 0));
	public static final RegistryObject<MobEffect> WOUNDED_POTION = EFFECTS.register("woundedpotion", () -> new WoundedEffect(MobEffectCategory.HARMFUL, 0));
	public static final RegistryObject<MobEffect> SOUL_SLOW_POTION = EFFECTS.register("soulslowpotion", () -> new PotionEffect(MobEffectCategory.HARMFUL, 0));
	
	/* RESISTANCE */
	public static final RegistryObject<MobEffect> WITHER_RESISTANCE_POTION = EFFECTS.register("witherresistance", () -> new PotionEffect(MobEffectCategory.BENEFICIAL, 0));
	public static final RegistryObject<MobEffect> POISON_RESISTANCE_POTION = EFFECTS.register("poisonresistance", () -> new PotionEffect(MobEffectCategory.BENEFICIAL, 0));
	
	public static class PotionCrafting {
		
		public static void load() {
			
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER), new ItemStack(Items.ICE), new ItemStack(ItemLoader.EXTINGUISHPOTION.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER), new ItemStack(Items.PACKED_ICE), new ItemStack(ItemLoader.EXTINGUISHPOTION.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER), new ItemStack(Items.BLUE_ICE), new ItemStack(ItemLoader.EXTINGUISHPOTION.get()));
			
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.PUMPKIN_PIE), new ItemStack(ItemLoader.HUNGERANTIDOTE.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.GOLDEN_APPLE), new ItemStack(ItemLoader.INFECTIONANTIDOTE.get()));
			//recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.ROTTEN_FLESH), new ItemStack(ItemLoader.INFECTIONPOTION.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.HONEY_BLOCK), new ItemStack(ItemLoader.POISONRESISTANCEPOTION.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.BLAZE_ROD), new ItemStack(ItemLoader.WEAKNESSANTIDOTE.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.CRIMSON_FUNGUS), new ItemStack(ItemLoader.WITHERANTIDOTE.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.WARPED_FUNGUS), new ItemStack(ItemLoader.WITHERANTIDOTE.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.WITHER_ROSE), new ItemStack(ItemLoader.WITHERRESISTANCEPOTION.get()));
			recipe(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), new ItemStack(Items.WITHER_SKELETON_SKULL), new ItemStack(ItemLoader.WITHERRESISTANCEPOTION.get()));
			
			recipe(new ItemStack(Items.GLASS_BOTTLE), new ItemStack(Items.MILK_BUCKET), new ItemStack(ItemLoader.MILKBOTTLE.get()));

		}

		private static void recipe(ItemStack input, ItemStack ingredient, ItemStack output) {
			
			BrewingRecipeRegistry.addRecipe(
					Ingredient.of(input),
					Ingredient.of(ingredient),
					output);
			
		}
		
	}

}
