package com.example.navdraw;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.navdraw.databinding.ActivityMainBinding;
import com.example.navdraw.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    Context context = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Favourite button?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Search, R.id.nav_Settings, R.id.nav_Favourites)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //DO NOT TOUCH THESE, WILL BREAK THE WHOLE APP, FIX!!!!!!
        //context = MainActivity.this;
        //System.out.println(context.getFilesDir());
        //setContentView(R.layout.activity_main);
        //DO NOT TOUCH THESE, WILL BREAK THE WHOLE APP, FIX!!!!!!



        //Writing headline for saved firms file
        /*try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("savedFirms.csv", context.MODE_APPEND));
            ows.append("Y-tunnus;verovelvollisen nimi;verotuskunta;verotettava tulo;maksuunpannut verot yhteensä\n");
            ows.close();
            System.out.println("Headline writen");

        } catch (IOException e){
            Log.e("IOException", "Error in WriteFile");
        }*/
        //TODO testataan toimiiko ilman
        //readTaxData();


    }


    /* TODO testataan toimiiko ilman
    private List<TaxSample> TaxSamples = new ArrayList<>();


    public void writeFile(View v){
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("savedFirms2.csv", context.MODE_APPEND));
            ows.append(TaxSamples.get(1).getID() + ";" + TaxSamples.get(1).getName() + ";" + TaxSamples.get(1).getLocation() + ";" + TaxSamples.get(1).getTaxedIncome() + ";" + TaxSamples.get(1).getPayedTax() + "\n");
            ows.close();
            System.out.println("Written line to csv");

        } catch (IOException e){
            Log.e("IOException", "Error in WriteFile");
        }
    }

    private void readTaxData() {

        InputStream is = getResources().openRawResource(R.raw.verotiedot);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // Get rid of title line
        try {
            String extra = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                // Split by ','
                String[] tokens = line.split(";");

                // Read the data
                TaxSample sample = new TaxSample();
                sample.setID(tokens[1]);
                sample.setName(tokens[2]);
                sample.setLocation(tokens[3]);
                sample.setTaxedIncome(String.valueOf((tokens[4])));
                sample.setPayedTax(String.valueOf((tokens[5])));
                TaxSamples.add(sample);

                Log.d("MyActivity", "Just created "+ sample);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data on line " + line, e);
            e.printStackTrace();
        }
    }
    //Writes saved firms data to csv
    //Add favorites button to this
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

