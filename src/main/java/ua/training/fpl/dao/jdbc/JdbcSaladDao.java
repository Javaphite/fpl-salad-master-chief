package ua.training.fpl.dao.jdbc;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.dao.SaladDao;
import ua.training.fpl.exception.UncheckedSQLException;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.model.entity.Salad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSaladDao implements SaladDao {

    @Override
    public int create(Salad salad) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "INSERT INTO salads(recipeId, portions) VALUES(?,?)")) {
            statement.setInt(1, salad.getRecipe().getId());
            statement.setInt(2, salad.getPortions());
            statement.executeUpdate();
            connection.commit();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException exception) {
            LOG.error("Salad insertion failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public Salad find(int id) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "SELECT * FROM salads WHERE saladId=?")) {
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();

            Salad salad = null;
            if (results.next()) {
                salad = new Salad();
                salad.setId(results.getInt(1));
                salad.setPortions(results.getInt(2));
                Recipe recipe = AccessConfig.getDaoFactory().getRecipeDao().find(results.getInt(3));
                salad.setRecipe(recipe);
            }
            return salad;
        } catch (SQLException exception) {
            LOG.error("Salad reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
