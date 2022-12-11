package com.expertmode.handlers;

import com.expertmode.config.Config;

import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChunkHandler {
	
	private static final boolean ultraHard = Config.Holder.ultraHard;
	
	@SubscribeEvent
	public static void chunkLoadEvent(ChunkEvent.Load event) {
		
		if(!event.getLevel().isClientSide()) {
			
			if(ultraHard) {
				
				if(event.getChunk().getInhabitedTime() < 3600000) {
				
					event.getChunk().setInhabitedTime(3600000);
				
				}
				
			}
		
		}
		
	}

}
