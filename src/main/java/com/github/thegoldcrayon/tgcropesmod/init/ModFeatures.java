package com.github.thegoldcrayon.tgcropesmod.init;
import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
    {

    }

    public static Feature<?> registerFeatures(Feature<?> entry, String name)
    {
        entry.setRegistryName(new ResourceLocation(TGCRopesMod.MODID, name));
        ForgeRegistries.FEATURES.register(entry);
        return entry;
    }
}
