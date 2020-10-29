package dbaseInterface;

import java.util.*;

public class appDetails
{
    int AppID;
    int Version;
    List<Integer> Ratings;
    int avgRatings;
    List<String> Reviews;

    public appDetails(int ID, int Ver, List<Integer> UserRatings, int aRating, List<String> UserReviews )
    {
        AppID=ID;
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
