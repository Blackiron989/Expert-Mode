package com.expertmode.loot;

import java.util.Random;
import java.util.function.Supplier;

import com.expertmode.enchantments.EnchantmentLoader;
import com.expertmode.util.ExpertModeFunctions;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class NetherBridgeModifier extends LootModifier {
	
	public static final Supplier<Codec<NetherBridgeModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
			.apply(inst, NetherBridgeModifier::new)));

	public NetherBridgeModifier(final LootItemCondition[] conditionsIn) {
		
		super(conditionsIn);
		
	}
	
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		
		boolean setting = ExpertModeFunctions.isSettingEnabled(context.getLevel(), "curses");
		if(setting) {

			Random random = new Random();
			if(random.nextInt(10) > 8) {
				
				ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK, 1);
				switch(random.nextInt(4)) {
					case 0:
						EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.CURSE_OF_AVARICE.get(), 1));
					break;
					case 1:
						EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.CURSE_OF_CHORUS.get(), 1));
					break;
					case 2:
						EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.CURSE_OF_DARKNESS.get(), 1));
					break;
					case 3:
						EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.CURSE_OF_DETONATION.get(), 1));
					break;
				}
				generatedLoot.add(itemstack);
				
			}
		
		}
		return generatedLoot;
		
	}
	
	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		
		return CODEC.get();
		
	}

}
