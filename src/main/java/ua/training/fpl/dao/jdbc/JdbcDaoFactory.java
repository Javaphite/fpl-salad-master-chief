package ua.training.fpl.dao.jdbc;

import ua.training.fpl.dao.DaoFactory;
import ua.training.fpl.dao.PreparedProductDao;
import ua.training.fpl.dao.ProductDao;
import ua.training.fpl.dao.RecipeDao;
import ua.training.fpl.dao.SaladDao;

public class JdbcDaoFactory implements DaoFactory {

    @Override
    public ProductDao getProductDao() {
        return new JdbcProductDao();
    }

    @Override
    public PreparedProductDao getPreparedProductDao() {
        return new JdbcPreparedProductDao();
    }

    @Override
    public RecipeDao getRecipeDao() {
        return new JdbcRecipeDao();
    }

    @Override
    public SaladDao getSaladDao() {
        return new JdbcSaladDao();
    }
}
