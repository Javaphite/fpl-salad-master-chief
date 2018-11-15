package ua.training.fpl.model.dao;

import ua.training.fpl.model.entity.Recipe;

import java.util.List;

public interface RecipeDao extends GenericDao<Recipe> {

    /**
     * Searching for all recipes exist in remote storage.
     * @return list of recipes
     */
    List<Recipe> findAll();
}
