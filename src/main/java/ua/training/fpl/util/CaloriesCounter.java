package ua.training.fpl.util;

import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.Recipe;

public final class CaloriesCounter {

    private CaloriesCounter() {}

    public static long caloriesOf(Salad salad, PreparedProduct product) {
        return getProductCalorificValue(product) * salad.getPortions();
    }

    public static long caloriesOf(Salad salad) {
        return caloriesOf(salad.getRecipe()) * salad.getPortions();
    }

    public static long caloriesOf(Recipe recipe) {
        return recipe.getComponents()
                .entrySet()
                .stream()
                .mapToLong(component -> component.getValue()*getProductCalorificValue(component.getKey()))
                .sum();
    }

    private static long getProductCalorificValue(PreparedProduct preparedProduct) {
        return preparedProduct.getProduct().getCalorificValue();
    }
}
