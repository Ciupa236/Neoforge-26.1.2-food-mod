package net.ciupa.ciupafoods.item;

import net.ciupa.ciupafoods.CiupasYummies;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CiupasYummies.MOD_ID);

    public static final DeferredItem<Item> WHEAT_FLOUR = ITEMS.registerSimpleItem("wheat_flour");
    public static final DeferredItem<Item> DOUGH = ITEMS.registerSimpleItem("dough",
            properties -> new Item.Properties().craftRemainder(Items.BUCKET));
    public static final DeferredItem<Item> SALT = ITEMS.registerSimpleItem("salt");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
