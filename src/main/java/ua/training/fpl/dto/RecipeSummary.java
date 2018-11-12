package ua.training.fpl.dto;

public class RecipeSummary {

    private String name;
    private String description;
    private long weight;
    private long calories;
    private boolean vegan;

    public String getName() {
        return name;
    }

    public RecipeSummary setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeSummary setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getWeight() {
        return weight;
    }

    public RecipeSummary setWeight(long weight) {
        this.weight = weight;
        return this;
    }

    public long getCalories() {
        return calories;
    }

    public RecipeSummary setCalories(long calories) {
        this.calories = calories;
        return this;
    }

    public boolean isVegan() {
        return vegan;
    }

    public RecipeSummary setVegan(boolean vegan) {
        this.vegan = vegan;
        return this;
    }
}
