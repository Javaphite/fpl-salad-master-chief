package ua.training.fpl.model.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.model.dao.ProductDao;
import ua.training.fpl.exception.UncheckedSQLException;
import ua.training.fpl.model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProductDao implements ProductDao {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcProductDao.class);

    @Override
    public int create(Product product) {
       try (Connection connection = AccessConfig.getConnection();
            PreparedStatement statement = AccessConfig.getStatement(connection,
                    "INSERT INTO products(name, calorificValue, category)" +
                            "VALUES(?,?,?)")) {
           statement.setNString(1, product.getName());
           statement.setLong(2, product.getCalorificValue());
           statement.setNString(3, product.getCategory().name());
           statement.executeUpdate();
           connection.commit();

           ResultSet result = statement.getGeneratedKeys();
           if (result.next()) {
               return result.getInt(1);
           } else {
               return -1;
           }
        } catch (SQLException exception) {
            LOG.error("Product insertion failed: ", exception);
           throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection connection = AccessConfig.getConnection();
             PreparedStatement statement = AccessConfig.getStatement(connection,
                     "SELECT * FROM products WHERE productId=?")) {
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();

            Product product = null;
            if (results.next()) {
                product = new Product();
                product.setId(results.getInt(1));
                product.setName(results.getNString(2));
                product.setCalorificValue(results.getInt(3));
                product.setCategory(Product.ProductCategory.valueOf(results.getNString(4)));
            }
            return product;
        } catch (SQLException exception) {
            LOG.error("Product reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
