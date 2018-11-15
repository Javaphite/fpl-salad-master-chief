package ua.training.fpl.model.dao.jdbc;

import ua.training.fpl.model.dao.DaoFactory;
import ua.training.fpl.model.dao.PreparedProductDao;
import ua.training.fpl.model.dao.ProductDao;
import ua.training.fpl.model.dao.RecipeDao;
import ua.training.fpl.model.dao.SaladDao;

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
