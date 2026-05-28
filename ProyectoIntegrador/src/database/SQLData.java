package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLData {
    // Cambia "IP_DE_TU_SERVIDOR" por la IP real de tu Ubuntu Live Server
    private static final String URL = "jdbc:mariadb://192.168.34.131:3306/EstacionMYeteorologica";
    private static final String USER = "grupo1s";
    private static final String PASS = "g1s";

    public static Connection getConexion() throws SQLException {
        try {
            // Registrar el driver nativo de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[-] No se encontró el Driver de MariaDB: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}