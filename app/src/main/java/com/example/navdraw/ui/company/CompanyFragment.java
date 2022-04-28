package com.example.navdraw.ui.company;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.navdraw.R;

import com.example.navdraw.TaxList;
import com.example.navdraw.TaxSample;
import com.example.navdraw.databinding.FragmentFavouritesBinding;

import java.util.List;

public class CompanyFragment extends Fragment {

    int a;
    TextView textViewName;
    TextView textViewTaxed;
    TextView textViewYID;
    TextView textViewTax;
    private List<TaxSample> TaxSamples;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        textViewName = rootView.findViewById(R.id.textViewName);
        textViewTaxed = rootView.findViewById(R.id.textViewTaxed);
        textViewYID = rootView.findViewById(R.id.textViewYID);
        textViewTax = rootView.findViewById(R.id.textViewTax);

        a = getArguments().getInt("a");

        setData();
        return rootView;
    }

    public void setData() {
        TaxSamples = TaxList.getInstance().getTaxSamples();
        textViewName.setText(TaxSamples.get(a).getName());
        textViewYID.setText(TaxSamples.get(a).getID());
        textViewTax.setText(String.valueOf(TaxSamples.get(a).getTaxedIncome()));
        textViewTaxed.setText(String.valueOf(TaxSamples.get(a).getPayedTax()));
    }
}
