package dbaseInterface;

import java.sql.Date;
import java.time.LocalDate;
import java.util.jar.Attributes.Name;

public class userDetails {
    public String Name;
    public int userID;
    public LocalDate DOB;
    public String password;
    public String email;

    public userDetails() {
        Name = null;
        userID = -1;
        DOB = null;
        email = null;
    }

    public userDetails(String Name, int userID, LocalDate DOB, String email, String password) {
        this.Name = Name;
        this.userID = userID;
        this.DOB = DOB;
        this.email = email;
        this.password = password;
    }
}
