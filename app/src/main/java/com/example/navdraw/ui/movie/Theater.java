package com.example.navdraw.ui.movie;

public class Theater {
    public String name;
    private String ID;

    public Theater()
    {

    }

    public Theater(String id, String n)
    {
        ID = id;
        name = n;
    }

    public String toString()
    {
        return name;
    }

    public String getID()
    {
        return ID;
    }
}
