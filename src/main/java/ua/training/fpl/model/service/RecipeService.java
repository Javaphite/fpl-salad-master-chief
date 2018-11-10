package ua.training.fpl.model.service;

import ua.training.fpl.dto.SaladRecipeSummary;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Product;
import ua.training.fpl.model.entity.SaladRecipe;
import ua.training.fpl.util.CaloriesCounter;
import ua.training.fpl.util.VeganDetector;
import ua.training.fpl.util.WeightCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {

    List<SaladRecipe> knownRecipes;

    {
        Product product = new Product();
        product.setName("Mayonaisse");
        product.setCategory(Product.ProductCategory.OTHER);
        product.setCalorificValue(1000);
        PreparedProduct preparedProduct = new PreparedProduct(product, PreparedProduct.PreparationMethod.RAW);
        knownRecipes = new ArrayList<>();
        knownRecipes.add(SaladRecipe.builder()
                .setName("Recipe 1")
                .addComponent(preparedProduct, 100)
                .build());
        knownRecipes.add(SaladRecipe.builder()
                .setName("Recipe 2")
                .addComponent(preparedProduct, 200)
                .build());
    }

    public List<SaladRecipeSummary> getKnownRecipes() {
        return knownRecipes.stream()
                .map(this::getRecipeSummary)
                .collect(Collectors.toList());
    }

    private SaladRecipeSummary getRecipeSummary(SaladRecipe recipe) {
        SaladRecipeSummary recipeSummary = new SaladRecipeSummary();
        recipeSummary.setName(recipe.getName());
        recipeSummary.setCalories(CaloriesCounter.caloriesOf(recipe));
        recipeSummary.setWeight(WeightCounter.weightOf(recipe));
        recipeSummary.setVegan(VeganDetector.isVegan(recipe));
        recipeSummary.setDescription(recipe.toString());

        return recipeSummary;
    }

    // TODO: implement after JSP layer compeleted
    public SaladRecipe registerRecipe() { return null;}
}
