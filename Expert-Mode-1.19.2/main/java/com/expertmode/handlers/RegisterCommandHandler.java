package com.expertmode.handlers;

import com.expertmode.util.ExpertModeCommand;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterCommandHandler {

	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {

		ExpertModeCommand.register(event.getDispatcher());
		
	}
	
}
