package database;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {
        System.out.println("[*] Intentando conectar al servidor MariaDB de la universidad...");
        
        try {
            // 1. Intentamos obtener la conexión usando tu clase auxiliar
            Connection conn = SQLData.getConexion();
            
            if (conn != null && !conn.isClosed()) {
                System.out.println("\n=================================================");
                System.out.println("[¡ÉXITO!] Conexión establecida correctamente.");
                System.out.println("El Driver de MariaDB y las credenciales funcionan.");
                System.out.println("=================================================");
                
                // Cerramos la conexión de prueba inmediatamente
                conn.close();
            }
            
        } catch (SQLException e) {
            System.err.println("\n[-] ERROR: No se pudo conectar a la base de datos.");
            System.err.println("Código de error SQL: " + e.getErrorCode());
            System.err.println("Detalle del problema: " + e.getMessage());
            
            if (e.getMessage().contains("Access denied")) {
                System.err.println("\n[Sugerencia] El usuario o la contraseña 'grupo1s' están mal creados en MariaDB.");
            } else if (e.getMessage().contains("Connection refused") || e.getMessage().contains("timed out")) {
                System.err.println("\n[Sugerencia] El firewall de Ubuntu o el bind-address están bloqueando el puerto 3306.");
            }
        }
    }
}