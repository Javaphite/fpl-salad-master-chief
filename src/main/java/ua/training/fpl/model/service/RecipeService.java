package ua.training.fpl.model.service;

import ua.training.fpl.dto.RecipeSummary;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Product;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.util.CaloriesCounter;
import ua.training.fpl.util.VeganDetector;
import ua.training.fpl.util.WeightCounter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RecipeService implements Service {
    private Collection<Recipe> knownRecipes = new ArrayList<>();

    {
        Product product = new Product();
        product.setName("Mayonaisse");
        product.setCategory(Product.ProductCategory.OTHER);
        product.setCalorificValue(1000);
        PreparedProduct preparedProduct = new PreparedProduct(product, PreparedProduct.PreparationMethod.RAW);
        knownRecipes.add(Recipe.builder()
                .setName("Recipe 1")
                .addComponent(preparedProduct, 100)
                .build());
        knownRecipes.add(Recipe.builder()
                .setName("Recipe 2")
                .addComponent(preparedProduct, 200)
                .build());
    }

    public List<RecipeSummary> getKnownRecipes() {
        return knownRecipes.stream()
                .map(this::getRecipeSummary)
                .collect(Collectors.toList());
    }

    public List<RecipeSummary> getRecipesSorted(Comparator<RecipeSummary> comparator) {
        List<RecipeSummary> recipeSummaries = getKnownRecipes();
        recipeSummaries.sort(comparator);

        return recipeSummaries;
    }

    public List<RecipeSummary> getRecipesFiltered(Predicate<RecipeSummary> predicate) {
        List<RecipeSummary> recipeSummaries = getKnownRecipes();
        recipeSummaries.removeIf(predicate.negate());

        return recipeSummaries;
    }


    private RecipeSummary getRecipeSummary(Recipe recipe) {
        RecipeSummary recipeSummary = new RecipeSummary();
        recipeSummary.setName(recipe.getName());
        recipeSummary.setCalories(CaloriesCounter.caloriesOf(recipe));
        recipeSummary.setWeight(WeightCounter.weightOf(recipe));
        recipeSummary.setVegan(VeganDetector.isVegan(recipe));
        recipeSummary.setDescription(recipe.toString());

        return recipeSummary;
    }
}
