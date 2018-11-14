package ua.training.fpl.dao;

import ua.training.fpl.model.entity.PreparedProduct;

import java.util.Map;

public interface PreparedProductDao extends GenericDao<PreparedProduct> {

    Map<PreparedProduct, Long> findAllOfRecipe(int recipeId);
}
