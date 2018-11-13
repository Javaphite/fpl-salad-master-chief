package ua.training.fpl.dao;

public interface DaoFactory {

    ProductDao getProductDao();

    PreparedProductDao getPreparedProductDao();

    RecipeDao getRecipeDao();

    SaladDao getSaladDao();
}
