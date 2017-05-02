package com.example.maxime.projetparking.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.maxime.projetparking.Inscription;
import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Ticket;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Maxime on 15/04/2017.
 */

public class TicketAdapter extends ArrayAdapter<Ticket> {


    public TicketAdapter(Context context, List<Ticket> tickets){
        super(context,0,tickets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_ticket,parent, false);
        }

        TicketViewHolder viewHolder = (TicketViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TicketViewHolder();
            viewHolder.immatriculation = (TextView) convertView.findViewById(R.id.immatriculaion);
            viewHolder.dateDemande = (TextView) convertView.findViewById(R.id.dateDebut);
            viewHolder.dureeStationementInitiale = (TextView) convertView.findViewById(R.id.dureePrevu);
            viewHolder.id = (TextView) convertView.findViewById(R.id.id);

            convertView.setTag(viewHolder);
        }
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
        final Ticket ticket = getItem(position);
        viewHolder.immatriculation.setText("Immatriculation : "+ticket.getImmatriculation());
        viewHolder.dateDemande.setText("Date : "+shortDateFormat.format(ticket.getDateDebut()));
        viewHolder.dureeStationementInitiale.setText("Durée : "+ticket.getDureeStationementInitiale());
        viewHolder.id.setText("Id : "+ticket.getId());

        return convertView;
    }

    private class TicketViewHolder {
        TextView immatriculation, dateDemande,dureeStationementInitiale, id;
    }
}
