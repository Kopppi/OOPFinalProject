package com.example.navdraw.ui.movie;

public class Movie {




    private String title;
    private String theater;
    private String startTime;
    private String auditorium;
    private String length;
    private String rating;
    private String[] var;
    private String[] var2;
    private String[] var3;

    public Movie()
    {

    }

    public Movie(String mTitle, String thtr, String sTime, String room, String lngth, String r)
    {
        title = mTitle;
        theater = thtr;
        auditorium = room;
        length = lngth;
        rating = r;

        var = sTime.split("T");
        var2 = var[0].split("-");
        var3 = var[1].split(":");

        for (int i = 0; i < var2.length; i++)
        {
            startTime = var2[2] + "." + var2[1] + "." + var2[0] + " klo: " + var3[0] + ":" + var3[1];
        }
    }

    public String toString()
    {
        return (title + "\n" + theater + " " + auditorium + "\n" + startTime + "\n" + "Length: " + length + "min, R: " + rating);
    }


    public String getTitle() {
        return title;
    }

}
