package com.example.cprieto.repeticionexamen2evaluacion.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.cprieto.repeticionexamen2evaluacion.Interfaces.OnListadoOpcionSelected;
import com.example.cprieto.repeticionexamen2evaluacion.MainActivity;
import com.example.cprieto.repeticionexamen2evaluacion.R;

public class FragmentListadoOpciones extends ListFragment {

    private OnListadoOpcionSelected mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado_opciones, container, false);

        //Creamos el arraylist y le añadimos las opciones
        ArrayList<String> listaOpciones = new ArrayList<>();
        listaOpciones.add("Añadir");
        listaOpciones.add("Consultar");

        //Le aplicamos el adaptador a la lista
        setListAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaOpciones));

        return view;
    }

    @Override
    public void onListItemClick(ListView lista, View v, int position, long id) {

        //Al hacer click en un elemento de la lista llamamos a un metodo que se encuentra en el MainActivity y le pasamos la posicion
        ((MainActivity) getActivity()).opcionClick((String) lista.getItemAtPosition(position));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListadoOpcionSelected) {
            mListener = (OnListadoOpcionSelected) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }
}
