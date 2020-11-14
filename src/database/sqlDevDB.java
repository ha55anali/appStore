package database;

import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

public class sqlDevDB implements dbaseInterface.devInterface {

    // private connection object. need open everytime function has to be called
    private Connection conn = null;

    public sqlDevDB() {
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
    public userDetails getDevDetails(int devID) {
        this.createConnection();
        try {
            CallableStatement cs;
            userDetails resultObj = new userDetails();
            cs = conn.prepareCall("{call appStore.dbo.getdevDetails(?)}");
            cs.setInt(1, devID);
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
    public boolean checkDevExists(int devID) {
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.check_Dev_Exist(?,?)}");
            cs.setInt(1, devID);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();

            return cs.getInt(2) == 1 ? true : false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;

    }

    // appID will be assigned by database. pass dummy devID
    // will return devID assigned to new dev
    // NOTE: Complete
    public int addUser(userDetails dev) {
        this.createConnection();
        // convert LocalDate to Date object for SQL
        Date dob = Date.valueOf(dev.DOB);
        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.add_user_dev(?,?,?,?)}");
            cs.setString(1, dev.Name);
            cs.setString(2, dev.email);
            cs.setString(3, dev.password);
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
    public void removeUser(int devID) {
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.remove_user_dev(?)}");
            cs.setInt(1, devID);
            cs.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public void addApp(int devID, int appID) {
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.add_App_dev(?,?)}");
            cs.setInt(1, devID);
            cs.setInt(2, appID);
            cs.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public void removeApp(int devID, int appID) {
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.remove_App_Dev(?,?)}");
            cs.setInt(1, devID);
            cs.setInt(2, appID);
            cs.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public boolean checkAppDev(int devID, int appID) {
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.check_app_dev(?,?,?)}");
            cs.setInt(1, devID);
            cs.setInt(2, appID);
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
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.check_Email_Exists_dev(?,?)}");
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
    public int authenticateUser(String email, String password) {
        this.createConnection();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.authenticate_User_dev(?,?,?)}");
            cs.setString(1, email);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();

            return cs.getInt(3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }

    // NOTE: Complete
    public List<Integer> getDevApps(int devID) {

        this.createConnection();
        List<Integer> appList = new ArrayList<Integer>();

        CallableStatement cs;
        try {
            cs = conn.prepareCall("{call appStore.dbo.get_Dev_Apps(?)}");
            cs.setInt(1, devID);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                appList.add(rs.getInt(1));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appList;

    }
}
