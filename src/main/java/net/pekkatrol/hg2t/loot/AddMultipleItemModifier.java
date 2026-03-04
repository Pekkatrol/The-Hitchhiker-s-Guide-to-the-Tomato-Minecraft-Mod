package net.pekkatrol.hg2t.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class AddMultipleItemModifier extends LootModifier {

    private final Item item;
    private final int quantity;

    public static final MapCodec<AddMultipleItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                            BuiltInRegistries.ITEM.byNameCodec()
                                    .fieldOf("item").forGetter(e -> e.item))
                    .and(Codec.INT.fieldOf("quantity").forGetter(e -> e.quantity))
                    .apply(inst, AddMultipleItemModifier::new));



    public AddMultipleItemModifier(LootItemCondition[] conditionsIn, Item item, int quantity) {
        super(conditionsIn);
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }
        generatedLoot.add(new ItemStack(this.item, this.quantity));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
