package jasper.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnUtils {

    public static Connection getOracleConnection()
            throws ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String sid = "db11g";
        String userName = "ejemplooraclesefarcol";
        String password = "12345";

        return getOracleConnection(hostName, sid, userName, password);
    }

    public static Connection getOracleConnection(String hostName, String sid,
            String userName, String password) throws ClassNotFoundException,
            SQLException {

        // Declare el Driver para ORACLE DB
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}
