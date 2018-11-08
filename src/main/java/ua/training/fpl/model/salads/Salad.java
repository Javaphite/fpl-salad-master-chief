package ua.training.fpl.model.salads;

public class Salad {

    private SaladRecipe recipe;
    private int portions;

    public Salad(SaladRecipe recipe, int portions) {
        this.recipe = recipe;
        setPortions(portions);
    }

    public SaladRecipe getRecipe() {
        return recipe;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        if (portions > 0) {
            this.portions = portions;
        } else {
            throw new IllegalArgumentException("Portion quantity shouldn't be less or equal to 0.");
        }
    }
}
