package com.expertmode.handlers;

import net.minecraftforge.common.MinecraftForge;

public class EventHandlers {
	
	public static void load() {

		MinecraftForge.EVENT_BUS.register(LivingDamageHandler.class);
		MinecraftForge.EVENT_BUS.register(PotionHandler.class);
		MinecraftForge.EVENT_BUS.register(HealingHandler.class);
		MinecraftForge.EVENT_BUS.register(LivingHurtHandler.class);
		MinecraftForge.EVENT_BUS.register(WorldHandler.class);
		MinecraftForge.EVENT_BUS.register(LivingEquipmentChangeHandler.class);
		MinecraftForge.EVENT_BUS.register(LivingUpdateHandler.class);
		MinecraftForge.EVENT_BUS.register(RegisterCommandHandler.class);
		MinecraftForge.EVENT_BUS.register(ChunkHandler.class);
		MinecraftForge.EVENT_BUS.register(PlayerXpHandler.class);
		MinecraftForge.EVENT_BUS.register(LivingDeathHandler.class);
		MinecraftForge.EVENT_BUS.register(LivingSpawnHandler.class);
		MinecraftForge.EVENT_BUS.register(EntityItemPickupHandler.class);
		
	}

	public static void loadClient() {
		
		MinecraftForge.EVENT_BUS.register(RenderGameOverlayHandler.class);
		
	}

}
