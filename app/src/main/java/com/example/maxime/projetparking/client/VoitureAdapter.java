package com.example.maxime.projetparking.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.maxime.projetparking.Inscription;
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
    public View getView(int position, View convertView, final ViewGroup parent) {

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

        Button btn1 = (Button) convertView.findViewById(R.id.modifierVoiture);
        btn1.setTag(viewHolder);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CarViewHolder mH = (CarViewHolder) v.getTag();

                String immatriculation = mH.Immatriculation.getText().toString();
                Intent intent = new Intent(getContext(), ModifierVoiture.class);
                intent.putExtra("immatriculation",immatriculation);
                Context mContext = parent.getContext();
                mContext.startActivity(intent);
                ((Activity) mContext).finish();


            }
        });
        Button btn2 = (Button) convertView.findViewById(R.id.supprimerVoiture);
        btn2.setTag(viewHolder);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CarViewHolder mH = (CarViewHolder) v.getTag();
                String immatriculation = mH.Immatriculation.getText().toString();
                immatriculation = immatriculation.substring(18,immatriculation.length());
                List<Voiture> voiture = Voiture.find(Voiture.class,"immatriculation = ?",immatriculation);
                Voiture voitureCourrante = voiture.get(0);
                voitureCourrante.delete();
                Context mContext = parent.getContext();
                Intent intent = new Intent(parent.getContext(), ClientActivity.class);
                getContext().startActivity(intent);
                ((Activity) mContext).finish();


            }
        });


        return convertView;
    }

    private class CarViewHolder {
        TextView Immatriculation, Marque,Model,Mail;
    }
}
