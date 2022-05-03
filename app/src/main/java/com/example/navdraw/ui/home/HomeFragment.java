package com.example.navdraw.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.navdraw.ui.movie.AllMovies;
import com.example.navdraw.ui.movie.AllTheaters;
import com.example.navdraw.R;
import com.example.navdraw.TaxList;
import com.example.navdraw.TaxSample;
import com.example.navdraw.databinding.FragmentHomeBinding;
import com.example.navdraw.ui.company.CompanyFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private double totalTaxAmount = 0;
    private double averageTaxAmount = 0;
    TextView textViewTotalTax;
    TextView textViewAverageTax;
    TextView textViewAd;
    EditText editTextFilter;
    ListView listView;
    ArrayAdapter adapter;
    private ArrayList<String> names;
    private List<TaxSample> TaxSamples;
    AllTheaters theaters = new AllTheaters();
    AllMovies films = new AllMovies();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TaxList taxList = TaxList.getInstance();
        textViewTotalTax = (TextView) root.findViewById(R.id.textViewTotalTax);
        textViewAverageTax = (TextView) root.findViewById(R.id.textViewaverageTax);
        editTextFilter = (EditText) root.findViewById(R.id.editTextSearch);
        listView = (ListView) root.findViewById(R.id.listViewID);
        textViewAd = (TextView)root.findViewById(R.id.ad);
        names = TaxList.getInstance().getNames();


        //Adding adapter to ArrayList names
        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_layout, names);
        listView.setAdapter(adapter);

        //Adding filter to enable search from ListView
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (HomeFragment.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get the selected company name to string and send it to another fragment
                String selectedFromList = (listView.getItemAtPosition(i).toString());
                Fragment fragment = null;
                Bundle bdl=new Bundle();
                bdl.putString("a",selectedFromList);
                try {
                    fragment = (Fragment) CompanyFragment.class.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                fragment.setArguments(bdl);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(((ViewGroup)getView().getParent()).getId(), fragment, null);
                // Commit the transaction
                transaction.commit();

            }
        });
        calculator();

        // Movie advertisement functions

        theaters.readXML();
        films.readXML();
        Random rand = new Random();
        int randNum = rand.nextInt(5);
        textViewAd.setText("Advertisement: Finnkino currently playing movies: " + films.getArray().get(randNum).getTitle());
        return root;
    }

    //Counts total taxes paid and average amount of taxes paid
    public void calculator(){
        TaxSamples = TaxList.getInstance().getTaxSamples();
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