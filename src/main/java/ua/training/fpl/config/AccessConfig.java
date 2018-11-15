package ua.training.fpl.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.fpl.model.dao.DaoFactory;
import ua.training.fpl.model.dao.jdbc.JdbcDaoFactory;
import ua.training.fpl.exception.UncheckedSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * Global configuration class of all access concerns.
 */
public final class AccessConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AccessConfig.class);
    private static final AccessConfig INSTANCE = new AccessConfig();

    private final MysqlDataSource dataSource;
    private final DaoFactory daoFactory;

    private AccessConfig() {
        daoFactory = new JdbcDaoFactory();
        dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://127.0.0.1:3306/master-chief");
        dataSource.setUser("accessor");
        dataSource.setPassword("whitesharkBBBBB");
        try {
            dataSource.setUseSSL(false);
        } catch(SQLException exception) {
            LOG.error("Data source exception: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    public static Connection getConnection() {
        try {
             Connection connection = INSTANCE.dataSource.getConnection();
             connection.setAutoCommit(false);
             return connection;
        } catch(SQLException exception) {
            LOG.error("Connection establishment exception: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    public static PreparedStatement getStatement(Connection connection, String sql) {
        try {
            return Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            LOG.error("Statement creation exception: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    public static DaoFactory getDaoFactory() {
        return INSTANCE.daoFactory;
    }
}
