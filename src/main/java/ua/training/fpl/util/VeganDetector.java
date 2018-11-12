package ua.training.fpl.util;

import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Product;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.Recipe;

public final class VeganDetector {

    private VeganDetector() {}

    public static boolean isVegan(PreparedProduct preparedProduct) {
        return isVegan(preparedProduct.getProduct());
    }

    public static boolean isVegan(Product product) {
        return product.getCategory().isVegan();
    }

    public static boolean isVegan(Salad salad) {
        return isVegan(salad.getRecipe());
    }

    public static boolean isVegan(Recipe recipe) {
        return recipe.getProducts()
                .keySet()
                .stream()
                .map(VeganDetector::isVegan)
                .reduce((x,y) -> x && y)
                .orElse(false);
    }
}

