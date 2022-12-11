package com.expertmode.loot;

import com.expertmode.Main;
import com.mojang.serialization.Codec;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChestLootModifier {
	
	/** Registry **/
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Main.MODID);

	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionBridgeModifier>> BASTION_BRIDGE = GLOBAL_LOOT_MODIFIER.register("bastion_bridge", BastionBridgeModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionHoglinStableModifier>> BASTION_HOGLIN_STABLE = GLOBAL_LOOT_MODIFIER.register("bastion_hoglin_stable", BastionHoglinStableModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionOtherModifier>> BASTION_OTHER = GLOBAL_LOOT_MODIFIER.register("bastion_other", BastionOtherModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionTreasureModifier>> BASTION_TREASURE = GLOBAL_LOOT_MODIFIER.register("bastion_treasure", BastionTreasureModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<NetherBridgeModifier>> NETHER_BRIDGE = GLOBAL_LOOT_MODIFIER.register("nether_bridge", NetherBridgeModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<RuinedPortalModifier>> RUINED_PORTAL = GLOBAL_LOOT_MODIFIER.register("ruined_portal", RuinedPortalModifier.CODEC);
	
	@SubscribeEvent
    public static void runData(GatherDataEvent event)
    {
		
		event.getGenerator().addProvider(event.includeServer(), new DataProvider(event.getGenerator(), Main.MODID));
        
    }
	
	private static class DataProvider extends GlobalLootModifierProvider {

		public DataProvider(DataGenerator gen, String modid) {
			
			super(gen, modid);

		}

		@Override
		protected void start() {
			
			add("bastion_bridge", new BastionBridgeModifier(
					
                new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_bridge")).build() })
					
            );
			add("bastion_hoglin_stable", new BastionHoglinStableModifier(
					
                new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_hoglin_stable")).build() })
					
            );
			add("bastion_other", new BastionOtherModifier(
					
                new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_other")).build() })
					
            );
			add("bastion_treasure", new BastionTreasureModifier(
			
				new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_treasure")).build() })
					
			);
			add("nether_bridge", new NetherBridgeModifier(
					
			    new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/nether_bridge")).build() })
					
			);
			add("ruined_portal", new RuinedPortalModifier(
				
				new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/ruined_portal")).build() })
					
			);

		}
		
	}

}
