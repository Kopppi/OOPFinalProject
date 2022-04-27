package com.example.navdraw.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navdraw.MainActivity;
import com.example.navdraw.R;
import com.example.navdraw.TaxSample;
import com.example.navdraw.databinding.FragmentHomeBinding;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private double totalTaxAmount = 0;
    private double averageTaxAmount = 0;
    TextView textViewTotalTax;
    TextView textViewAverageTax;
    String help;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewTotalTax = (TextView) root.findViewById(R.id.textViewTotalTax);
        textViewAverageTax = (TextView) root.findViewById(R.id.textViewaverageTax);
        readTaxData();
        //TODO fix this shit
        calculator();
        return root;

    } private List<TaxSample> TaxSamples = new ArrayList<>();



    public void writeFile(View v){
        Context context = null;
        // Can't pass this.Activity since we're not in an inner class of MainActivity
        // We need an instance of a context from the Activity
        // Multiple activities so getActivity is necessary instead of this.MainActivity
        context = getActivity();
        System.out.println(context.getFilesDir());
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
                TaxSamples.add(sample);

                Log.d("MyActivity", "Just created "+ sample);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data on line " + line, e);
            e.printStackTrace();
        }
    }
    //Counts total taxes paid and average amount of taxes paid
    public void calculator(){
        double count = 0;
        for (int i = 0; i < TaxSamples.size(); i++) {
            totalTaxAmount += TaxSamples.get(i).getPayedTax();
            count ++;
        }
        averageTaxAmount = (totalTaxAmount / count);
        String totalDouble = String.format("%.1f" + " €", totalTaxAmount);
        String averageDouble = String.format("%.1f" + " €", averageTaxAmount);
        textViewTotalTax.setText(totalDouble);
        textViewAverageTax.setText(averageDouble);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}