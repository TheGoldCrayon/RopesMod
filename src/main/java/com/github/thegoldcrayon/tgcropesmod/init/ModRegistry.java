package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.block.*;
import com.github.thegoldcrayon.tgcropesmod.container.DryingRackContainer;
import com.github.thegoldcrayon.tgcropesmod.entity.RopeArrowEntity;
import com.github.thegoldcrayon.tgcropesmod.item.RopeArrow;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.SoupItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TGCRopesMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TGCRopesMod.MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TGCRopesMod.MODID);
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, TGCRopesMod.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TGCRopesMod.MODID);


    //Blocks
    public static final RegistryObject<Block> ROPE = BLOCKS.register("rope", () -> new RopeBlock());
    public static final RegistryObject<Block> FLAX_CROP = BLOCKS.register("flax_crop", () -> new FlaxCropBlock());
    public static final RegistryObject<Block> BLOCK_OF_ROPE = BLOCKS.register("block_of_rope", () -> new BlockOfRopeBlock());
    public static final RegistryObject<Block> FLAX_BUSH = BLOCKS.register("flax_bush", () -> new FlaxBushBlock());

    //Tile Entities
    public static final RegistryObject<Block> DRYING_RACK = BLOCKS.register("drying_rack", () -> new DryingRackBlock());

    //Items
    public static final RegistryObject<Item> FRESH_FLAX = ITEMS.register("fresh_flax", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> DRY_FLAX = ITEMS.register("dry_flax", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> FLAX_SEEDS = ITEMS.register("flax_seeds", () -> new BlockNamedItem(FLAX_CROP.get(),new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ROPE_ITEM = ITEMS.register("rope", () -> new BlockItem(ROPE.get(), new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> DRYING_RACK_ITEM = ITEMS.register("drying_rack", () -> new BlockItem(DRYING_RACK.get(), new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> FLAXSEED_SOUP = ITEMS.register("flaxseed_soup", () -> new SoupItem(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).food(ModFoods.FLAXSEED_SOUP).maxStackSize(1)));
    public static final RegistryObject<Item> ROPE_ARROW_ITEM = ITEMS.register("rope_arrow", () -> new RopeArrow());
    public static final RegistryObject<Item> BLOCK_OF_ROPE_ITEM = ITEMS.register("block_of_rope", () -> new BlockItem(BLOCK_OF_ROPE.get(), new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> FLAX_BUSH_ITEM = ITEMS.register("flax_bush", () -> new BlockItem(FLAX_BUSH.get(), new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));

    //Tile Entity Types
    public static final RegistryObject<TileEntityType<DryingRackTileEntity>> DRYING_RACK_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("drying_rack", () -> TileEntityType.Builder.create(DryingRackTileEntity::new, DRYING_RACK.get()).build(null));

    //Container Types
    public static final RegistryObject<ContainerType<DryingRackContainer>> DRYING_RACK_CONTAINER_TYPE = CONTAINER_TYPES.register("drying_rack", () -> IForgeContainerType.create(DryingRackContainer::new));

    //Entity Types
    public static final RegistryObject<EntityType<RopeArrowEntity>> ROPE_ARROW_ENTITY_TYPE = ENTITY_TYPES.register("rope_arrow", () ->
            EntityType.Builder
            .<RopeArrowEntity>create(RopeArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5f)
            .setCustomClientFactory(RopeArrowEntity::new)
            .build(new ResourceLocation(TGCRopesMod.MODID, "rope_arrow").toString())
    );

    public static void register()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}