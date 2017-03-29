package com.example.cprieto.repeticionexamen2evaluacion.lista;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cprieto.repeticionexamen2evaluacion.R;
import com.example.cprieto.repeticionexamen2evaluacion.datos.Contacto;

import java.util.ArrayList;

/**
 * Created by Carlos Prieto on 21/03/2017.
 */

public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Contacto> contactos;

    public CustomAdapter(Context context, ArrayList<Contacto> contactos) {
        super(context, R.layout.fila_lista, contactos);

        this.context = context;
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            //inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.fila_lista, parent, false);
        }

        //Cogemos el nombre del contacto
        TextView txtNombre = (TextView) convertView.findViewById(R.id.txvNombre);
        txtNombre.setText(contactos.get(position).getNombre());

        return convertView;
    }

}
