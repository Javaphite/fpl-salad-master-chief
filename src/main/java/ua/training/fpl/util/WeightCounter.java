package ua.training.fpl.util;

import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.Recipe;

import java.util.Map;

public final class WeightCounter {

    private WeightCounter() {}

    public static long weightOf(Salad salad, PreparedProduct product) {
        return  salad.getRecipe().getProducts().get(product) * salad.getPortions();
    }

    public static long weightOf(Salad salad) {
        return weightOf(salad.getRecipe()) * salad.getPortions();
    }

    public static long weightOf(Recipe recipe) {
        return recipe.getProducts()
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .sum();
    }
}
