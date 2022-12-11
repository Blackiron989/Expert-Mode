package com.expertmode.handlers;

import com.expertmode.util.ExpertModeFunctions;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerXpHandler {
	
	@SubscribeEvent
	public static void playerXpEvent(PlayerXpEvent.XpChange event) {
		
		if(event.getEntity().getLevel().isClientSide()) return;
		
		if(event.getEntity() instanceof Player) {
		
			if(ExpertModeFunctions.isSettingEnabled(event.getEntity().getServer().getLevel(ServerLevel.OVERWORLD), "no_xp")) {
				
				event.setCanceled(true);
		
			}
			
		}
		
	}

}
