package ua.training.fpl.model.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Recipe {

    private int id;
    private String name;
    private Map<PreparedProduct, Long> products;

    private Recipe(String name, Map<PreparedProduct, Long> components) {
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

    public void setProdutcs(Map<PreparedProduct, Long> produtcs) {
        this.products = produtcs;
    }

    public static SaladRecipeBuilder builder() {
        return new SaladRecipeBuilder();
    }

    public static class SaladRecipeBuilder {
        private Map<PreparedProduct, Long> components = new HashMap<>();
        private String name;

        public SaladRecipeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SaladRecipeBuilder addComponent(PreparedProduct product, long weight) {
            components.putIfAbsent(product, weight);
            components.computeIfPresent(product, (key, value) -> value + weight);
            return this;
        }

        public SaladRecipeBuilder removeComponent(PreparedProduct product) {
            components.remove(product);
            return this;
        }

        public Recipe build() {
            return new Recipe(name, components);
        }
     }
}
