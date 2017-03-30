package com.example.cprieto.repeticionexamen2evaluacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cprieto.repeticionexamen2evaluacion.datos.Contacto;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentAniadir;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentConsultar;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentEditar;
import com.example.cprieto.repeticionexamen2evaluacion.fragment.FragmentListadoOpciones;

public class MainActivity extends AppCompatActivity {

    FragmentListadoOpciones fOpciones = new FragmentListadoOpciones();
    FragmentAniadir fAniadir = new FragmentAniadir();
    FragmentConsultar fConsultar = new FragmentConsultar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cargamos el fragment en funcion si es modo movil o tablet
        if (findViewById(R.id.contenedorFragment) != null) {

            fOpciones.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment, fOpciones).commit();
        } else {

            fOpciones.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.contenedorEstatico, fOpciones).commit();

        }

    }

    /**
     * Metodo que cambia el fragment en funcion del elemento de la lista que hemos pulsado
     *
     * @param opcion
     */
    public void opcionClick(String opcion) {

        switch (opcion) {
            case "Añadir":
                if (findViewById(R.id.contenedorFragment) != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fAniadir).addToBackStack(null).commit();
                else
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDinamico, fAniadir).addToBackStack(null).commit();
                break;

            case "Consultar":
                if (findViewById(R.id.contenedorFragment) != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fConsultar).addToBackStack(null).commit();
                else
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDinamico, fConsultar).addToBackStack(null).commit();
                break;
        }

    }

    /**
     * Metodo que al pulsar el boton Aceptar o borrar del FragmentEditar vuelva a cargar el fragmentConsultar
     *
     * @param opcionBoton
     */
    public void recargarFragment(String opcionBoton) {


        if (opcionBoton.equals("ACEPTAR") || opcionBoton.equals("BORRAR"))
            if (findViewById(R.id.contenedorFragment) != null)
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fConsultar).addToBackStack(null).commit();
    }

    /**
     * Metodo que llama al Fragment editar al hacer click en un elemento de la lista
     *
     * @param c
     */
    public void contactoClick(Contacto c) {

        FragmentEditar fEditar = new FragmentEditar();

        //Creamos y enviamos un Bundle con el id del contacto al que hacemos click para recogerlo en la clase FragmentEditar
        Bundle bundle = new Bundle();
        bundle.putInt("id", c.getId());
        fEditar.setArguments(bundle);

        if (findViewById(R.id.contenedorFragment) != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, fEditar).addToBackStack(null).commit();

    }

}
