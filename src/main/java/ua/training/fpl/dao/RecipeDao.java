package ua.training.fpl.dao;

import ua.training.fpl.model.entity.Recipe;

import java.util.List;

public interface RecipeDao extends GenericDao<Recipe> {

    List<Recipe> readAll();
}
