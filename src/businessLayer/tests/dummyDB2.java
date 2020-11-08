package businessLayer.tests;

import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.concurrent.Callable;

import javax.naming.spi.DirStateFactory.Result;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.DatabaseMetaData;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import dbaseInterface.*;

public class dummyDB2 implements dbaseInterface.userInterface {

    private Connection conn = null;

    public dummyDB2() {
        // try {

        // String dbURL = "jdbc:sqlserver://localhost\\appStore:1433";
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

    }

    private void createConnection() {
        String dbURL = "jdbc:sqlserver://localhost\\DatabaseName=appStore:1433";
        String username = "sa";
        String pass = "Yibz6969";
        try {
            conn = DriverManager.getConnection(dbURL, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(dbaseInterface.userDetails user) throws SQLException {

        this.createConnection();
        Date dob = Date.valueOf(user.DOB);
        CallableStatement cs = conn.prepareCall("{call appStore.dbo.add_user(?,?,?,?)}");
        cs.setString(1, user.Name);
        cs.setString(2, user.email);
        cs.setString(3, user.password);
        cs.setDate(4, dob);
        cs.execute();
    }

    public userDetails getUserDetails(int userID) {
        this.createConnection();
        try {
            CallableStatement cs;
            userDetails resultObj = new userDetails();
            cs = conn.prepareCall("{call appStore.dbo.get_User_Details(?)");
            cs.setInt(1, userID);
            ResultSet rs = cs.executeQuery();
            if (rs != null) {
                resultObj.userID = rs.getInt(1);
                resultObj.Name = rs.getString(2);
                resultObj.DOB = rs.getDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                resultObj.password = rs.getString(4);
                resultObj.email = rs.getString(5);
            }
            return resultObj;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    // if user does not exist throw invalidargumentexception
    public void removeUser(int userID) {
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.remove_user(?)}");
            cs.setInt(1, userID);
            cs.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    public boolean checkEmailExists(String email) {
        try {
            this.createConnection();
            CallableStatement cs = conn.prepareCall("{call appStore.dbo.check_email_exist(?,?)}");
            cs.setString(1, email);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();

            if (cs.getInt(2) == 1) {
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // -1 if not installed
    public int checkAppInstall(int appID, int userID) {
        return -1;
    }

    public boolean checkUserExists(int userID) {
        try {
            this.createConnection();
            CallableStatement cs = conn.prepareCall("{call appStore.dbo.check_User_Exist(?,?)}");
            cs.setInt(1, userID);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();

            if (cs.getInt(2) == 1) {
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

}
