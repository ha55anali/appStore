package blInterface;

import java.time.LocalDate;

public class userDetails {
    public String Name;
    public int userID;
    public LocalDate DOB;
    public String password;
    public String email;

    public userDetails(String Name, int userID, LocalDate DOB, String email, String password) {
        this.Name = Name;
        this.userID = userID;
        this.DOB = DOB;
        this.email = email;
        this.password = password;
    }
}
