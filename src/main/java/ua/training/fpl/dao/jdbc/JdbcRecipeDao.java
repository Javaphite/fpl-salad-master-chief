package ua.training.fpl.dao.jdbc;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.dao.RecipeDao;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcRecipeDao implements RecipeDao {

    @Override
    public int create(Recipe recipe) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "INSERT INTO recipes(name) VALUES(?)")) {
            statement.setNString(1, recipe.getName());
            statement.executeUpdate();

            // Read new recipe id before updating product-in-recipes table
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()) {
                recipe.setId(result.getInt(1));
            }

            // Commit all changes if and only if both updates were successful
            if (updateRecipeProductLinks(connection, recipe) ) {
                connection.commit();
                return recipe.getId();
            } else {
                return -1;
            }
        } catch (SQLException exception) {
            LOG.error("Recipe insertion failed: ", exception);
            return -1;
        }
    }

    @Override
    public Recipe read(int id) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "SELECT * FROM recipes WHERE recipesId=?")) {
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            Recipe recipe = null;
            if (results.next()) {
                Recipe.RecipeBuilder builder = Recipe.builder()
                        .setRecipeId(results.getInt(1))
                        .setName(results.getNString(2));

                AccessConfig.getDaoFactory().getPreparedProductDao()
                        .readAllOfRecipe(id)
                        .forEach(builder::addComponent);
                recipe = builder.build();
            }
            return recipe;
        } catch (SQLException exception) {
            LOG.error("Recipe reading failed: ", exception);
            return null;
        }
    }

    @Override
    public List<Recipe> readAll() {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,"SELECT * FROM recipes")) {
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int id = results.getInt(1);
                Recipe.RecipeBuilder builder = Recipe.builder()
                        .setRecipeId(id)
                        .setName(results.getNString(2));

                AccessConfig.getDaoFactory().getPreparedProductDao()
                        .readAllOfRecipe(id)
                        .forEach(builder::addComponent);
                recipes.add(builder.build());
            }
            return recipes;
        } catch (SQLException exception) {
            LOG.error("Recipes reading failed: ", exception);
            return Collections.emptyList();
        }
    }

    private boolean updateRecipeProductLinks(Connection connection, Recipe recipe) {
        try (PreparedStatement statement = AccessConfig.getStatement(connection,
                "INSERT INTO products_in_recipes(recipeId, preparedProductId, weight) VALUES(?,?,?)")) {
            for (PreparedProduct product : recipe.getProducts().keySet()) {
                statement.setInt(1, recipe.getId());
                statement.setInt(2, product.getId());
                statement.setLong(3, recipe.getProducts().get(product));
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        } catch (SQLException exception) {
            LOG.error("Products-in-Recipes information update failed: ", exception);
            return false;
        }
    }
}