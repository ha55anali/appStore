package dbaseInterface;

import java.util.*;

public class appDetails
{
    public int AppID;
    public String Name;
    public String Description;
    public int Version;
    public List<Integer> Ratings;
    public int avgRatings;
    public List<String> Reviews;

    public appDetails(int ID, String AppName, String AppDescription, int Ver, List<Integer> UserRatings, int aRating, List<String> UserReviews )
    {
        AppID=ID;
        Name=AppName;
        Description= AppDescription;
        Version=Ver;
        Ratings=new ArrayList<Integer>();
        for(int i : UserRatings)
            Ratings.add(i);
        avgRatings=aRating;
        Reviews=new ArrayList<String>();
        for(String i : UserReviews)
            Reviews.add(i);
    }

}
