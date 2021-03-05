package com.revature.utility;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBConnection {
    private static Logger log = Logger.getLogger(JDBConnection.class);

    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.warn("WARN: Program failed to load the Postgres database driver.");
            e.printStackTrace();
        }

        Properties props = new Properties();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Connection conn = null;

        try {
            props.load(loader.getResourceAsStream("connection.properties"));
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                log.warn("unable to obtain a connection to the database");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            log.warn("WARN: Failed to load the credentials for Postgres database.");
        }
        //TODO Do not forget to close your JDBC resources with a finally block
        log.info("Successful: JDBC Connection was successful.");
        return conn;
    }


}
