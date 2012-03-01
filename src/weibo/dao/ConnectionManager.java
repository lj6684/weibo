package weibo.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class ConnectionManager {

    private static ConnectionManager instance;
    private static ComboPooledDataSource dataSource;

    private ConnectionManager() throws SQLException, PropertyVetoException {
        dataSource = new ComboPooledDataSource();

        dataSource.setUser("root");
        dataSource.setPassword("11111111");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/weibo");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setInitialPoolSize(5);
        dataSource.setMinPoolSize(1);
        dataSource.setMaxPoolSize(10);
        dataSource.setMaxStatements(50);
        dataSource.setMaxIdleTime(60);
    }

    public static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized final Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

