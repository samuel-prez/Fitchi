package jasper.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnUtils {

    public static Connection getSQLServerConnection_SQLJDBC()
            throws ClassNotFoundException, SQLException {
        String hostName = "172.16.1.5";
        String sqlInstanceName = "SQLEXPRESS";
        String database = "sefarcol";
        String userName = "sefarcolweb";
        String password = "Sefarcol2016";

        return getSQLServerConnection_SQLJDBC(hostName, sqlInstanceName,
                database, userName, password);
    }

    public static Connection getSQLServerConnection_JTDS() throws SQLException,
            ClassNotFoundException {
        String hostName = "learningsql";
        String sqlInstanceName = "SQLEXPRESS";
        String database = "simplehr";
        String userName = "sa";
        String password = "12345";

        return getSQLServerConnection_JTDS(hostName, sqlInstanceName, database,
                userName, password);
    }

    private static Connection getSQLServerConnection_SQLJDBC(String hostName,
            String sqlInstanceName, String database, String userName,
            String password) throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
                + ";instance=" + sqlInstanceName + ";databaseName=" + database;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

    private static Connection getSQLServerConnection_JTDS(String hostName,
            String sqlInstanceName, String database, String userName,
            String password) throws ClassNotFoundException, SQLException {

        Class.forName("net.sourceforge.jtds.jdbc.Driver");

        String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/"
                + database + ";instance=" + sqlInstanceName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

}
