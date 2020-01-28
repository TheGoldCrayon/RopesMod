package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.block.DryingRackBlock;
import com.github.thegoldcrayon.tgcropesmod.block.FlaxCropBlock;
import com.github.thegoldcrayon.tgcropesmod.block.RopeBlock;
import com.github.thegoldcrayon.tgcropesmod.container.DryingRackContainer;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry
{
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TGCRopesMod.MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TGCRopesMod.MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TGCRopesMod.MODID);
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, TGCRopesMod.MODID);


    //Blocks
    public static final RegistryObject<Block> ROPE = BLOCKS.register("rope", () -> new RopeBlock());
    public static final RegistryObject<Block> FLAX_CROP = BLOCKS.register("flax_crop", () -> new FlaxCropBlock());
    public static final RegistryObject<Block> DRYING_RACK = BLOCKS.register("drying_rack", () -> new DryingRackBlock());

    //Items
    public static final RegistryObject<Item> FRESH_FLAX = ITEMS.register("fresh_flax", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> DRY_FLAX = ITEMS.register("dry_flax", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> FLAX_SEEDS = ITEMS.register("flax_seeds", () -> new BlockNamedItem(FLAX_CROP.get(),new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ROPE_ITEM = ITEMS.register("rope", () -> new BlockItem(ROPE.get(), new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> DRYING_RACK_ITEM = ITEMS.register("drying_rack", () -> new BlockItem(DRYING_RACK.get(), new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));

    //Tile Entity Types
    public static final RegistryObject<TileEntityType<DryingRackTileEntity>> DRYING_RACK_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("drying_rack", () -> TileEntityType.Builder.create(DryingRackTileEntity::new, DRYING_RACK.get()).build(null));

    //Container Types
    public static final RegistryObject<ContainerType<DryingRackContainer>> DRYING_RACK_CONTAINER_TYPE = CONTAINER_TYPES.register("drying_rack", () -> IForgeContainerType.create(DryingRackContainer::new));

    //Tile Entities

    //Containers

    public static void register()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}