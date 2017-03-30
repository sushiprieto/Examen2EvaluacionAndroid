package com.example.cprieto.repeticionexamen2evaluacion.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cprieto.repeticionexamen2evaluacion.R;
import com.example.cprieto.repeticionexamen2evaluacion.bbdd.DBAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAniadir extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText edtNombre, edtEdad, edtTelefono;
    private RadioButton rdbHombre, rdbMujer;
    private Button btnAceptar;

    String nombre, edad, telefono, sexo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aniadir, container, false);

        edtNombre = (EditText) view.findViewById(R.id.edtNombre);
        edtEdad = (EditText) view.findViewById(R.id.edtEdad);
        edtTelefono = (EditText) view.findViewById(R.id.edtTelefono);
        rdbHombre = (RadioButton) view.findViewById(R.id.rdbHombre);
        rdbMujer = (RadioButton) view.findViewById(R.id.rdbMujer);
        btnAceptar = (Button) view.findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(this);
        rdbHombre.setOnCheckedChangeListener(this);
        rdbMujer.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        if (edtNombre.getText().equals("") || edtEdad.getText().equals("") || edtTelefono.getText().equals("") || (!rdbHombre.isChecked() && !rdbMujer.isChecked())) {

            Toast.makeText(getContext(), "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show();

        } else {

            nombre = edtNombre.getText().toString();
            edad = edtEdad.getText().toString();
            telefono = edtTelefono.getText().toString();

            //Insertamos un nuevo contacto
            insertar(nombre, edad, telefono, sexo);

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        //Comprobamos que radiobutton esta checkeado
        if (isChecked) {
            switch (compoundButton.getId()) {
                case R.id.rdbHombre:
                    sexo = "H";
                    break;
                case R.id.rdbMujer:
                    sexo = "M";
                    break;
            }
        }

    }

    /**
     * Metodo que inserta un nuevo contacto llamando al metodo insertarContacto() de la manejadora de la BBDD
     *
     * @param nombre
     * @param edad
     * @param telefono
     * @param sexo
     */
    private void insertar(String nombre, String edad, String telefono, String sexo) {

        DBAdapter db = new DBAdapter(getActivity());
        db.abrirBD();

        boolean guardado = db.insertarContacto(nombre, edad, telefono, sexo);

        //Si se ha guardado bien reseteamos los eddittext, sino mostramos un error
        if (guardado) {

            edtNombre.setText("");
            edtEdad.setText("");
            edtTelefono.setText("");
            rdbHombre.setChecked(false);
            rdbMujer.setChecked(false);

            Toast.makeText(getActivity(), "Guardado con éxito", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getActivity(), "No se puede guardar", Toast.LENGTH_SHORT).show();

        }

        db.cerrarBD();

    }

}
