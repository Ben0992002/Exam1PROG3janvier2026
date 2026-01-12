package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/mini_football_db";
    private static final String USER = "football_manager";
    private static final String PASSWORD = "password123";

    private static Connection connection = null;

    public static Connection getDBConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Chargement du driver PostgreSQL (Optionnel sur les versions récentes mais plus sûr)
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion réussie à la base de données !");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver PostgreSQL non trouvé : " + e.getMessage());
            }
        }
        return connection;
    }
}
