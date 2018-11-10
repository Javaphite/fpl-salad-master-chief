package ua.training.fpl.model.entity;

public class Salad {

    private SaladRecipe recipe;
    private int portions;

    public Salad() { }

    public Salad(SaladRecipe recipe, int portions) {
        this.recipe = recipe;
        this.portions = portions;
    }

    public SaladRecipe getRecipe() {
        return recipe;
    }
    public void setRecipe(SaladRecipe recipe) {
        this.recipe = recipe;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }
}
