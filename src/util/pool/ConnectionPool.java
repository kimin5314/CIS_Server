package util.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.util.Properties;

import java.util.Vector;

public class ConnectionPool {
    static String warFilePath = ConnectionPool.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    private static final String CONFIG_FILE = warFilePath + "config.properties";
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/";
    private static final String DEFAULT_USERNAME = "kimin";
    private static final String DEFAULT_PASSWORD = "Hjm@5314";
    private static final String DEFAULT_DATABASE = "cis";
    private static final int DEFAULT_INIT_SIZE = 8;
    private static final int DEFAULT_MAX_SIZE = 20;
    private static final Vector<Connection> pool = new Vector<>();
    private static String url;
    private static String username;
    private static String password;
    private static String database;
    private static int initSize;
    private static int maxSize;
    private static int usedSize = 0;

    static {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            url = DEFAULT_URL;
            username = DEFAULT_USERNAME;
            password = DEFAULT_PASSWORD;
            database = DEFAULT_DATABASE;
            initSize = DEFAULT_INIT_SIZE;
            maxSize = DEFAULT_MAX_SIZE;
            createDefaultConfigFile();
        }
        url = properties.getProperty("url", DEFAULT_URL);
        username = properties.getProperty("username", DEFAULT_USERNAME);
        password = properties.getProperty("password", DEFAULT_PASSWORD);
        database = properties.getProperty("database", DEFAULT_DATABASE);
        initSize = Integer.parseInt(properties.getProperty("initSize", String.valueOf(DEFAULT_INIT_SIZE)));
        maxSize = Integer.parseInt(properties.getProperty("maxSize", String.valueOf(DEFAULT_MAX_SIZE)));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            initPool();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver.");
        }
    }

    private static void createDefaultConfigFile() {
        Properties properties = new Properties();
        properties.setProperty("url", DEFAULT_URL);
        properties.setProperty("username", DEFAULT_USERNAME);
        properties.setProperty("password", DEFAULT_PASSWORD);
        properties.setProperty("database", DEFAULT_DATABASE);
        properties.setProperty("initSize", String.valueOf(DEFAULT_INIT_SIZE));
        properties.setProperty("maxSize", String.valueOf(DEFAULT_MAX_SIZE));

        try {
            FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
            properties.store(fos, "Database Configuration");
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create config file.");
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
