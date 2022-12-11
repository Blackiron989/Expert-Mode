package com.expertmode.util;

import net.minecraft.server.level.ServerLevel;

public class ExpertModeFunctions {
	
	public static boolean isSettingEnabled(ServerLevel level, String setting) {
		
		if(level != null) {
		
			if(!level.isClientSide()) {
				
				ExpertModeSavedData data = ExpertModeSavedData.get(level);
				return data.getSetting(data, setting);
				
			}
		
		}
		
		return false;
		
	}

}
