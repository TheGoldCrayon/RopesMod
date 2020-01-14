package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.block.FlaxCropBlock;
import com.github.thegoldcrayon.tgcropesmod.block.RopeBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//@Mod.EventBusSubscriber(modid = TGCRopesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry
{
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TGCRopesMod.MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TGCRopesMod.MODID);

    //Items
    public static final RegistryObject<Item> FRESH_FLAX = ITEMS.register("fresh_flax", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> DRY_FLAX = ITEMS.register("dry_flax", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> FLAX_SEEDS = ITEMS.register("flax_seeds", () -> new BlockNamedItem(ModBlocks.FLAX_CROP,new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ROPE_ITEM = ITEMS.register("rope", () -> new BlockItem(ModBlocks.ROPE, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));

    //Blocks
    public static final RegistryObject<Block> ROPE = BLOCKS.register("rope", () -> new RopeBlock());
    public static final RegistryObject<Block> FLAX_CROP = BLOCKS.register("flax_crop", () -> new FlaxCropBlock());

    public static void register()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}