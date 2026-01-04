package net.pekkatrol.hg2t.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.BlockHitResult;
import net.pekkatrol.hg2t.block.entity.ModDataComponents;
import net.pekkatrol.hg2t.block.entity.custom.CardboardBlockEntity;
import net.pekkatrol.hg2t.block.entity.custom.PresentBlockEntity;
import net.pekkatrol.hg2t.block.entity.renderer.ModBlockEntities;

import javax.annotation.Nullable;

public class CardBoxBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final MapCodec<CardBoxBlock> CODEC = simpleCodec(CardBoxBlock::new);

    public CardBoxBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @javax.annotation.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CardboardBlockEntity(pPos, pState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && !player.isCreative()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof CardboardBlockEntity cardboard) {
                ItemStack stack = new ItemStack(this);
                CompoundTag invTag = new CompoundTag();
                cardboard.saveToItem(invTag, level.registryAccess());
                stack.set(ModDataComponents.CARDBOX_INVENTORY.get(), invTag);
                Containers.dropItemStack(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        stack
                );
            }
        }
        return state;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state,
                            @Nullable LivingEntity placer, ItemStack stack) {
        if (level.isClientSide) return;
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof CardboardBlockEntity cardboard) {
            CompoundTag tag = stack.get(ModDataComponents.CARDBOX_INVENTORY.get());
            if (tag != null) {
                cardboard.loadFromItem(tag, level.registryAccess());
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof  CardboardBlockEntity cardboardBlockEntity) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(cardboardBlockEntity, Component.literal("Cardboard")), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) {
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntities.CARDBOARD_BE.get(),
                (level, blockPos, blockState, cardboardBlockEntity) -> cardboardBlockEntity.tick(level, blockPos, blockState));
    }
}
