package com.example.cprieto.repeticionexamen2evaluacion.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.cprieto.repeticionexamen2evaluacion.MainActivity;
import com.example.cprieto.repeticionexamen2evaluacion.R;
import com.example.cprieto.repeticionexamen2evaluacion.bbdd.DBAdapter;
import com.example.cprieto.repeticionexamen2evaluacion.datos.Contacto;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEditar extends Fragment implements View.OnClickListener {

    private EditText edtNombre, edtEdad, edtTelefono;
    private RadioButton rdbHombre, rdbMujer;
    private Button btnAceptar, btnBorrar;
    private Switch swEditar;

    private Context contexto;

    String newNombre, newEdad, newTelefono, newSexo;
    int id;

    String textBtn;

    public FragmentEditar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        edtNombre = (EditText) view.findViewById(R.id.edtNombre);
        edtEdad = (EditText) view.findViewById(R.id.edtEdad);
        edtTelefono = (EditText) view.findViewById(R.id.edtTelefono);
        rdbHombre = (RadioButton) view.findViewById(R.id.rdbHombre);
        rdbMujer = (RadioButton) view.findViewById(R.id.rdbMujer);
        swEditar = (Switch) view.findViewById(R.id.swEditar);
        btnAceptar = (Button) view.findViewById(R.id.btnAceptar);
        btnBorrar = (Button) view.findViewById(R.id.btnBorrar);

        //Recogemos el bundle que habiamos enviado en el MainActivity
        id = getArguments().getInt("id");

        //Le pasamos el contexto que recogimos en el metodo onAttach y se lo pasamos al DBAdapter
        DBAdapter adapterBD = new DBAdapter(contexto);
        adapterBD.abrirBD();

        //Llamamos al metodo getContacto() de la manejadora de la BBDD y le pasamos el id
        Contacto contacto = adapterBD.getContacto(id);

        edtNombre.setText(contacto.getNombre());
        edtEdad.setText(contacto.getEdad());
        edtTelefono.setText(contacto.getTelefono());
        if (contacto.getSexo().equals("H"))
            rdbHombre.setChecked(true);
        else
            rdbMujer.setChecked(true);

        btnAceptar.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);
        swEditar.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.swEditar:
                edtNombre.setEnabled(swEditar.isChecked());
                edtEdad.setEnabled(swEditar.isChecked());
                edtTelefono.setEnabled(swEditar.isChecked());
                rdbHombre.setEnabled(swEditar.isChecked());
                rdbMujer.setEnabled(swEditar.isChecked());
                btnAceptar.setEnabled(swEditar.isChecked());

                break;

            case R.id.btnAceptar:
                if (edtNombre.getText().equals("") || edtEdad.getText().equals("") || edtTelefono.getText().equals("") || (!rdbHombre.isChecked() && !rdbMujer.isChecked())) {

                    Toast.makeText(getContext(), "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show();

                } else {

                    newNombre = edtNombre.getText().toString();
                    newEdad = edtEdad.getText().toString();
                    newTelefono = edtTelefono.getText().toString();
                    if (rdbHombre.isChecked())
                        newSexo = "H";
                    else
                        newSexo = "M";

                    actualizar(newNombre, newEdad, newTelefono, newSexo);

                }

                //Al hacer click en el boton aceptar cogemos el texto del boton y se lo mandamos al metodo recargarFragment del MainActivity para volver al fragment anterior
                textBtn = btnBorrar.getText().toString();
                ((MainActivity) getActivity()).recargarFragment(textBtn);

                break;

            case R.id.btnBorrar:
                AlertDialog diaBox = ConfirmacionBorrar();
                diaBox.show();

        }

    }

    //Metodo que regoge el contexto que le llega
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.contexto = context;
    }

    /**
     * Metodo que actualiza un contacto pasandole unos parametros y llama al metodo actualizaContacto() de la manejadora de la BBDD
     *
     * @param newNombre
     * @param newEdad
     * @param newTelefono
     * @param newSexo
     */
    private void actualizar(String newNombre, String newEdad, String newTelefono, String newSexo) {

        DBAdapter db = new DBAdapter(getActivity());
        db.abrirBD();

        boolean actualizado = db.actualizaContacto(id, newNombre, newEdad, newTelefono, newSexo);

        //Si se ha actualizado bien mostramos un mensaje, sino mostramos un error
        if (actualizado) {

            Toast.makeText(getActivity(), "Actualizado con éxito", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getActivity(), "No se puede guardar", Toast.LENGTH_SHORT).show();

        }

        db.cerrarBD();

    }

    private void borrar() {

        DBAdapter db = new DBAdapter(getActivity());
        db.abrirBD();

        boolean deleted = db.borrarContacto(id);


        //Si se ha borrado bien
        if (deleted) {

            Toast.makeText(getActivity(), "Borrado con éxito", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getActivity(), "No se puede borrar", Toast.LENGTH_SHORT).show();

        }

        db.cerrarBD();

    }

    private AlertDialog ConfirmacionBorrar() {
        AlertDialog confirmacion = new AlertDialog.Builder(getActivity())
                //set message, title, and icon
                .setTitle("BORRAR")
                .setMessage("¿Seguro que quieres borrar el contacto?")
                //.setIcon(R.drawable.delete)

                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        //Llamamos al metodo para borrar el contacto
                        borrar();

                        //Al hacer click en el boton borrar cogemos el texto del boton y se lo mandamos al metodo recargarFragment del MainActivity para volver al fragment anterior
                        textBtn = btnBorrar.getText().toString();
                        ((MainActivity) getActivity()).recargarFragment(textBtn);

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
    }

}
