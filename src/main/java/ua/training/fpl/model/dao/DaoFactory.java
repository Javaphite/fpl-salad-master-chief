package ua.training.fpl.model.dao;

public interface DaoFactory {

    ProductDao getProductDao();

    PreparedProductDao getPreparedProductDao();

    RecipeDao getRecipeDao();

    SaladDao getSaladDao();
}
