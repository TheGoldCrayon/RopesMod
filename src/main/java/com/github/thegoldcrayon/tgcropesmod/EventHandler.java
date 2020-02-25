package com.github.thegoldcrayon.tgcropesmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = TGCRopesMod.MODID)
public class EventHandler
{
    private static ResourceLocation grass = new ResourceLocation("minecraft", "blocks/grass");
    private static ResourceLocation newTable = new ResourceLocation(TGCRopesMod.MODID, "blocks/grass");

    public static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event)
    {
        if(event.getName().equals(grass))
            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(newTable)).build());

        //LOGGER.debug("Loaded loot table additions");
    }
}
