package ua.training.fpl.model.dto;

import ua.training.fpl.model.entity.Salad;

/**
 * Simple DTO for transporting detail information about
 * component of {@link Salad} from model to views.
 */
public class SaladComponent {

    private String name;
    private long calorificValue;
    private long weight;
    private long calories;
    private boolean vegan;
    private String category;
    private String preparationMethod;

    public String getName() {
        return name;
    }

    public SaladComponent setName(String name) {
        this.name = name;
        return this;
    }

    public long getCalorificValue() {
        return calorificValue;
    }

    public SaladComponent setCalorificValue(long calorificValue) {
        this.calorificValue = calorificValue;
        return this;
    }

    public long getWeight() {
        return weight;
    }

    public SaladComponent setWeight(long weight) {
        this.weight = weight;
        return this;
    }

    public long getCalories() {
        return calories;
    }

    public SaladComponent setCalories(long calories) {
        this.calories = calories;
        return this;
    }

    public boolean isVegan() {
        return vegan;
    }

    public SaladComponent setVegan(boolean vegan) {
        this.vegan = vegan;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SaladComponent setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public SaladComponent setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
        return this;
    }
}
