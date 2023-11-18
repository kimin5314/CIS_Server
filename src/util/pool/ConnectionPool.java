package util.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Vector;

public class ConnectionPool {
    private static final Vector<Connection> pool = new Vector<>();
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String username = "root";
    private static final String password = "Hjm@5314";
    private static final String database = "cis";
    private static final int initSize = 8;
    private static final int maxSize = 20;
    private static int usedSize = 0;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            initPool();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC driver.");
        }
    }

    private static void initPool() {
        for (int i = 0; i < initSize; i++) {
            pool.add(createConnection());
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        if (usedSize < initSize) {
            conn = pool.get(0);
            usedSize++;
            pool.remove(0);
        } else if (usedSize < maxSize) {
            conn = createConnection();
            if (conn != null) {
                usedSize++;
            }
        }
        return conn;
    }

    public static Connection createConnection() throws NullPointerException {
        try {
            return DriverManager.getConnection(url + database, username, password);
        } catch (SQLException e) {
            throw new NullPointerException("Failed to create connection.");
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void returnConnection(Connection conn) {
        if (pool.size() < initSize) {
            pool.add(conn);
        } else {
            closeConnection(conn);
        }
        --usedSize;
    }
}
