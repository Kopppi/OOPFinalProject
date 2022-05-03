package com.example.navdraw.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.navdraw.R;

import com.example.navdraw.TaxList;
import com.example.navdraw.databinding.FragmentFavouritesBinding;
import com.example.navdraw.ui.company.CompanyFragment;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    ListView listView;
    TextView textView;
    ListAdapter adapter;
    private List<FavouritesSample> Favourites;
    private ArrayList<String> names;

    private FragmentFavouritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavouritesViewModel favouritesViewModel =
                new ViewModelProvider(this).get(FavouritesViewModel.class);

        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textView = root.findViewById(R.id.text_favourites);

        listView = root.findViewById(R.id.listView);

        Favourites = FavouritesList.getInstance().getFavouritesSample();
        names = FavouritesList.getInstance().getNames();

        //Adding adapter to ArrayList names
        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_layout, names);
        listView.setAdapter(adapter);
        setTextView();
        return root;
    }


    public void setTextView(){
        textView.setText("Favourites chosen");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}