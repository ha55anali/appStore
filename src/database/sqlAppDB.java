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

public class sqlAppDB implements dbaseInterface.appInterface {

    // private connection object. need open everytime function has to be called
    private Connection conn = null;

    public sqlAppDB() {
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
    public appDetails getAppDetails(int appID) {
        CallableStatement cs;
        appDetails resultObj = new appDetails(-1, null, null, -1, null, null, -1, null);
        this.createConnection();
        try {
            cs = conn.prepareCall("{call appStore.dbo.getAppDetails(?)}");
            cs.setInt(1, appID);
            ResultSet rs = cs.executeQuery();
            // rs stores the result of our call
            if (rs.next()) {
                resultObj.AppID = rs.getInt(1);
                resultObj.Name = rs.getString(2);
                resultObj.Description = rs.getString(3);
                resultObj.Version = rs.getInt(4);

                resultObj.Category = rs.getString(5);
                resultObj.avgRatings = rs.getInt(6);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cs = conn.prepareCall("{call appStore.dbo.get_reviews(?)}");
            cs.setInt(1, appID);
            ResultSet rs = cs.executeQuery();
            // rs stores the result of our call
            while (rs.next()) {
                resultObj.Reviews.add(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cs = conn.prepareCall("{call appStore.dbo.get_ratings(?)}");
            cs.setInt(1, appID);
            ResultSet rs = cs.executeQuery();
            // rs stores the result of our call
            while (rs.next()) {
                resultObj.Ratings.add(rs.getInt(3));
            }

            return resultObj;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // NOTE: Complete
    public List<Integer> getAllApps() {
        this.createConnection();
        try {
            CallableStatement cs;
            List<Integer> appsList = new ArrayList<Integer>();
            cs = conn.prepareCall("{call appStore.dbo.getAllApps()}");
            ResultSet rs = cs.executeQuery();
            // rs stores the result of our call
            while (rs.next()) {
                appsList.add(rs.getInt(1));
            }

            return appsList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // NOTE: Complete
    public List<Integer> getAppsInCategory(String Category) {
        this.createConnection();
        try {
            CallableStatement cs;
            List<Integer> appsList = new ArrayList<Integer>();
            cs = conn.prepareCall("{call appStore.dbo.getAppsInCatergory(?)}");
            cs.setString(1, Category);
            ResultSet rs = cs.executeQuery();
            // rs stores the result of our call
            while (rs.next()) {
                appsList.add(rs.getInt(1));
            }
            return appsList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // NOTE: Complete
    public void addRating(int appID, int userID, int rating) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.addRating(?,?,?)}");
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public void addComment(int appID, int userID, String comment) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.addComment(?,?,?)}");
            cs.setInt(1, appID);
            cs.setInt(2, userID);
            cs.setString(3, comment);
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public int addApp(appDetails app) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.add_App(?,?,?,?,?)}");
            cs.setString(1, app.Name);
            cs.setFloat(2, app.Version);
            cs.setString(3, app.Category);
            cs.setString(4, app.Description);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();

            int appID = cs.getInt(5);

            return appID;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // NOTE: Complete
    public void updateApp(appDetails app) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.updateApp(?,?,?,?,?)}");
            cs.setInt(1, app.AppID);
            cs.setString(2, app.Name);
            cs.setFloat(3, app.Version);
            cs.setString(4, app.Category);
            cs.setString(5, app.Description);

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public void removeApp(int appID) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.remove_App(?)}");
            cs.setInt(1, appID);

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NOTE: Complete
    public boolean checkAppExists(int appID) {
        this.createConnection();
        try {
            CallableStatement cs;

            cs = conn.prepareCall("{call appStore.dbo.checkAppExists(?,?)}");
            cs.setInt(1, appID);

            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();

            return cs.getInt(2) == 1 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
