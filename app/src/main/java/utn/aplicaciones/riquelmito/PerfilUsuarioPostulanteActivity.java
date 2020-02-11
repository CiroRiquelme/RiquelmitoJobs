package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Sexo;
import utn.aplicaciones.riquelmito.domain.Usuario;
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;

public class PerfilUsuarioPostulanteActivity extends AppCompatActivity {
    private final int LONG_MIN_NOMBRE = 2;    //Longitud mínima del nombre y apellido

    private EditText etPerfPostulNombre;
    private EditText etPerfPostulApellido;
    private EditText etPerfPostulDNI;
    private EditText etPerfPostulNacimiento;
    private Spinner spnPerfPostulSexo;
    private Spinner spnPerfPostulProvincia;
    private EditText etPerfPostulCiudad;
    private EditText etPerfPostulTelefono;
    private EditText etPerfPostulEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario_postulante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPerfPostulNombre = findViewById(R.id.etPerfPostulNombre);
        etPerfPostulApellido = findViewById(R.id.etPerfPostulApellido);;
        etPerfPostulDNI = findViewById(R.id.etPerfPostulDNI);;
        etPerfPostulNacimiento = findViewById(R.id.etPerfPostulNacimiento);;
        spnPerfPostulSexo = findViewById(R.id.spnPerfPostulSexo);;
        spnPerfPostulProvincia = findViewById(R.id.spnPerfPostulProvincia);;
        etPerfPostulCiudad = findViewById(R.id.etPerfPostulCiudad);;
        etPerfPostulTelefono = findViewById(R.id.etPerfPostulTelefono);;
        etPerfPostulEmail = findViewById(R.id.etPerfPostulEmail);;

        llenarCampos();
    }

    private void llenarCampos(){
        int cantidadItemsSpnSexo = spnPerfPostulSexo.getAdapter().getCount();
        int cantidadItemsSpnProvincia = spnPerfPostulProvincia.getAdapter().getCount();

        //Verifica que la variable global de usuario actual no nula
        if(AdministradorDeSesion.postulante == null)
            return;

        //Verifica que los campos necesarios no sean nulos
        if(AdministradorDeSesion.postulante.getNombre() != null)
            etPerfPostulNombre.setText(AdministradorDeSesion.postulante.getNombre());
        if(AdministradorDeSesion.postulante.getApellido() != null)
            etPerfPostulApellido.setText(AdministradorDeSesion.postulante.getApellido());
        if(AdministradorDeSesion.postulante.getDni() != null)
            etPerfPostulDNI.setText(AdministradorDeSesion.postulante.getDni().toString());
        if(AdministradorDeSesion.postulante.getNacimiento() != null){
            etPerfPostulNacimiento.setText(AdministradorDeCargaDeInterfaces.dateToString(AdministradorDeSesion.postulante.getNacimiento()));
        }
        if(AdministradorDeSesion.postulante.getSexo() != null)
            for(int i = 0; i < cantidadItemsSpnSexo; i++){
                if((String) spnPerfPostulSexo.getItemAtPosition(i) == AdministradorDeSesion.postulante.getSexo().toString()){
                    spnPerfPostulSexo.setSelection(i);
                    break;
                }
            }
        if(AdministradorDeSesion.postulante.getProvincia() != null)
            for(int j = 0; j < cantidadItemsSpnProvincia; j++){
                if(((String) spnPerfPostulProvincia.getItemAtPosition(j)).equals(AdministradorDeSesion.postulante.getProvincia())){
                    spnPerfPostulProvincia.setSelection(j);
                    break;
                }
            }
        if(AdministradorDeSesion.postulante.getCiudad() != null)
            etPerfPostulCiudad.setText(AdministradorDeSesion.postulante.getCiudad());
        if(AdministradorDeSesion.postulante.getTelefono() != null)
            etPerfPostulTelefono.setText(AdministradorDeSesion.postulante.getTelefono());
        if(AdministradorDeSesion.postulante.getEmail() != null)
            etPerfPostulEmail.setText(AdministradorDeSesion.postulante.getEmail());
    }


    public void goToSeleccionarUbicacionPostulante(View view) {
        Intent activity = new Intent(this, SeleccionarUbicacionPostulanteActivity.class);
        startActivity(activity);
    }

    public void actualizarPerfilDeUsuario(View view){
        boolean operacionValida = true;
        StringBuffer mensajeError = new StringBuffer();

        Date fechaNacimiento;

        if(etPerfPostulApellido.getText().length() < LONG_MIN_NOMBRE){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_1));
            mensajeError.append( ' ' );
            mensajeError.append(R.string.perfil_nombre);
            mensajeError.append( ' ' );
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_2));
            mensajeError.append( ' ' );
            mensajeError.append( LONG_MIN_NOMBRE );
            mensajeError.append( ' ' );
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_3));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(etPerfPostulNombre.getText().length() < LONG_MIN_NOMBRE){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_1));
            mensajeError.append( ' ' );
            mensajeError.append(R.string.perfil_apellido);
            mensajeError.append( ' ' );
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_2));
            mensajeError.append( ' ' );
            mensajeError.append( LONG_MIN_NOMBRE );
            mensajeError.append( ' ' );
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_3));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        fechaNacimiento = AdministradorDeCargaDeInterfaces.stringToDate(etPerfPostulNacimiento.getText().toString());
        if(fechaNacimiento == null){
            mensajeError.append(this.getString(R.string.dialogo_error_fecha_nacimiento_incorrecta));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(!AdministradorDeCargaDeInterfaces.esEmailValido(etPerfPostulEmail.getText().toString())){
            mensajeError.append(this.getString(R.string.dialogo_error_formato_email_invalido));
            mensajeError.append( '\n' );
            operacionValida = false;
        }


        if(operacionValida){
            //Si no hubieron errores

            //TODO: Actualizar los valores modificados (o en su defecto todos) de la DB interna, de firestore y de la variable global
        }
        else{
            //Mostrar dialogo de advertencias
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(this.getString(R.string.title_dialogo_campos_invalidos))
                    .setMessage(mensajeError)
                    .setNeutralButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //No es necesario hacer nada
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


    //Esta función permite que el botón de 'volver atrás' de la barra superior funcione
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
