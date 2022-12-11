package com.expertmode.handlers;

import java.util.NavigableMap;

import com.expertmode.config.Config;
import com.expertmode.util.ExpertModeFunctions;
import com.expertmode.util.ExpertModeSavedData;
import com.expertmode.util.SettingMap;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldHandler {
	
	private static final boolean healthRegeneration = Config.Holder.healthRegeneration;
	
	@SubscribeEvent
	public static void worldLoadEvent(LevelEvent.Load event) {
		
		if(!event.getLevel().isClientSide()) {
			
			ExpertModeSavedData data = ExpertModeSavedData.get(event.getLevel().getServer().getLevel(ServerLevel.OVERWORLD));
			NavigableMap<String, Boolean> map = SettingMap.settings();
			for(String string : map.keySet()) {
				
				if(!data.getSetting(data, string)) {
				
					data.setSetting(string, map.get(string));
				
				}
				
			}
	
			GameRules gamerule = event.getLevel().getLevelData().getGameRules();
			MinecraftServer server = event.getLevel().getServer();
	
			boolean rule = gamerule.getBoolean(GameRules.RULE_NATURAL_REGENERATION);
			if(ExpertModeFunctions.isSettingEnabled(server.getLevel(ServerLevel.OVERWORLD), "health_regeneration")) {
				
				if(rule) {
					
					gamerule.getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, server);
					
				}
				
			} else if(healthRegeneration && !ExpertModeFunctions.isSettingEnabled(server.getLevel(ServerLevel.OVERWORLD), "health_regeneration")) { 
			
				if(!rule) {
					
					gamerule.getRule(GameRules.RULE_NATURAL_REGENERATION).set(true, server);
					
				}
			
			}
		
		}

	}

}
