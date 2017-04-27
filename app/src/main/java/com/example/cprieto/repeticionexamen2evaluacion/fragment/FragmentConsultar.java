package com.example.cprieto.repeticionexamen2evaluacion.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cprieto.repeticionexamen2evaluacion.Interfaces.OnListadoContactosSelected;
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

    private OnListadoContactosSelected mListener;

    int contextMenuIndexClicked = -1;


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

        /*
        ESTO ES UNA FORMA DE HACER EL CLICK LARGO DE LA LISTA
         */
        /*lsvContactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog diaBox = ConfirmacionBorrar();
                diaBox.show();

                return false;
            }
        });*/

        //Añadimos el context menu
        registerForContextMenu(lsvContactos);

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
            String edad = c.getString(2);
            String telefono = c.getString(3);
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
        mListener.contactoClick((Contacto) lista.getItemAtPosition(position));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListadoContactosSelected) {
            mListener = (OnListadoContactosSelected) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListadoContactosSelected");
        }
    }

    private void borrar(int idSele) {

        DBAdapter db = new DBAdapter(getActivity());
        db.abrirBD();

        boolean deleted = db.borrarContacto(idSele);

        //Si se ha borrado bien
        if (deleted) {

            Toast.makeText(getActivity(), "Borrado con éxito", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getActivity(), "No se puede borrar", Toast.LENGTH_SHORT).show();

        }

        db.cerrarBD();

    }

    /*private AlertDialog ConfirmacionBorrar() {
        AlertDialog confirmacion = new AlertDialog.Builder(getActivity())
                //set message, title, and icon
                .setTitle("BORRAR")
                .setMessage("¿Seguro que quieres borrar el contacto?")
                //.setIcon(R.drawable.delete)

                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        //Llamamos al metodo para borrar el contacto
                        borrar();

                        dialog.dismiss();
                    }

                })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return confirmacion;
    }*/

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_click_lista, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_borrar_dialog:

                Toast.makeText(getActivity(), "Holaa", Toast.LENGTH_SHORT).show();

                break;
        }
        return true;
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_click_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Cogemos el index context menu click
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        contextMenuIndexClicked = info.position;

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int idSele = info.position + 1;

        switch (item.getItemId()) {
            case R.id.context_borrar_dialog:

                //Toast.makeText(getActivity(), "Holaa: " + idSele, Toast.LENGTH_SHORT).show();

                borrar(idSele);

                break;
        }
        return true;

    }
}