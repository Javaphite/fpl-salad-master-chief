package ua.training.fpl.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.fpl.dao.DaoFactory;
import ua.training.fpl.dao.jdbc.JdbcDaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public final class AccessConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AccessConfig.class);
    private static final AccessConfig INSTANCE = new AccessConfig();

    private MysqlDataSource dataSource;
    private DaoFactory daoFactory;

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
            throw new RuntimeException(exception);
        }
    }

    public static Connection getConnection() {
        try {
             Connection connection = INSTANCE.dataSource.getConnection();
             connection.setAutoCommit(false);
             return connection;
        } catch(SQLException exception) {
            LOG.error("Connection establishment exception: ", exception);
            return null;
        }
    }

    public static PreparedStatement getStatement(Connection connection, String sql) {
        try {
            return Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            LOG.error("Statement creation exception: ", exception);
            return null;
        }
    }

    public static DaoFactory getDaoFactory() {
        return INSTANCE.daoFactory;
    }
}
