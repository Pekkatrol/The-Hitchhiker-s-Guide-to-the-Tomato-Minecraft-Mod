package net.pekkatrol.hg2t.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.block.custom.AlmondBushBlock;
import net.pekkatrol.hg2t.block.custom.TomatoCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HG2Tomato.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BLACK_SAND);
        blockWithItem(ModBlocks.MARBLE_BLOCK);
        blockWithItem(ModBlocks.MARBLE_BRICK);
        blockWithItem(ModBlocks.LUMIR_PLANKS);
        blockWithItem(ModBlocks.NICKEL_BLOCK);
        blockWithItem(ModBlocks.NICKEL_ORE);

        stairsBlock(ModBlocks.LUMIR_STAIRS.get(), blockTexture(ModBlocks.LUMIR_PLANKS.get()));
        slabBlock(ModBlocks.LUMIR_SLAB.get(), blockTexture(ModBlocks.LUMIR_PLANKS.get()), blockTexture(ModBlocks.LUMIR_PLANKS.get()));
        buttonBlock(ModBlocks.LUMIR_BUTTON.get(), blockTexture(ModBlocks.LUMIR_PLANKS.get()));
        pressurePlateBlock(ModBlocks.LUMIR_PRESSURE_PLATE.get(), blockTexture(ModBlocks.LUMIR_PLANKS.get()));
        fenceBlock(ModBlocks.LUMIR_FENCE.get(), blockTexture(ModBlocks.LUMIR_PLANKS.get()));
        fenceGateBlock(ModBlocks.LUMIR_FENCE_GATE.get(), blockTexture(ModBlocks.LUMIR_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.LUMIR_DOOR.get(), modLoc("block/lumir_door_bottom"), modLoc("block/lumir_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.LUMIR_TRAPDOOR.get(), modLoc("block/lumir_trapdoor"), true, "cutout");

        wallBlock(ModBlocks.MARBLE_WALL.get(), blockTexture(ModBlocks.MARBLE_BLOCK.get()));

        logBlock(ModBlocks.LUMIR_LOG.get());
        logBlock(ModBlocks.STRIPPED_LUMIR_LOG.get());

        leavesBlock(ModBlocks.LUMIR_LEAVES);
        saplingBlock(ModBlocks.LUMIR_SAPLING);

        blockItem(ModBlocks.LUMIR_LOG);
        blockItem(ModBlocks.STRIPPED_LUMIR_LOG);
        blockItem(ModBlocks.LUMIR_STAIRS);
        blockItem(ModBlocks.LUMIR_SLAB);
        blockItem(ModBlocks.LUMIR_PRESSURE_PLATE);
        blockItem(ModBlocks.LUMIR_FENCE_GATE);
        blockItem(ModBlocks.LUMIR_TRAPDOOR, "_bottom");

        makeCrop((CropBlock) ModBlocks.TOMATO_CROP.get(), "tomato_crop_stage", "tomato_crop_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.ALMOND_BUSH.get()), "almond_bush_stage", "almond_bush_stage");
    }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(AlmondBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "block/" + textureName + state.getValue(AlmondBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    public void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((TomatoCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "block/" + textureName + state.getValue(((TomatoCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("hgtotomato:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("hgtotomato:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
