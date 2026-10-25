package net.ciupa.ciupafoods.datagen;

import net.ciupa.ciupafoods.CiupasYummies;
import net.ciupa.ciupafoods.block.ModBlocks;
import net.ciupa.ciupafoods.item.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

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

        shapeless(RecipeCategory.FOOD, ModItems.DOUGH.get())
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.WHEAT_FLOUR.get())
                .requires(ModItems.SALT.get())
                .unlockedBy(getHasName(ModItems.WHEAT_FLOUR.get()), has(ModItems.WHEAT_FLOUR))
                .group("flour")
                .save(output);

        shaped(RecipeCategory.FOOD, ModItems.WHEAT_FLOUR.get())
                .pattern("S")
                .pattern("W")
                .pattern("S")
                .define('S', Items.COBBLESTONE)
                .define('W', Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .group("flour")
                .save(output);

        foodCookingRecipes("bread", ModItems.DOUGH.get(), Items.BREAD, 0.35F, output);
    }

    private static void foodCookingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, RecipeOutput output) {
        String namePrefix = Identifier.fromNamespaceAndPath(CiupasYummies.MOD_ID, name).toString();
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 600)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(output, namePrefix + "_from_campfire");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, 200)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(output, namePrefix + "_from_furnace");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(output, namePrefix + "_from_smoker");
    }
}
