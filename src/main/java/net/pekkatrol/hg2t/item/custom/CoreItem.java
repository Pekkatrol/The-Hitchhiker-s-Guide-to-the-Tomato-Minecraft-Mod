package net.pekkatrol.hg2t.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.entity.ModEntities;
import net.pekkatrol.hg2t.entity.custom.EnergizedGolemEntity;

import java.util.Properties;

public class CoreItem extends Item {

    public CoreItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (!level.isClientSide) {
            if (level.getBlockState(pos).is(ModBlocks.CORE_ALTAR.get())) {
                EnergizedGolemEntity golem = ModEntities.ENERGIZED_GOLEM.get()
                        .create(level);
                if (golem != null) {
                    golem.moveTo(
                            pos.getX() + 0.5,
                            pos.getY() + 1,
                            pos.getZ() + 0.5,
                            level.getRandom().nextFloat() * 360F,
                            0F
                    );
                    level.addFreshEntity(golem);

                    if (player != null && !player.getAbilities().instabuild) {
                        context.getItemInHand().shrink(1);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
