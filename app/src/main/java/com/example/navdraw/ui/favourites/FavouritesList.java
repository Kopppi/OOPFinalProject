package com.example.navdraw.ui.favourites;


import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.navdraw.R;
import com.example.navdraw.TaxSample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FavouritesList {
    private static FavouritesList favouritesList = null;

    public static FavouritesList getInstance() {
        if (favouritesList == null) {
            favouritesList = new FavouritesList();
        }
        return favouritesList;
    }

    private List<FavouritesSample> FavouritesSample;
    private ArrayList<String> names;

    public void setArrays(Context context){
        FavouritesSample = new ArrayList<>();
        names = new ArrayList<>();

        try (InputStream is = new FileInputStream(context.getFilesDir() + "/organized.csv"))
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    // Split by ';'
                    line = line.replaceAll("�", "ä");
                    line = line.replaceAll(",", ".");
                    line = line.replaceAll("�", "ä");
                    String[] tokens = line.split(";");

                    // Read the data
                    FavouritesSample sample = new FavouritesSample();
                    sample.setID(tokens[0]);
                    sample.setLocation(tokens[1]);
                    sample.setName(tokens[2]);
                    sample.setTaxedIncome(Double.parseDouble(tokens[3]));
                    sample.setPayedTax(Double.parseDouble(tokens[4]));
                    names.add(tokens[2]);
                    FavouritesSample.add(sample);

                    Log.d("MyActivity", "Just created " + sample);
                }
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data on line " + line, e);
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FavouritesList() {
    }

    public List<FavouritesSample> getFavouritesSample() {
        return FavouritesSample;
    }

    public ArrayList<String> getNames() {
        return names;
    }
}

