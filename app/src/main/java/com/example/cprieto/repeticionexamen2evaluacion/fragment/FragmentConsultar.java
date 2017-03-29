package com.example.cprieto.repeticionexamen2evaluacion.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cprieto.repeticionexamen2evaluacion.MainActivity;
import com.example.cprieto.repeticionexamen2evaluacion.R;
import com.example.cprieto.repeticionexamen2evaluacion.bbdd.DBAdapter;
import com.example.cprieto.repeticionexamen2evaluacion.datos.Contacto;
import com.example.cprieto.repeticionexamen2evaluacion.lista.CustomAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConsultar extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lsvContactos;

    private ArrayList<Contacto> listaContactos = new ArrayList<>();

    private CustomAdapter adapter;

    public FragmentConsultar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultar, container, false);

        //Buscamos la lista
        lsvContactos = (ListView) view.findViewById(R.id.lsvContactos);

        //Inicializamos el adapter de la lista
        adapter = new CustomAdapter(getActivity(), listaContactos);

        this.getContactos();

        lsvContactos.setOnItemClickListener(this);

        return view;
    }

    /**
     * Metodo donde obtenemos todos los nombres de la BBDD
     */
    private void getContactos() {

        listaContactos.clear();
        DBAdapter db = new DBAdapter(getActivity());
        db.abrirBD();

        Cursor c = db.getContactos();

        //Va recorriendo el cursor en busca de datos
        while (c.moveToNext()) {

            int id = c.getInt(0);
            String nombre = c.getString(1);
            String telefono = c.getString(2);
            String edad = c.getString(3);
            String sexo = c.getString(4);

            Contacto contacto = new Contacto();
            contacto.setId(id);
            contacto.setNombre(nombre);
            contacto.setTelefono(telefono);
            contacto.setEdad(edad);
            contacto.setSexo(sexo);

            listaContactos.add(contacto);

        }

        db.cerrarBD();

        //Añadimos a la lista
        lsvContactos.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> lista, View view, int position, long id) {

        //Al hacer click en un elemento de la lista llamamos a un metodo que se encuentra en el MainActivity y le pasamos la posicion
        ((MainActivity) getActivity()).contactoClick((Contacto) lista.getItemAtPosition(position));

    }

}
