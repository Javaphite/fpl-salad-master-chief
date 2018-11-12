package ua.training.fpl.model.entity;

public class Salad {

    private int id;
    private Recipe recipe;
    private int portions;

    public Salad() { }

    public Salad(Recipe recipe, int portions) {
        this.recipe = recipe;
        this.portions = portions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }
}
