package ua.training.fpl.dto;

public class SaladSummary {

    String name;
    int portions;
    long totalWeight;
    long totalCalories;
    boolean vegan;

    public String getName() {
        return name;
    }

    public SaladSummary setName(String name) {
        this.name = name;
        return this;
    }

    public int getPortions() {
        return portions;
    }

    public SaladSummary setPortions(int portions) {
        this.portions = portions;
        return this;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public SaladSummary setTotalWeight(long totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public long getTotalCalories() {
        return totalCalories;
    }

    public SaladSummary setTotalCalories(long totalCalories) {
        this.totalCalories = totalCalories;
        return this;
    }

    public boolean isVegan() {
        return vegan;
    }

    public SaladSummary setVegan(boolean vegan) {
        this.vegan = vegan;
        return this;
    }
}
