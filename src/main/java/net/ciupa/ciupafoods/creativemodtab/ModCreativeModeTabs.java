package net.ciupa.ciupafoods.creativemodtab;

import net.ciupa.ciupafoods.CiupasYummies;
import net.ciupa.ciupafoods.block.ModBlocks;
import net.ciupa.ciupafoods.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CiupasYummies.MOD_ID);

    public static final Supplier<CreativeModeTab> COOKING_INGREDIENTS_TAB = CREATIVE_MODE_TABS.register("cooking_ingredients_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WHEAT_FLOUR.get()))
                    .title(Component.translatable("creativemodetab.ciupafoods.cooking_ingredients"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.WHEAT_FLOUR);
                        output.accept(ModItems.DOUGH);
                        output.accept(ModItems.SALT);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> FOOD_BLOCKS_TAB = CREATIVE_MODE_TABS.register("food_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SALT_CUBE.get()))
                    .title(Component.translatable("creativemodetab.ciupafoods.food_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SALT_CUBE);
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
