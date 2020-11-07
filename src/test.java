import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.DatabaseMetaData;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import dbaseInterface.userDetails;

// jdbc:sqlserver://server:port;DatabaseName=dbname

class test {
    public static void main(String[] args) {

        blInterface.userInterface user = new businessLayer.User();
        blInterface.userDetails deets = new blInterface.userDetails("Nushi", 1, LocalDate.now(), "nushi@pishi.com",
                "pishipishi");
        user.addUser(deets);

        // Connection conn = null;

        // try {

        // String dbURL = "jdbc:sqlserver://localhost\\master:1433";
        // String user = "sa";
        // String pass = "Yibz6969";
        // conn = DriverManager.getConnection(dbURL, user, pass);
        // if (conn != null) {
        // DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
        // System.out.println("Driver name: " + dm.getDriverName());
        // System.out.println("Driver version: " + dm.getDriverVersion());
        // System.out.println("Product name: " + dm.getDatabaseProductName());
        // System.out.println("Product version: " + dm.getDatabaseProductVersion());
        // }

        // } catch (SQLException ex) {
        // ex.printStackTrace();
        // } finally {
        // try {
        // if (conn != null && !conn.isClosed()) {
        // conn.close();
        // }
        // } catch (SQLException ex) {
        // ex.printStackTrace();
        // }
        // }

        // String dbURL =
        // "jdbc:sqlserver://localhost\\DatabaseName=master;user=sa;password=Yibz6969";
        // Connection conn;
        // try {
        // conn = DriverManager.getConnection(dbURL);
        // } catch (SQLException e1) {
        // e1.printStackTrace();
        // }

        // // if (conn != null) {
        // System.out.println("Connected");
        // }
        // blInterface.userInterface userInt = businessLayer.blFactory.getUserObject();
        // try {
        // blInterface.userDetails u = new blInterface.userDetails("nushiPishi", 1,
        // LocalDate.now(), "nushi@pishi.com",
        // "yibz6969");
        // userInt.addUser(u);
        // userInt.removeUser(u.userID);
        // System.out.println(u.Name);

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
    }

}