package com.expertmode.items;

import java.util.ArrayList;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.DrinkHelper;
//import net.minecraft.util.Hand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class MilkBottleItem extends Item {

	public MilkBottleItem(Properties properties) {
		
		super(properties);

	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level level, LivingEntity living) {
		
		if(!level.isClientSide) {
			
			if(living instanceof Player) {
				
				Player player = (Player)living;

				for(MobEffect effect : removeableEffects()) {
					
					if(living.getEffect(effect) != null) {
						
						living.removeEffect(effect);
						
					}
					
				}
				
				if(!player.isCreative()) {
					
					itemstack.shrink(1);
					
				}
				
				if(itemstack.isEmpty()) {
					
					return new ItemStack(Items.GLASS_BOTTLE);
					
				} else {
						
					if(!player.isCreative()) {
						
						ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
						
						if(!player.getInventory().add(bottle)) {
							
							player.drop(bottle, false);
							
						}
						
					}
					
					return itemstack;
					
				}
				
			} else {
				
				return itemstack;
				
			}

		} else {
			
			return itemstack;
			
		}
		
	}
	
	@Override
	public int getUseDuration(ItemStack itemstack) {
	
		return 40;
	
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		
		return UseAnim.DRINK;
    }

	@Override
	public SoundEvent getDrinkingSound() {
		
		return SoundEvents.GENERIC_DRINK;
	
	}

	@Override
	public SoundEvent getEatingSound() {
		
		return SoundEvents.GENERIC_DRINK;
	
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		
		return ItemUtils.startUsingInstantly(level, player, hand);
		
	}
	
	/*@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		return DrinkHelper.useDrink(world, player, hand);
	}*/
	
	private ArrayList<MobEffect> removeableEffects() {
		
		ArrayList<MobEffect> list = new ArrayList<>();

		list.add(MobEffects.BLINDNESS);
		list.add(MobEffects.BAD_OMEN);
		list.add(MobEffects.DIG_SLOWDOWN);
		list.add(MobEffects.UNLUCK);
		list.add(MobEffects.MOVEMENT_SLOWDOWN);
		list.add(MobEffects.POISON);
		
		return list;
		
	}

}
