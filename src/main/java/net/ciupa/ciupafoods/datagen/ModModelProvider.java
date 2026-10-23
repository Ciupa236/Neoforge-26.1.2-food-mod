package net.ciupa.ciupafoods.datagen;

import net.ciupa.ciupafoods.CiupasYummies;
import net.ciupa.ciupafoods.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, CiupasYummies.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.FLOUR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.DOUGH.get(), ModelTemplates.FLAT_ITEM);


    }
}
