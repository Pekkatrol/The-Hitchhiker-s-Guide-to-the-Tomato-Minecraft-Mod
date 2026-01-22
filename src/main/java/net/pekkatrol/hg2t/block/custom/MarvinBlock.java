package net.pekkatrol.hg2t.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MarvinBlock extends HorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = MarvinBlock.box(2, 0, 2, 14, 13, 14);
    public static final MapCodec<MarvinBlock> CODEC = simpleCodec(MarvinBlock::new);

    public MarvinBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected  MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
