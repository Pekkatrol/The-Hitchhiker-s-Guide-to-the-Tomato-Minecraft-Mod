package net.pekkatrol.hg2t.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HG2Tomato.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels(){
        basicItem(ModItems.BANANA.get());
        basicItem(ModItems.BIO_POWDER.get());
        basicItem(ModItems.NICKEL_INGOT.get());
        basicItem(ModItems.RAW_NICKEL.get());
        basicItem(ModItems.WHEAT_POUCH.get());
        basicItem(ModItems.COMPACT_LOG.get());
        basicItem(ModItems.TOMATO_SEEDS.get());
        basicItem(ModItems.TOMATO.get());
        basicItem(ModItems.TOMATO_POUCH.get());
        basicItem(ModItems.KETCHOUP.get());

        buttonItem(ModBlocks.LUMIR_BUTTON, ModBlocks.LUMIR_PLANKS);
        fenceItem(ModBlocks.LUMIR_FENCE, ModBlocks.LUMIR_PLANKS);
        wallItem(ModBlocks.MARBLE_WALL, ModBlocks.MARBLE_BLOCK);

        simpleBlockItem(ModBlocks.LUMIR_DOOR);

        saplingItem(ModBlocks.LUMIR_SAPLING);

        handheldItem(ModItems.NOOKIA);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "block/" + item.getId().getPath()));
    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "item/" + item.getId().getPath()));
    }
}
