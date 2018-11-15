package ua.training.fpl.util;

import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.Recipe;

public final class CaloriesCounter {

    private static final int CALORIES_CONSTANT = 100;

    private CaloriesCounter() {}

    public static long caloriesOf(Salad salad, PreparedProduct product) {
        return getCaloriesOfWeight(getCalorificValue(product), WeightCounter.weightOf(salad, product));
    }

    public static long caloriesOf(Salad salad) {
        return caloriesOf(salad.getRecipe()) * salad.getPortions();
    }

    public static long caloriesOf(Recipe recipe) {
        return recipe.getProducts()
                .entrySet()
                .stream()
                .mapToLong(component ->
                        getCaloriesOfWeight(component.getValue(), getCalorificValue(component.getKey())))
                .sum();
    }

    private static long getCalorificValue(PreparedProduct preparedProduct) {
        return preparedProduct.getProduct().getCalorificValue();
    }

    private static long getCaloriesOfWeight(long calorificValue, long weight) {
        long transit = calorificValue*weight;
        return transit/CALORIES_CONSTANT + transit%CALORIES_CONSTANT;
    }
}
