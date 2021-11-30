package modelos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/bd_hotel";  // ruta de la base

    public static synchronized Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);

        } catch (Exception ex) {
            System.out.println("Error en la conexion");
            ex.printStackTrace();
        }
        return cn;
    }

    public static void closeConexion(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sql) {
            System.out.println("Error en la conexion");
            sql.printStackTrace();
        }
    }

}