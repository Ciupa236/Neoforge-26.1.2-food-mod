package net.ciupa.ciupafoods.datagen;

import net.ciupa.ciupafoods.block.ModBlocks;
import net.ciupa.ciupafoods.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class runner extends RecipeProvider.Runner {
        public runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "CiupasYummies Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        twoByTwoPacker(RecipeCategory.MISC, ModBlocks.SALT_CUBE.get(), ModItems.SALT.get());

        shaped(RecipeCategory.MISC, ModItems.WHEAT_FLOUR.get())
                .pattern("S")
                .pattern("W")
                .pattern("S")
                .define('S', Items.COBBLESTONE)
                .define('W', Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT),has(Items.WHEAT))
                .group("flour")
                .save(output);

        simpleCookingRecipe("furnace", SmeltingRecipe::new, 200, ModItems.DOUGH, Items.BREAD, 0.35F);
        simpleCookingRecipe("smoker", SmokingRecipe::new, 100, ModItems.DOUGH, Items.BREAD, 0.35F);
    }
}
