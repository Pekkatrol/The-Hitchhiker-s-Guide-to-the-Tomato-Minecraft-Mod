package net.pekkatrol.hg2t.item.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;

public class NookiaItem extends PickaxeItem {
    public NookiaItem(Tier tier, Properties props) {
        super(tier, props);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.getDestroySpeed(null, null) >= 0;
    }

}
