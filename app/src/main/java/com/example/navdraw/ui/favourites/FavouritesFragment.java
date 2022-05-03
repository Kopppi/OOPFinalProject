package com.example.navdraw.ui.favourites;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.navdraw.MainActivity;
import com.example.navdraw.R;

import com.example.navdraw.TaxList;
import com.example.navdraw.databinding.FragmentFavouritesBinding;
import com.example.navdraw.ui.company.CompanyFragment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    ListView listView;
    TextView textView;
    ListAdapter adapter;
    Button button;
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
        button = root.findViewById(R.id.button);
        Favourites = FavouritesList.getInstance().getFavouritesSample();
        names = FavouritesList.getInstance().getNames();


        //Adding adapter to ArrayList names
        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_layout, names);
        listView.setAdapter(adapter);
        setTextView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFavourites();
            }
        });
        return root;
    }

    //This function clears organized.csv and favourites.csv files
    public void clearFavourites(){
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(getActivity().getApplicationContext().openFileOutput("organized.csv", Context.MODE_PRIVATE));
            osw.write("");
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStreamWriter osw2 = null;
        try {
            osw2 = new OutputStreamWriter(getActivity().getApplicationContext().openFileOutput("favourites.csv", Context.MODE_PRIVATE));
            osw2.write("");
            osw2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getActivity().recreate();
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