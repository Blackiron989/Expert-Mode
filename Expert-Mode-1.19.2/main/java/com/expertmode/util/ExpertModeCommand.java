package com.expertmode.util;

import java.util.NavigableMap;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ExpertModeCommand {
	
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		
		NavigableMap<String, Boolean> map = SettingMap.settings();
		
		final LiteralArgumentBuilder<CommandSourceStack> literalargumentbuilder = Commands.literal("expertmode").requires((command) -> {
			return command.hasPermission(2);
		}).executes((command) -> {
			return expertMode(command, map);
		}).then(Commands.literal("reset").executes((command) -> {
			return resetAll(command, map);
		}));

		for(String string : map.keySet()) {
					
			literalargumentbuilder.then(Commands.literal(string).executes((command) -> {
				
				return getSetting(command, string);
				
			}).then(Commands.literal("enable").executes((command) -> {
				
				return setSettingList(command, string, true);
				
			})).then(Commands.literal("disable").executes((command) -> {
				
				return setSettingList(command, string, false);
				
			})).then(Commands.literal("reset").executes((command) -> {
				
				return setSettingList(command, string, map.get(string));
				
			})));
			
		}
		
		dispatcher.register(literalargumentbuilder);
		
	}
	
	private static int expertMode(CommandContext<CommandSourceStack> context, NavigableMap<String, Boolean> map) {

		String s = "synced with config.";
		
		ExpertModeSavedData data = ExpertModeSavedData.get(context.getSource().getLevel());
		for(String string : map.keySet()) {
			
			if(data.getSetting(data, string) != map.get(string)) {
				
				s = "not synced with config.";
				break;
				
			}
			
		}
		
		context.getSource().sendSuccess(Component.translatable("Expert Mode is " + s), true);
		
		return 1;
		
	}
	
	private static int resetAll(CommandContext<CommandSourceStack> context, NavigableMap<String, Boolean> map) {
		
		for(String string : map.keySet()) {
			
			setSettingList(context, string, map.get(string));
			
		}
		
		return 1;
		
	}
	
	private static int setSettingList(CommandContext<CommandSourceStack> context, String string, boolean bool) {
		
		ExpertModeSavedData data = ExpertModeSavedData.get(context.getSource().getLevel());
		data.setSetting(string, bool);
		
		String s = state(bool);
		context.getSource().sendSuccess(Component.translatable(string + " is " + s), true);
		
		return 1;
		
	}
	
	private static int getSetting(CommandContext<CommandSourceStack> context, String string) {
		
		ExpertModeSavedData data = ExpertModeSavedData.get(context.getSource().getLevel());
		
		String s = state(data.getSetting(data, string));
		context.getSource().sendSuccess(Component.translatable(string + " is " + s), true);
		
		return 1;
		
	}
	
	private static String state(boolean bool) {
		
		if(bool) {
			
			return "enabled";
			
		} else {
			
			return "disabled";
			
		}
		
	}

}
