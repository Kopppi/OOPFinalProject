package com.example.navdraw.ui.company;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.navdraw.R;

import com.example.navdraw.TaxList;
import com.example.navdraw.TaxSample;
import com.example.navdraw.ui.home.HomeFragment;

import java.util.List;

public class CompanyFragment extends Fragment {

    String a;
    int b = 0;
    int position;
    TextView textViewName;
    TextView textViewTaxed;
    TextView textViewYID;
    TextView textViewTax;
    Button buttonBack;
    private List<TaxSample> TaxSamples;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);
        TaxSamples = TaxList.getInstance().getTaxSamples();

        textViewName = rootView.findViewById(R.id.textViewName);
        textViewTaxed = rootView.findViewById(R.id.textViewTaxed);
        textViewYID = rootView.findViewById(R.id.textViewYID);
        textViewTax = rootView.findViewById(R.id.textViewTax);
        buttonBack = rootView.findViewById(R.id.buttonBack);

        //Gets string from listview on item that has been clicked
        a = getArguments().getString("a");
        //Get position of company in the list
        position = getPosition();
        setData();
        //Creating a button and onClickListener to go back to home fragment
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
        textViewYID.setText(TaxSamples.get(position).getID());
        textViewTax.setText(String.valueOf(TaxSamples.get(position).getTaxedIncome()) + " €");
        textViewTaxed.setText(String.valueOf(TaxSamples.get(position).getPayedTax()) + " €");
    }

    public void addToFavourites(){

    }
}