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
        blInterface.userDetails deets = new blInterface.userDetails("Nushinishu", 4, LocalDate.now().minusYears(20),
                "nushi@pishimons.com", "pishipishi");
        try {
            System.out.println(user.removeUser(1));
            // deets = user.getUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}