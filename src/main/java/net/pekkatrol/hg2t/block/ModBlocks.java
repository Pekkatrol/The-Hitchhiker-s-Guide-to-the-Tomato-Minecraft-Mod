package net.pekkatrol.hg2t.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HG2Tomato.MOD_ID);

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
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));


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

