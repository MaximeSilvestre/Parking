package com.example.maxime.projetparking.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Voiture;

import java.util.List;

/**
 * Created by Maxime on 15/04/2017.
 */

public class VoitureAdapter extends ArrayAdapter<Voiture> {


    public VoitureAdapter(Context context, List<Voiture> voitures){
        super(context,0,voitures);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_car,parent, false);
        }

        CarViewHolder viewHolder = (CarViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CarViewHolder();
            viewHolder.Immatriculation = (TextView) convertView.findViewById(R.id.immatriculaion);
            viewHolder.Marque = (TextView) convertView.findViewById(R.id.marque);
            viewHolder.Model = (TextView) convertView.findViewById(R.id.model);
            convertView.setTag(viewHolder);
        }

        Voiture voiture = getItem(position);
        viewHolder.Immatriculation.setText("Immatriculation : "+voiture.getImmatriculation());
        viewHolder.Marque.setText("Marque : "+voiture.getMarque());
        viewHolder.Model.setText("Model : "+voiture.getModele());


        return convertView;
    }

    private class CarViewHolder {
        TextView Immatriculation, Marque,Model,Mail;
    }
}
