package ua.training.fpl.model.dao;

import ua.training.fpl.model.entity.PreparedProduct;

import java.util.Map;

public interface PreparedProductDao extends GenericDao<PreparedProduct> {

    /**
     * Searching for all {@link PreparedProduct}s participated in recipe with given id.
     * @param recipeId
     * @return map of prepared products and their weight in recipe
     */
    Map<PreparedProduct, Long> findAllOfRecipe(int recipeId);
}
