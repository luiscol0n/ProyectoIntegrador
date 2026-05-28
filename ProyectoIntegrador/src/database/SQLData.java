package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class SQLData {
	
    private static final String URL = "jdbc:mariadb://192.168.34.191:3306/EstacionMeteorologica";
    private static final String USER = "grupo1s";
    private static final String PASS = "g1s";

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
        	} catch (ClassNotFoundException e) {
        		System.err.println("[-] No se encontró el Driver de MariaDB: " + e.getMessage());
        	}
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public static void insertarLectura(

            int estacionId,
            double precipitacion,
            double direccionViento,
            double velocidadViento

    ) {

        try {

            Connection conn = getConexion();

            String sql =
                    "INSERT INTO lecturas " +
                    "(estacion_id, precipitacion, direccion_viento, velocidad_viento) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, estacionId);

            stmt.setDouble(2, precipitacion);

            stmt.setDouble(3, direccionViento);

            stmt.setDouble(4, velocidadViento);

            stmt.executeUpdate();

            System.out.println(
                    "Lectura guardada"
            );

            stmt.close();

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}