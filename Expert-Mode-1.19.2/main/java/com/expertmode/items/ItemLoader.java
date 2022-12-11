package com.expertmode.items;

import com.expertmode.Main;
import com.expertmode.util.Group;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemLoader {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

	public static final RegistryObject<Item> EXTINGUISHPOTION = ITEMS.register("extinguishpotion", () -> new ExtinguishPotionItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16)));
	public static final RegistryObject<Item> HUNGERANTIDOTE = ITEMS.register("hungerantidote", () -> new AntidoteItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16), MobEffects.HUNGER));
	public static final RegistryObject<Item> INFECTIONANTIDOTE = ITEMS.register("infectionantidote", () -> new AntidoteItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16), MobEffects.UNLUCK));
	//public static final RegistryObject<Item> POISONANTIDOTE = ITEMS.register("poisonantidote", () -> new AntidoteItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16), Effects.POISON));
	public static final RegistryObject<Item> WEAKNESSANTIDOTE = ITEMS.register("weaknessantidote", () -> new AntidoteItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16), MobEffects.WEAKNESS));
	public static final RegistryObject<Item> WITHERANTIDOTE = ITEMS.register("witherantidote", () -> new AntidoteItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16), MobEffects.WITHER));
	
	public static final RegistryObject<Item> WITHERRESISTANCEPOTION = ITEMS.register("witherresistancepotion", () -> new ResistanceItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16), MobEffects.WITHER, 2400, 0));
	public static final RegistryObject<Item> POISONRESISTANCEPOTION = ITEMS.register("poisonresistancepotion", () -> new ResistanceItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16), MobEffects.POISON, 2400, 0));
	
	public static final RegistryObject<Item> MILKBOTTLE = ITEMS.register("milkbottle", () -> new MilkBottleItem(new Properties().tab(Group.EXPERTMODE_GROUP).stacksTo(16)));

}
