package net.pekkatrol.hg2t.event;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.item.ModItems;
import net.pekkatrol.hg2t.potion.ModPotions;

@Mod.EventBusSubscriber(modid = HG2Tomato.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onBrewingRecipeBuilder(BrewingRecipeRegisterEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        //builder.addMix(Potions.AWKWARD, ModItems.TOMATO.get(), ModPotions.KETCHOUP.getHolder().get());
    }
}
