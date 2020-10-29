package blInterface;

import java.util.*;


public class App
{
    int AppID;
    int Version;
    List<Integer> Ratings;
    int avgRatings;
    List<String> Reviews;

    public App(int ID, int Ver, List<Integer> UserRatings, int aRating, List<String> UserReviews )
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
