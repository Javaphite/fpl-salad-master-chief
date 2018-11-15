package ua.training.fpl.model.service;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.model.dto.RecipeSummary;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.util.CaloriesCounter;
import ua.training.fpl.util.VeganDetector;
import ua.training.fpl.util.WeightCounter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Includes service methods to work with recipes.
 */
public class RecipeService {

    /**
     * Retrieves information about all recipes in remote storage.
     * @return list of known recipes summaries
     */
    public List<RecipeSummary> getKnownRecipes() {
        return AccessConfig.getDaoFactory().getRecipeDao().findAll().stream()
                .map(this::getRecipeSummary)
                .collect(Collectors.toList());
    }

    /**
     * Sorts recipes using given {@link Comparator}.
     * @param comparator
     * @return sorted list of recipe summaries
     */
    public List<RecipeSummary> getRecipesSorted(Comparator<RecipeSummary> comparator) {
        List<RecipeSummary> recipeSummaries = getKnownRecipes();
        recipeSummaries.sort(comparator);
        return recipeSummaries;
    }

    /**
     * Filters recipes using given {@link Predicate}.
     * @param predicate describing recipes to be in resulted sample
     * @return sorted list of recipe summaries
     */
    public List<RecipeSummary> getRecipesFiltered(Predicate<RecipeSummary> predicate) {
        List<RecipeSummary> recipeSummaries = getKnownRecipes();
        recipeSummaries.removeIf(predicate.negate());
        return recipeSummaries;
    }

    private RecipeSummary getRecipeSummary(Recipe recipe) {
        return new RecipeSummary()
                .setId(recipe.getId())
                .setName(recipe.getName())
                .setCalories(CaloriesCounter.caloriesOf(recipe))
                .setWeight(WeightCounter.weightOf(recipe))
                .setVegan(VeganDetector.isVegan(recipe))
                .setDescription(getRecipeDescription(recipe));
    }

    private String getRecipeDescription(Recipe recipe) {
        StringBuilder descriptionBuilder = new StringBuilder();
        for (Map.Entry<PreparedProduct, Long> entry: recipe.getProducts().entrySet()) {
            descriptionBuilder.append(entry.getKey().getProduct().getName())
                    .append('(')
                    .append(entry.getValue())
                    .append("), ");
        }
        return descriptionBuilder.toString();
    }
}
