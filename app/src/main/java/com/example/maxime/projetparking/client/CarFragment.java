package com.example.maxime.projetparking.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Voiture;

import java.util.List;

/**
 * Created by Belal on 18/09/16.
 */


public class CarFragment extends Fragment {

    ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_menu_2, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 2");
        List<Voiture> voitures = Voiture.listAll(Voiture.class);
        mListView = (ListView) view.findViewById(R.id.listView);
        VoitureAdapter adapter = new VoitureAdapter(getActivity().getBaseContext(), voitures);
        mListView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addVoiture);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), addVoiture.class);
                intent.putExtra("user",getArguments().getString("user"));
                startActivityForResult(intent,1);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Voiture> voitures = Voiture.listAll(Voiture.class);
        VoitureAdapter adapter = new VoitureAdapter(getActivity().getBaseContext(), voitures);
        mListView.setAdapter(adapter);
    }


}
