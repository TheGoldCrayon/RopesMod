package com.github.thegoldcrayon.tgcropesmod;

import com.github.thegoldcrayon.tgcropesmod.block.FlaxCropBlock;
import com.github.thegoldcrayon.tgcropesmod.block.RopeBlock;
import com.github.thegoldcrayon.tgcropesmod.init.ModBlocks;
import com.github.thegoldcrayon.tgcropesmod.init.ModItemGroups;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = TGCRopesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber
{
    private static final Logger LOGGER = LogManager.getLogger(TGCRopesMod.MODID + " Mod Event Subscriber");

    //This will be called by Forge when it's time to register Blocks
    //This will always be called before Items
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(
                setup(new RopeBlock(), "rope"),
                setup(new FlaxCropBlock(), "flax_crop")
        );
        LOGGER.debug("Registered Blocks");
    }

    //This will be called by Forge when it's time to register Items
    //This will always be called after Blocks
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();

        registry.registerAll(
                setup(new BlockNamedItem(ModBlocks.FLAX_CROP, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "flax_seeds"),
                setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "fresh_flax"),
                setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "dry_flax")
        );

        //Go through entire registry as to include any potential Registry Overrides
        for(final Block block : ForgeRegistries.BLOCKS.getValues())
        {
            final ResourceLocation blockRegistryName = block.getRegistryName();

            // An extra safe-guard against badly registered blocks
            Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" of class \"" + block.getClass().getName() + "\"is null! This is not allowed!");

            // Check that the blocks is from our mod, if not, continue to the next block
            if (!blockRegistryName.getNamespace().equals(TGCRopesMod.MODID))
                continue;

            // If you have blocks that don't have a corresponding BlockItem, uncomment this code and create
            // an Interface - or even better an Annotation - called NoAutomaticBlockItem with no methods
            // and implement it on your blocks that shouldn't have BlockItems automatically made for them
//			if (block instanceof NoAutomaticBlockItem) {
//				continue;
//			}

            // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
            final Item.Properties properties = new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP);

            // Create the new BlockItem with the block and it's properties
            final BlockItem blockItem = new BlockItem(block, properties);

            // Setup the new BlockItem with the block's registry name and register it
            registry.register(setup(blockItem, blockRegistryName));
        }

        LOGGER.debug("Registered Items");
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name)
    {
        return setup(entry, new ResourceLocation(TGCRopesMod.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }
}
