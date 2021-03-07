package com.revature.utility;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBConnection {
    private static Logger log = Logger.getLogger(JDBConnection.class);

    /**
     * getConnection() when invoked, the method will try to establish a connection to the postgres database
     * engine on AWS. It establishes a connection using the Properties class to retrieve the DB credentials
     * from a prop. file. Returns
     * @return Connection conn
     */
    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.warn("WARN: Program failed to load the Postgres database driver.");
            e.printStackTrace();
        }

        Properties credentials = new Properties();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Connection conn = null;

        try {
            credentials.load(loader.getResourceAsStream("connection.properties"));
            String url = credentials.getProperty("url");
            String username = credentials.getProperty("username");
            String password = credentials.getProperty("password");

            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                log.warn("WARN: Unable to obtain a secured connection to the database");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            log.warn("WARN: Failed to load the credentials for Postgres database.");
        }

        log.info("Successful: JDBC Connection was established.");

        return conn;
    }
}
