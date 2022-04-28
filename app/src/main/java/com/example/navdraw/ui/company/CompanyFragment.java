package com.example.navdraw.ui.company;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.navdraw.R;

import com.example.navdraw.TaxList;
import com.example.navdraw.TaxSample;
import com.example.navdraw.databinding.FragmentFavouritesBinding;
import com.example.navdraw.ui.home.HomeFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;

public class CompanyFragment extends Fragment {

    int a;
    TextView textViewName;
    TextView textViewTaxed;
    TextView textViewYID;
    TextView textViewTax;
    Button buttonBack;
    private List<TaxSample> TaxSamples;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        textViewName = rootView.findViewById(R.id.textViewName);
        textViewTaxed = rootView.findViewById(R.id.textViewTaxed);
        textViewYID = rootView.findViewById(R.id.textViewYID);
        textViewTax = rootView.findViewById(R.id.textViewTax);
        buttonBack = rootView.findViewById(R.id.buttonBack);

        //Gets index from listview on item that has been clicked
        a = getArguments().getInt("a");
        setData();
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
                transaction.replace(((ViewGroup)getView().getParent()).getId(), fragment, null);
                // Commit the transaction
                transaction.commit();
            }
        });

        return rootView;
    }

    //Set data to textviews
    public void setData() {
        TaxSamples = TaxList.getInstance().getTaxSamples();
        textViewName.setText(TaxSamples.get(a).getName());
        textViewYID.setText(TaxSamples.get(a).getID());
        textViewTax.setText(String.valueOf(TaxSamples.get(a).getTaxedIncome()));
        textViewTaxed.setText(String.valueOf(TaxSamples.get(a).getPayedTax()));
    }
}