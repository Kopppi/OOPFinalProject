package com.example.navdraw;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TaxList {
    private static TaxList taxList = new TaxList();

        public static TaxList getInstance() {
            return taxList;
        }

    private List<TaxSample> TaxSamples;
    private ArrayList<String> names;


    private TaxList() {
        TaxSamples = new ArrayList<>();
        names = new ArrayList<>();

        String file = "raw/verotiedot10.csv";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
        //InputStream is2 = context.openRawResource(R.raw.verotiedot10);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // Get rid of tittle line
        try {
            String extra = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                // Split by ';'
                line = line.replaceAll(",", ".");
                String[] tokens = line.split(";");

                // Read the data
                TaxSample sample = new TaxSample();
                sample.setID(tokens[1]);
                sample.setName(tokens[2]);
                sample.setLocation(tokens[3]);
                sample.setTaxedIncome(Double.parseDouble(tokens[4]));
                sample.setPayedTax(Double.parseDouble(tokens[5]));
                names.add(tokens[2]);
                TaxSamples.add(sample);

                Log.d("MyActivity", "Just created "+ sample);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data on line " + line, e);
            e.printStackTrace();
        }
    }

    public List<TaxSample> getTaxSamples() {
        return TaxSamples;
    }

    public ArrayList<String> getNames() {
        return names;
    }
}
