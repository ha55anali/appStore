package database;

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

public class sqlUserDB implements dbaseInterface.userInterface {

    // private connection object. need open everytime function has to be called
    private Connection conn = null;

    public sqlUserDB() {
        // let it be empty for now
    }

    // helper function used whenever a procedure is used
    private void createConnection() {
        // creates a connection with the db
        // 1433 is the port number. this is sql server's default for linux devices
        // dbURL might vary for windows devices
        String dbURL = "jdbc:sqlserver://localhost\\DatabaseName=appStore:1433";
        // username and pwd of your own sql server acc
        String username = "sa";
        String pass = "Yibz6969";
        try {
            // necessary every time we need to call a procedure
            conn = DriverManager.getConnection(dbURL, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public int addUser(dbaseInterface.userDetails user) {

        this.createConnection();
        // convert LocalDate to Date object for SQL
        Date dob = Date.valueOf(user.DOB);
        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.add_user(?,?,?,?,?)}");
            cs.setString(1, user.Name);
            cs.setString(2, user.email);
            cs.setString(3, user.password);
            cs.setDate(4, dob);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            int userID = cs.getInt(5);

            return userID;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return -1;

    }

    // NOTE: Complete
    public userDetails getUserDetails(int userID) {
        this.createConnection();
        try {
            CallableStatement cs;
            userDetails resultObj = new userDetails();
            cs = conn.prepareCall("{call appStore.dbo.get_User_Details(?)}");
            cs.setInt(1, userID);
            ResultSet rs = cs.executeQuery();
            // rs stores the result of our call
            if (rs.next()) {
                resultObj.userID = rs.getInt(1);
                resultObj.Name = rs.getString(2);
                // convert Date object back to LocalDate
                Date date = rs.getDate(3);
                resultObj.DOB = date.toLocalDate();

                resultObj.password = rs.getString(4);
                resultObj.email = rs.getString(5);
                return resultObj;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // NOTE: Complete
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

    // NOTE: Complete
    public void addInstalledApp(int appID, int userID, int ver) {
        this.createConnection();
        try {
            CallableStatement cs;
            cs = conn.prepareCall("{call appStore.dbo.add_Installed_App(?,?,?)}");
            cs.setInt(1, appID);
            cs.setInt(2, userID);
            cs.setInt(3, ver);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public void removeInstalledApp(int appID, int userID) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.remove_Installed_App(?,?)}");
            cs.setInt(1, appID);
            cs.setInt(2, userID);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public int authenticateUser(String email, String password) {
        this.createConnection();
        int authResult = -1;
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.authenticate_user(?,?,?)}");
            cs.setString(1, email);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();

            authResult = cs.getInt(3);
            return authResult;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authResult;
    }

    // NOTE: Complete
    public void addCard(int userID, int cardNo, int ExpYear) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.add_card(?,?,?)}");
            cs.setInt(1, userID);
            cs.setInt(2, cardNo);
            cs.setInt(3, ExpYear);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public void changeCardDetails(int userID, int cardNo, int NewExpYear) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.change_Card_Details(?,?,?)}");
            cs.setInt(1, userID);
            cs.setInt(2, cardNo);
            cs.setInt(3, NewExpYear);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public void removeCardDetails(int userID, int cardNo) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.remove_card_details(?,?)}");
            cs.setInt(1, userID);
            cs.setInt(2, cardNo);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public boolean checkUserCard(int userID, int cardNo) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.check_user_card(?,?,?)}");
            cs.setInt(1, userID);
            cs.setInt(2, cardNo);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            return cs.getInt(3) == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // NOTE: Complete
    public boolean checkEmailExists(String email) {
        try {
            this.createConnection();
            CallableStatement cs = conn.prepareCall("{call appStore.dbo.check_email_exists(?,?)}");
            cs.setString(1, email);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();

            return cs.getInt(2) == 1 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return false;

    }

    // NOTE: Complete
    // TODO: need to make some changes after usama pushes
    public int checkAppInstall(int appID, int userID) {
        try {
            this.createConnection();
            CallableStatement cs = conn.prepareCall("{call appStore.dbo.check_App_Install(?,?,?)}");
            cs.setInt(1, appID);
            cs.setInt(2, userID);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            return cs.getInt(3);

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return -1;

    }

    // NOTE: Complete
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
