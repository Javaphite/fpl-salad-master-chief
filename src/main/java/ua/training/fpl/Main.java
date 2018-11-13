package ua.training.fpl;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.entity.Recipe;

public class Main {

    public static void main(String[] args) {
        /*AccessConfig.getDaoFactory().getRecipeDao().readAll();
        ApplicationConfig.getRecipeService().getKnownRecipes();*/
      //  Recipe recipe = AccessConfig.getDaoFactory().getRecipeDao().read(21);
        Recipe recipe2 = ApplicationConfig.getRecipeService().getRecipeByName("Egyptian egg salad");
        ApplicationConfig.getSaladService().createSalad(recipe2, 3);
    }
}
