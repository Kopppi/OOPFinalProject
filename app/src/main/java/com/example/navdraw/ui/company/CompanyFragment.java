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

import com.example.navdraw.TaxSample;
import com.example.navdraw.databinding.FragmentFavouritesBinding;

public class CompanyFragment extends Fragment {

    TextView textViewPosition;
    int a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);
        textViewPosition = rootView.findViewById(R.id.textViewCompanyPosition);

        a = getArguments().getInt("a");
        textViewPosition.setText(String.valueOf(a));

        return rootView;
    }
}
