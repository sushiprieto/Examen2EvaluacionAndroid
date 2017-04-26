package com.example.cprieto.repeticionexamen2evaluacion;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cprieto.repeticionexamen2evaluacion.Interfaces.OnListadoContactosSelected;
import com.example.cprieto.repeticionexamen2evaluacion.Interfaces.OnListadoOpcionSelected;
import com.example.cprieto.repeticionexamen2evaluacion.bbdd.DBAdapter;
import com.example.cprieto.repeticionexamen2evaluacion.datos.Contacto;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentAniadir;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentConsultar;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentEditar;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentListadoOpciones;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListadoContactosSelected, OnListadoOpcionSelected {

    FragmentListadoOpciones fOpciones = new FragmentListadoOpciones();
    FragmentAniadir fAniadir = new FragmentAniadir();
    FragmentConsultar fConsultar = new FragmentConsultar();
    FragmentEditar fEditar = new FragmentEditar();

    private boolean esMovil = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cargamos el fragment en funcion si es modo movil o tablet
        if (findViewById(R.id.fragmentMovil) != null) {

            fOpciones.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentMovil, fOpciones).commit();
            esMovil = true;

        } else {

            fOpciones.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentTabletIzq, fOpciones).commit();
            esMovil = false;

        }

    }

    /**
     * Metodo que cambia el fragment en funcion del elemento de la lista que hemos pulsado
     *
     * @param opcion
     */
    @Override
    public void opcionClick(String opcion) {

        switch (opcion) {
            case "Añadir":
                if (esMovil)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMovil, fAniadir).addToBackStack(null).commit();
                else
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTabletDer, fAniadir).addToBackStack(null).commit();
                break;

            case "Consultar":
                if (esMovil)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMovil, fConsultar).addToBackStack(null).commit();
                else
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTabletDer, fConsultar).addToBackStack(null).commit();
                break;
        }

    }

    /**
     * Metodo que al pulsar el boton Aceptar o borrar del FragmentEditar vuelva a cargar el fragmentConsultar
     *
     * @param opcionBoton
     */
    public void recargarFragment(String opcionBoton) {

        if (opcionBoton.equals("ACEPTAR") || opcionBoton.equals("BORRAR")){
            if (esMovil)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMovil, fConsultar).addToBackStack(null).commit();
            else
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTabletDer, fConsultar).addToBackStack(null).commit();
        }

    }

    /**
     * Metodo que llama al Fragment editar al hacer click en un elemento de la lista
     *
     * @param c
     */
    @Override
    public void contactoClick(Contacto c) {

        FragmentEditar fEditar = new FragmentEditar();

        //Creamos y enviamos un Bundle con el id del contacto al que hacemos click para recogerlo en la clase FragmentEditar
        Bundle bundle = new Bundle();
        bundle.putInt("id", c.getId());
        fEditar.setArguments(bundle);

        if (esMovil)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMovil, fEditar).addToBackStack(null).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTabletDer, fEditar).addToBackStack(null).commit();

    }

    /*@Override
    public void OnListadoOpcionSelected(int position) {
        if (position == 0) {
            this.getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fEditar).commit();
        } else {
            this.getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fConsultar).commit();
        }
    }*/

    /*@Override
    public void OnListadoPersonasSelected(int position) {
        DBAdapter dbAdapter = new DBAdapter(this);
        List<Contacto> contactos = (List<Contacto>) dbAdapter.getContactos();

        //Contacto c = contactos.get(position);

        this.getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fEditar).commit();
    }*/
}
