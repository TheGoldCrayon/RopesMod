package com.github.thegoldcrayon.tgcropesmod.client;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.client.gui.DryingRackScreen;
import com.github.thegoldcrayon.tgcropesmod.client.renderer.DryingRackRenderer;
import com.github.thegoldcrayon.tgcropesmod.client.renderer.entity.RopeArrowRenderer;
import com.github.thegoldcrayon.tgcropesmod.init.ModBlocks;
import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = TGCRopesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientModEventSubscriber
{
    private static final Logger LOGGER = LogManager.getLogger(TGCRopesMod.MODID + "Client Mod Event Subscriber");

    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event)
    {
        //Misc. Renders
        //RenderType rendertype1 = RenderType.cutout();
        RenderType rendertype1 = RenderType.getCutout();

        RenderTypeLookup.setRenderLayer(ModBlocks.ROPE, rendertype1);
        RenderTypeLookup.setRenderLayer(ModBlocks.FLAX_CROP, rendertype1);
        RenderTypeLookup.setRenderLayer(ModBlocks.FLAX_BUSH, rendertype1);
        LOGGER.debug("Completed Misc. Renders");

        //Container Screens
        ScreenManager.registerFactory(ModRegistry.DRYING_RACK_CONTAINER_TYPE.get(), DryingRackScreen::new);
        LOGGER.debug("Completed Screen Renders");

        //Tile Entity Renderers
        ClientRegistry.bindTileEntityRenderer(ModRegistry.DRYING_RACK_TILE_ENTITY_TYPE.get(), DryingRackRenderer::new);
        LOGGER.debug("Completed Tile Entity Renderers");

        //Entity Renderers
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.ROPE_ARROW_ENTITY_TYPE.get(), RopeArrowRenderer::new);
        LOGGER.debug("Completed Entity Renderers");
    }
}
