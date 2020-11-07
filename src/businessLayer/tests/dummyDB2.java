package businessLayer.tests;

import java.time.LocalDate;
import java.sql.Date;
import java.util.concurrent.Callable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import dbaseInterface.*;

public class dummyDB2 implements dbaseInterface.userInterface {

    private Connection conn = null;

    public dummyDB2() {
        try {

            String dbURL = "jdbc:sqlserver://localhost\\appStore:1433";
            String user = "sa";
            String pass = "Yibz6969";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void addUser(dbaseInterface.userDetails user) {
        // if (user.Name == "nushiPishi")
        // return;
        // else
        // throw new IllegalArgumentException("hello");

        try {
            String dbURL = "jdbc:sqlserver://localhost\\appStore:1433";
            String username = "sa";
            String pass = "Yibz6969";
            conn = DriverManager.getConnection(dbURL, username, pass);
            Date dob = Date.valueOf(user.DOB);
            CallableStatement cs = conn.prepareCall("{call signup(?,?,?,?)}");
            cs.setString(1, user.Name);
            cs.setString(2, user.email);
            cs.setString(3, user.password);
            cs.setDate(4, dob);
            // cs.setDate(4, dob);
            cs.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            // System.out.println(e.getMessage());

            e.printStackTrace();
        }

    }

    public userDetails getUserDetails(int userID) {
        if (userID < 100)
            return new userDetails("nushiPishi", 69, LocalDate.now(), "nushi@pishi.com", "yibz6969");
        else
            throw new IllegalArgumentException("alo jee error aya reee");
    }

    // if user does not exist throw invalidargumentexception
    public void removeUser(int userID) {
        if (userID == 69)
            return;
        else
            throw new IllegalArgumentException("alo jee ik hor error aya jay");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void addInstalledApp(int appID, int userID, int ver) {
        if (appID == 69)
            throw new IllegalArgumentException("app already installed dumbo");
        else if (userID != 69)
            throw new IllegalArgumentException("User dont exist dumbas");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void removeInstalledApp(int appID, int userID) {
        if (appID != 69)
            throw new IllegalArgumentException("app not installed dumbo");
    }

    // if user does not exist throw invalidargumentexception
    public void authenticateUser(int userID, String password) {
        if (userID != 69)
            throw new IllegalArgumentException("ni krna stfu");
    }

    public void addCard(int userID, int cardNo, int ExpYear) {
        if (cardNo != 69)
            throw new IllegalArgumentException("Nope cant add that card no");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void setPaymentMethod(int userID, String method) {
        if (method != "dhui")
            throw new IllegalArgumentException("dont accept this method");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void changeCardDetails(int userID, int cardNo, int NewExpYear) {
        if (cardNo != 69)
            throw new IllegalArgumentException("Nope cant change that card's deets");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void removeCardDetails(int userID, int cardNo) {
        if (cardNo != 69)
            throw new IllegalArgumentException("Nope nope nope");
    }

    public boolean checkUserCard(int userID, int cardNo) {
        return true;

    }

    public boolean checkEmailExists(int userID, String email) {
        return true;
    }

    // -1 if not installed
    public int checkAppInstall(int appID, int userID) {
        return -1;
    }

    public boolean checkUserExists(int userID) {
        return false;
    }

}
