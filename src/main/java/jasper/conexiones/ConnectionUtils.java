package jasper.conexiones;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {

        return MySQLConnUtils.getMySQLConnection();
    }

    //
    // Test de conexion.
    //
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {

        System.out.println("Get connection ... ");

        Connection conn = ConnectionUtils.getConnection();

        System.out.println("Get connection " + conn);

        System.out.println("Done!");

    }
}
