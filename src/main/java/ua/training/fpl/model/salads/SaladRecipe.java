package ua.training.fpl.model.salads;

import ua.training.fpl.model.products.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class SaladRecipe {

    private Set<SaladComponent> components;

    private SaladRecipe(Collection<SaladComponent> components) {
        this.components = new TreeSet<>(SaladComponent.CALORIFIC_VALUE_COMPARATOR);
        this.components.addAll(components);
    }

    public long getPortionWeight() {
        return components.stream()
                .mapToLong(SaladComponent::getWeight)
                .sum();
    }

    public long getPortionCalories() {
        return components.stream()
                .mapToLong(SaladComponent::getCalories)
                .sum();
    }

    public Set<SaladComponent> getComponents() {
        return components;
    }

    public SaladRecipeBuilder builder() {
        return new SaladRecipeBuilder();
    }


    private static class SaladRecipeBuilder {
        Map<Integer, SaladComponent> components = new HashMap<>();

        public SaladRecipeBuilder add(Product product, long weight, Product.PreparationMethod... preparationSteps) {
            if (Objects.isNull(product) || weight <= 0) {
                throw new IllegalArgumentException("No product or illegal weight received!");
            }

            SaladComponent newComponent = new SaladComponent(product, weight, preparationSteps);
            int newComponentHash = newComponent.hashCode();

            if (components.containsKey(newComponentHash)) {
                SaladComponent existingComponent = components.get(newComponentHash);
                existingComponent.setWeight(existingComponent.getWeight() + newComponent.getWeight());
            }
            else {
                components.put(newComponentHash, newComponent);
            }

            return this;
        }

        public SaladRecipeBuilder remove(Product product, long weight, Product.PreparationMethod... preparationSteps) {
            if (Objects.isNull(product) || weight <= 0) {
                throw new IllegalArgumentException("No product or illegal weight received!");
            }

            SaladComponent newComponent = new SaladComponent(product, weight, preparationSteps);
            int newComponentHash = newComponent.hashCode();

            if (components.containsKey(newComponentHash)) {
                SaladComponent existingComponent = components.get(newComponentHash);
                long weightDiff = existingComponent.getWeight() - newComponent.getWeight();

                if (weightDiff > 0) {
                    existingComponent.setWeight(weightDiff);
                } else {
                    components.remove(newComponentHash);
                }
            }

            return this;
        }

        public SaladRecipe build() {
            return new SaladRecipe(components.values());
        }
    }
}
