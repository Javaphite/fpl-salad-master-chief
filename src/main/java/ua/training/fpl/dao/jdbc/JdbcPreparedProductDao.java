package ua.training.fpl.dao.jdbc;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.dao.PreparedProductDao;
import ua.training.fpl.exception.UncheckedSQLException;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JdbcPreparedProductDao implements PreparedProductDao {

    @Override
    public int create(PreparedProduct preparedProduct) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "INSERT INTO prepared_products(productId, preparationMethod) VALUES(?,?)")) {
            statement.setInt(1, preparedProduct.getProduct().getId());
            statement.setNString(2, preparedProduct.getPreparationMethod().name());
            statement.executeUpdate();
            connection.commit();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException exception) {
            LOG.error("Prepared product insertion failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public PreparedProduct find(int id) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "SELECT * FROM prepared_products WHERE preparedProductId=?")) {
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();

            PreparedProduct preparedProduct = new PreparedProduct();
            if (results.next()) {
                preparedProduct.setId(results.getInt(1));
                Product product =
                        AccessConfig.getDaoFactory().getProductDao().find(results.getInt(2));
                preparedProduct.setProduct(product);
                preparedProduct.setPreparationMethod(PreparedProduct.PreparationMethod.valueOf(results.getNString(3)));
            }
            return preparedProduct;
        } catch (SQLException exception) {
            LOG.error("Prepared product reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public Map<PreparedProduct, Long> findAllOfRecipe(int recipeId) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "SELECT * FROM products_in_recipes WHERE recipeId=?")) {
            statement.setInt(1, recipeId);
            ResultSet results = statement.executeQuery();

            Map<PreparedProduct, Long> productsInRecipe = new HashMap<>();
            while (results.next()) {
                int preparedProductId = results.getInt(3);
                long weight = results.getLong(4);
                productsInRecipe.put(find(preparedProductId), weight);
            }
            return productsInRecipe;
        } catch (SQLException exception) {
            LOG.error("Prepared product reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
