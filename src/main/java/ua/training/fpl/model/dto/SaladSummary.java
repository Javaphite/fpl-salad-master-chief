package ua.training.fpl.model.dto;

import ua.training.fpl.model.entity.Salad;

/**
 * Simple DTO for transporting summarized information about
 * particular {@link Salad} from model to views.
 */
public class SaladSummary {

    private String name;
    private int portions;
    private long totalWeight;
    private long totalCalories;
    private boolean vegan;

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
