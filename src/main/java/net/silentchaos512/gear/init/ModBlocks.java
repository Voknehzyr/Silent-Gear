package net.silentchaos512.gear.init;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.block.*;
import net.silentchaos512.gear.block.craftingstation.CraftingStationBlock;
import net.silentchaos512.gear.block.grader.GraderBlock;
import net.silentchaos512.gear.block.salvager.SalvagerBlock;
import net.silentchaos512.lib.registry.BlockRegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ModBlocks {
    public static final BlockRegistryObject<OreBlock> CRIMSON_IRON_ORE = register("crimson_iron_ore", () ->
            getOre(2, SoundType.STONE));
    public static final BlockRegistryObject<OreBlock> AZURE_SILVER_ORE = register("azure_silver_ore", () ->
            getOre(4, SoundType.STONE));

    public static final BlockRegistryObject<MetalBlock> CRIMSON_IRON_BLOCK = register("crimson_iron_block", ModBlocks::getMetalBlock);
    public static final BlockRegistryObject<MetalBlock> CRIMSON_STEEL_BLOCK = register("crimson_steel_block", ModBlocks::getMetalBlock);
    public static final BlockRegistryObject<MetalBlock> BLAZE_GOLD_BLOCK = register("blaze_gold_block", ModBlocks::getMetalBlock);
    public static final BlockRegistryObject<MetalBlock> AZURE_SILVER_BLOCK = register("azure_silver_block", ModBlocks::getMetalBlock);
    public static final BlockRegistryObject<MetalBlock> AZURE_ELECTRUM_BLOCK = register("azure_electrum_block", ModBlocks::getMetalBlock);

    public static final BlockRegistryObject<GraderBlock> MATERIAL_GRADER = register("material_grader", () ->
            new GraderBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5, 30)));
    public static final BlockRegistryObject<CraftingStationBlock> CRAFTING_STATION = register("crafting_station", CraftingStationBlock::new);
    public static final BlockRegistryObject<SalvagerBlock> SALVAGER = register("salvager", SalvagerBlock::new);
    public static final BlockRegistryObject<FlaxPlant> FLAX_PLANT = registerNoItem("flax_plant", () ->
            new FlaxPlant(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)));
    public static final BlockRegistryObject<WildFlaxPlant> WILD_FLAX_PLANT = registerNoItem("wild_flax_plant", () ->
            new WildFlaxPlant(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)));
    public static final BlockRegistryObject<StoneTorch> STONE_TORCH = register("stone_torch", StoneTorch::new, bro -> getStoneTorchItem());
    public static final BlockRegistryObject<StoneTorchWall> WALL_STONE_TORCH = registerNoItem("wall_stone_torch", StoneTorchWall::new);
    public static final BlockRegistryObject<NetherwoodLog> NETHERWOOD_LOG = register("netherwood_log", () ->
            new NetherwoodLog(false));
    public static final BlockRegistryObject<NetherwoodLog> STRIPPED_NETHERWOOD_LOG = register("stripped_netherwood_log", () ->
            new NetherwoodLog(true));
    public static final BlockRegistryObject<NetherwoodPlanks> NETHERWOOD_PLANKS = register("netherwood_planks", NetherwoodPlanks::new);
    public static final BlockRegistryObject<NetherwoodSlab> NETHERWOOD_SLAB = register("netherwood_slab", NetherwoodSlab::new);
    public static final BlockRegistryObject<NetherwoodStairs> NETHERWOOD_STAIRS = register("netherwood_stairs", NetherwoodStairs::new);
    public static final BlockRegistryObject<NetherwoodFence> NETHERWOOD_FENCE = register("netherwood_fence", () ->
            new NetherwoodFence(Block.Properties.create(Material.WOOD).hardnessAndResistance(2f, 3f).sound(SoundType.WOOD)));
    public static final BlockRegistryObject<NetherwoodDoor> NETHERWOOD_DOOR = register("netherwood_door", () ->
            new NetherwoodDoor(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f).sound(SoundType.WOOD).notSolid()));
    public static final BlockRegistryObject<NetherwoodTrapdoor> NETHERWOOD_TRAPDOOR = register("netherwood_trapdoor", () ->
            new NetherwoodTrapdoor(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f).sound(SoundType.WOOD).notSolid()));
    public static final BlockRegistryObject<NetherwoodLeaves> NETHERWOOD_LEAVES = register("netherwood_leaves", NetherwoodLeaves::new);
    public static final BlockRegistryObject<NetherwoodSapling> NETHERWOOD_SAPLING = register("netherwood_sapling", NetherwoodSapling::new);
    public static final BlockRegistryObject<FlowerPotBlock> POTTED_NETHERWOOD_SAPLING = registerNoItem("potted_netherwood_sapling", () -> makePottedPlant(NETHERWOOD_SAPLING));
    public static final BlockRegistryObject<PhantomLight> PHANTOM_LIGHT = register("phantom_light", PhantomLight::new);

    private ModBlocks() {}

    static void register() {}

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderTypes(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(FLAX_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(NETHERWOOD_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(NETHERWOOD_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(NETHERWOOD_TRAPDOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(POTTED_NETHERWOOD_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(STONE_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WALL_STONE_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WILD_FLAX_PLANT.get(), RenderType.getCutout());
    }

    private static OreBlock getOre(int harvestLevel, SoundType soundType) {
        return new OreBlock(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(4, 10)
                .harvestLevel(harvestLevel)
                .harvestTool(ToolType.PICKAXE)
                .sound(soundType));
    }

    private static MetalBlock getMetalBlock() {
        return new MetalBlock(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(3.0f, 6.0f)
                .sound(SoundType.METAL));
    }

    private static <T extends Block> BlockRegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return new BlockRegistryObject<>(Registration.BLOCKS.register(name, block));
    }

    private static <T extends Block> BlockRegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, ModBlocks::defaultItem);
    }

    private static <T extends Block> BlockRegistryObject<T> register(String name, Supplier<T> block, Function<BlockRegistryObject<T>, Supplier<? extends BlockItem>> item) {
        BlockRegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, item.apply(ret));
        return ret;
    }

    private static <T extends Block> Supplier<BlockItem> defaultItem(BlockRegistryObject<T> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().group(SilentGear.ITEM_GROUP));
    }

    private static Supplier<BlockItem> getStoneTorchItem() {
        return () -> new WallOrFloorItem(STONE_TORCH.get(), WALL_STONE_TORCH.get(), new Item.Properties().group(SilentGear.ITEM_GROUP));
    }

    private static FlowerPotBlock makePottedPlant(Supplier<? extends Block> flower) {
        FlowerPotBlock potted = new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), flower, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0));
        ResourceLocation flowerId = Objects.requireNonNull(flower.get().getRegistryName());
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(flowerId, () -> potted);
        return potted;
    }
}
