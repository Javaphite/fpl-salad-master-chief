package ua.training.fpl.model.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Entity for recipe representation.
 */
public final class Recipe {

    private int id;
    private String name;
    private Map<PreparedProduct, Long> products;

    private Recipe(int id, String name, Map<PreparedProduct, Long> components) {
        this.id = id;
        this.name = name;
        this.products = Collections.unmodifiableMap(components);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<PreparedProduct, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<PreparedProduct, Long> products) {
        this.products = products;
    }

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    public static class RecipeBuilder {
        private int recipeId;
        private String name;
        private Map<PreparedProduct, Long> components;

        // Accumulates data in SortedMap to predict order of elements.
        private RecipeBuilder() {
            Comparator<PreparedProduct> comparator =
                    Comparator.comparing(component -> component.getProduct().getCalorificValue());
            components = new TreeMap<>(comparator);
        }

        public RecipeBuilder setRecipeId(int recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public RecipeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Adds product with weight or accumulates weight if product already added
         * @param product
         * @param weight
         * @return
         */
        public RecipeBuilder addComponent(PreparedProduct product, long weight) {
            components.computeIfPresent(product, (key, value) -> value + weight);
            components.putIfAbsent(product, weight);
            return this;
        }

        public Recipe build() {
            return new Recipe(recipeId, name, components);
        }
     }
}
