package net.pekkatrol.hg2t.block;

import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.custom.*;
import net.pekkatrol.hg2t.item.ModItems;
import net.pekkatrol.hg2t.worldgen.tree.ModTreeGrowers;

import java.util.function.Supplier;


public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HG2Tomato.MOD_ID);

    public static final RegistryObject<Block> BLACK_SAND = registerBlock("black_sand",
            () -> new ColoredFallingBlock(
            new ColorRGBA(14406560),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                .instrument(NoteBlockInstrument.SNARE)
                .strength(0.5F)
                .sound(SoundType.SAND)
        ));


    public static final RegistryObject<Block> MARBLE_BLOCK = registerBlock("marble_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> MARBLE_BRICK = registerBlock("marble_brick",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> MARBLE_PILLAR = registerBlock("marble_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> CARD_BOX = registerBlock("card_box",
            () -> new CardBoxBlock(BlockBehaviour.Properties.of()
                    .strength(2f, 3f)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PRESENT = registerBlock("present",
            () -> new PresentBlock(BlockBehaviour.Properties.of()
                    .strength(2f, 3f)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> NICKEL_BLOCK = registerBlock("nickel_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> NICKEL_ORE = registerBlock("nickel_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> LUMIR_PLANKS = registerBlock("lumir_planks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f, 3f)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<RotatedPillarBlock> LUMIR_LOG = registerBlock("lumir_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_LUMIR_LOG = registerBlock("stripped_lumir_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> MARVIN = registerBlock("marvin",
            () -> new MarvinBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> CHAIR = registerBlock("chair",
            () -> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(2f, 3f)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> TABLE = registerBlock("table",
            () -> new TableBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(2f, 3f)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LUMIR_LEAVES = registerBlock("lumir_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> LUMIR_SAPLING = registerBlock("lumir_sapling",
            () -> new SaplingBlock(ModTreeGrowers.LUMIR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<FenceBlock> LUMIR_FENCE = registerBlock("lumir_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f, 3f)
                    .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<FenceGateBlock> LUMIR_FENCE_GATE = registerBlock("lumir_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
                    )
    );

    public static final RegistryObject<SlabBlock> LUMIR_SLAB = registerBlock("lumir_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<StairBlock> LUMIR_STAIRS = registerBlock("lumir_stairs",
            () -> new StairBlock(
                    LUMIR_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<PressurePlateBlock> LUMIR_PRESSURE_PLATE = registerBlock("lumir_pressure_plate",
            () -> new PressurePlateBlock(
                    BlockSetType.OAK,
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<ButtonBlock> LUMIR_BUTTON = registerBlock("lumir_button",
            () -> new ButtonBlock(
                    BlockSetType.OAK,
                    5,
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
                            .noCollission()
            )
    );

    public static final RegistryObject<DoorBlock> LUMIR_DOOR = registerBlock("lumir_door",
            () -> new DoorBlock(
                    BlockSetType.OAK,
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
            )
    );

    public static final RegistryObject<TrapDoorBlock> LUMIR_TRAPDOOR = registerBlock("lumir_trapdoor",
            () -> new TrapDoorBlock(
                    BlockSetType.OAK,
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
            )
    );

    public static final RegistryObject<WallBlock> MARBLE_WALL = registerBlock("marble_wall",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.STONE)
            )
    );

    public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            () -> new TomatoCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final RegistryObject<Block> ALMOND_BUSH = BLOCKS.register("almond_bush",
            () -> new AlmondBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));


    public static final RegistryObject<Block> CORE_ALTAR = registerBlock("core_altar",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(-1.0F, 3600000.0F)
                    .sound(SoundType.STONE)
                    .noLootTable()
            ));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

