package com.example.maxime.projetparking.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Ticket;
import com.example.maxime.projetparking.entity.Voiture;

import java.util.List;
import java.util.Objects;

/**
 * Created by Belal on 18/09/16.
 */


public class TicketFragment extends Fragment {

    ListView mListView;
    String user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        return inflater.inflate(R.layout.fragment_menu_1, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Gestion ticket");
        Intent intent = getActivity().getIntent();
        user = intent.getStringExtra("user");

        List<Ticket> tickets = Ticket.find(Ticket.class,"id_user = ?",user );
        mListView = (ListView) view.findViewById(R.id.listView);
        TicketAdapter adapter = new TicketAdapter(getActivity().getBaseContext(), tickets);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),InfoTicket.class);
                intent.putExtra("idTicket", ((TextView)view.findViewById(R.id.id)).getText().toString());
                startActivityForResult(intent,1);
            }
        });



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddTicket.class);
                intent.putExtra("user",user);
                startActivityForResult(intent,1);
            }
        });



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Ticket> tickets = Ticket.listAll(Ticket.class);
        TicketAdapter adapter = new TicketAdapter(getActivity().getBaseContext(), tickets);
        mListView.setAdapter(adapter);
    }
}
