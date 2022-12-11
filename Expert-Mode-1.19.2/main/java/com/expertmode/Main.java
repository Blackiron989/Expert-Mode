package com.expertmode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.expertmode.config.Config;
import com.expertmode.enchantments.EnchantmentLoader;
import com.expertmode.handlers.EventHandlers;
import com.expertmode.items.ItemLoader;
import com.expertmode.loot.ChestLootModifier;
import com.expertmode.potions.PotionLoader;
import com.expertmode.potions.PotionLoader.PotionCrafting;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("expertmode")
public class Main {
	
	public static final String MODID = "expertmode";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public Main() {
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        
        final IEventBus ieventbus = FMLJavaModLoadingContext.get().getModEventBus();
        
        //BlockLoader.BLOCKS.register(ieventbus);
        //BlockLoader.BLOCK_ITEM.register(ieventbus);
        EnchantmentLoader.ENCHANTMENTS.register(ieventbus);
        PotionLoader.EFFECTS.register(ieventbus);
        ItemLoader.ITEMS.register(ieventbus);
        
        EventHandlers.load();
        
        ChestLootModifier.GLOBAL_LOOT_MODIFIER.register(ieventbus);
        
        MinecraftForge.EVENT_BUS.register(this);
		
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		
		final ModLoadingContext modloadingcontext = ModLoadingContext.get();
    	
    	modloadingcontext.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        Config.loadConfig(Config.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve(MODID + ".toml"));
        
        Config.Holder.load();
        
        PotionCrafting.load();
    	
    	event.enqueueWork(new Runnable() {

			@Override
			public void run() {

				PotionCrafting.load();
				
			}
        	
        });
    	
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    	
    	//EventHandlers.loadClient();
    	
    }
    
    /*

     	Notes/Ideas/To-Do's:
     	
     	Update current "protection" enchantment changes to comepletely replace vanilla system rather than the current event based changes.
     	Challenges - Akin to that of B.O.I., (potentially better as a seperate mod) setup at world creation.
     	Champion Mobs - Infernal mobs esk mob overhaul/system.
     	Reworked Bosses - Add new stages, attacks, etc. to bosses.
		New dungeons.
		More difficult recipes.
		Reworked/Remade enchanting system (Probs better as seperate mod/module).
			Completely remake enchanting system to and related features.  No longer will xp have the the ~30
			level cap and instead levels will all be equal, but slightly more expensive.  Enchantments will
			be more predictable but more expensive and harder to achieve (ex. level 50+ costs).  Villagers will
			no longer distribute high level enchanted books and gear and will be more likely to include curses
			on the enchants they do provide.  Expand the amount of curses in the game and add them into the
			enchanting pool, with a way to avoid them at extra cost.  Repairing no longer will be limited
			in the amount of times an item can be repaired, increased repair costs to compensate.
		Enchantment Changes:
			Change loot tables to better balance loot spawn locations, ie. 1.19+ enchantment sneak speed would be found in nether bastions and soul speed vise-versa.
			Special enchantments will be moved to entirely loot based systems and would be removed/reduced from the enchanting table, villager trades, and fishing.
			Some enchantments could be added to the wandering traders loot table.

		Dangerous Villager Loot: Add a tag to chests that generate in villages and if the player takes anything from these chests it sets the villagers to "hostile" ie. worse trades and golems attack.
		Hostile World: All docile mobs are automatically set to hostile to the player.
			
		rework damage system related to things like protection enchantments to fully replace the original minecraft system, calling directly set health for the player and
		canceling the relative damage event.  This will require passing thru some information and damage types etc. in case of player death and to keep a similar setup to vanilla.
     	
     	v0.5+
     	More Curses: Just add more curses for now, they won't be distributed until the loot system is updated.
		Add weapon & tool related curses.
    */
    
    /**
      	Changelog: v0.4.1
      	Adjusted harder nights to apply speed to creepers instead of strength.
      	Fixed loot modifiers, will be reworked at some point.
      	Removed more debug messages.
      	Fixed "is is" in sync command.
      	Fixed issues with curses.
     */

}
