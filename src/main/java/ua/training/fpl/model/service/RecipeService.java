package ua.training.fpl.model.service;

import ua.training.fpl.config.AccessConfig;
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
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RecipeService {

    public List<RecipeSummary> getKnownRecipes() {
        return AccessConfig.getDaoFactory().getRecipeDao().readAll().stream()
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

    // TODO: add special method to DAO and reimplement this efficiently
    public Recipe getRecipeByName(String name) {
        return AccessConfig.getDaoFactory().getRecipeDao().readAll().stream()
                .filter(recipe -> Objects.equals(recipe.getName(), name))
                .findFirst()
                .get();
    }

    private RecipeSummary getRecipeSummary(Recipe recipe) {
        return new RecipeSummary()
                .setId(recipe.getId())
                .setName(recipe.getName())
                .setCalories(CaloriesCounter.caloriesOf(recipe))
                .setWeight(WeightCounter.weightOf(recipe))
                .setVegan(VeganDetector.isVegan(recipe))
                .setDescription(recipe.toString());
    }
}
