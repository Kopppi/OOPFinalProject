package com.example.navdraw.ui.company;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.navdraw.R;

import com.example.navdraw.TaxList;
import com.example.navdraw.TaxSample;
import com.example.navdraw.ui.favourites.FavouritesList;
import com.example.navdraw.ui.favourites.FavouritesSample;
import com.example.navdraw.ui.home.HomeFragment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CompanyFragment extends Fragment {

    String a;
    int b = 0;
    int position;
    String CSVData = "";
    TextView textViewName;
    TextView textViewTaxed;
    TextView textViewYID;
    TextView textViewTax;
    TextView textViewLocation;
    Button buttonBack;
    Button buttonAddFavourites;
    private List<TaxSample> TaxSamples;
    private List<FavouritesSample> Favourites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);
        TaxSamples = TaxList.getInstance().getTaxSamples();
        Favourites = new ArrayList<>();

        textViewName = rootView.findViewById(R.id.textViewName);
        textViewTaxed = rootView.findViewById(R.id.textViewTaxed);
        textViewYID = rootView.findViewById(R.id.textViewYID);
        textViewTax = rootView.findViewById(R.id.textViewTax);
        textViewLocation = rootView.findViewById(R.id.textViewLocation);
        buttonBack = rootView.findViewById(R.id.buttonBack);
        buttonAddFavourites = rootView.findViewById(R.id.buttonAddFavourites);

        //Gets string from listview on item that has been clicked
        a = getArguments().getString("a");
        //Get position of company in the list
        position = getPosition();
        setData();
        //Setting up a button that takes you to home fragment when clicked
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                try {
                    fragment = (Fragment) HomeFragment.class.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(((ViewGroup) getView().getParent()).getId(), fragment, null);
                // Commit the transaction
                transaction.commit();
            }
        });

        //Adding company to favourites list when button is clicked
        buttonAddFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavouritesSample company = new FavouritesSample();
                company.setID(TaxSamples.get(position).getID());
                company.setName(TaxSamples.get(position).getName());
                company.setLocation(TaxSamples.get(position).getLocation());
                company.setTaxedIncome(TaxSamples.get(position).getTaxedIncome());
                company.setPayedTax(TaxSamples.get(position).getPayedTax());
                Favourites.add(company);
                Toast toast = Toast.makeText(getContext(), TaxSamples.get(position).getName() + " Added to favourites", Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();

                for (int i = 0; i < Favourites.size(); i++) {
                    CSVData = Favourites.get(i).getID() + ";" + TaxSamples.get(position).getLocation() + ";" + Favourites.get(i).getName()
                            + ";" + Favourites.get(i).getPayedTax() + ";" + Favourites.get(i).getTaxedIncome() + "\n";
                }

                try {
                    OutputStreamWriter osw = new OutputStreamWriter(getActivity().getApplicationContext().openFileOutput("favourites.csv", Context.MODE_APPEND));
                    osw.append(CSVData);
                    osw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                writeOrganizedFile();
            }
        });


        return rootView;
    }

    //Check what position in the list the selected company is
    public int getPosition() {
        for (int i = 0; i < TaxSamples.size(); i++) {
            if (TaxSamples.get(i).getName() == a) {
                return i;
            }
        }
        return b;
    }

    //Set data to textviews
    public void setData() {
        textViewName.setText(TaxSamples.get(position).getName());
        textViewLocation.setText(TaxSamples.get(position).getLocation());
        textViewYID.setText(TaxSamples.get(position).getID());
        textViewTax.setText(String.valueOf(TaxSamples.get(position).getTaxedIncome()) + " €");
        textViewTaxed.setText(String.valueOf(TaxSamples.get(position).getPayedTax()) + " €");
    }


    //This method writes organized file
    public void writeOrganizedFile(){
        //Empty file so when we write it there will be no duplicates
        try {
            OutputStreamWriter osw = new OutputStreamWriter(getActivity().getApplicationContext().openFileOutput("organized.csv", Context.MODE_PRIVATE));
            osw.write("");
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Set bufferedReader and string for reading lines
        BufferedReader br = null;
        String line = "";

        // This class implements the Set interface, backed by a hash table (actually a HashMap instance).
        // It makes no guarantees as to the iteration order of the set; in particular, it does not guarantee that the order will
        // remain constant over time. This class permits the null element.
        HashSet<String> allLines = new HashSet<>();

        //reading all the lines from favourites.csv and checking for duplicates
        try {
            br = new BufferedReader(new FileReader(getActivity().getApplicationContext().getFilesDir() + "/favourites.csv"));
            while ((line = br.readLine()) != null) {
                //writing all non duplicates to organized file
                if (allLines.add(line)) {
                    duplicatesLog("Processed line: " + line);
                    try {
                        OutputStreamWriter osw = new OutputStreamWriter(getActivity().getApplicationContext().openFileOutput("organized.csv", Context.MODE_APPEND));
                        osw.append(line + "\n");
                        osw.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (!isNullOrEmpty(line)) {
                    duplicatesLog("--------------- Skipped line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Check if String with spaces is Empty or Null
    public static boolean isNullOrEmpty(String line) {
        if (line != null && !line.trim().isEmpty())
            return false;
        return true;
    }
    // Simple method for system outs
    private static void duplicatesLog(String s) {
        System.out.println(s);
    }
}