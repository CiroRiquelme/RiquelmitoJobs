package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Sexo;
import utn.aplicaciones.riquelmito.domain.Usuario;
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;
import utn.aplicaciones.riquelmito.utilidades.ConexionSQLiteHelper;

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

        spnPerfPostulSexo.setAdapter(new ArrayAdapter<Sexo>(this, android.R.layout.simple_selectable_list_item, Sexo.values()));

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
                if((Sexo) spnPerfPostulSexo.getItemAtPosition(i) == AdministradorDeSesion.postulante.getSexo()){
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
            boolean hayCambios = false;
            StringBuffer sentenciaSet = new StringBuffer();
            String nacimientoViejo = AdministradorDeCargaDeInterfaces.dateToString(AdministradorDeSesion.postulante.getNacimiento());
            String nacimientoNuevo = etPerfPostulNacimiento.getText().toString();

            if( ! etPerfPostulNombre.getText().toString().equals(AdministradorDeSesion.postulante.getNombre()) ){
                //Actualizar nombre
                AdministradorDeSesion.postulante.setNombre(etPerfPostulNombre.getText().toString());
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("nombre='"+etPerfPostulNombre.getText()+'\'');
                hayCambios = true;
            }

            if( ! etPerfPostulApellido.getText().toString().equals(AdministradorDeSesion.postulante.getApellido()) ){
                //Actualizar apellido
                AdministradorDeSesion.postulante.setApellido(etPerfPostulApellido.getText().toString());
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("apellido='"+etPerfPostulApellido.getText()+'\'');
                hayCambios = true;
            }

            if( ! etPerfPostulDNI.getText().toString().equals(AdministradorDeSesion.postulante.getDni()+"") ){
                //Actualizar dni
                AdministradorDeSesion.postulante.setDni(Integer.valueOf(etPerfPostulDNI.getText().toString()));
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("dni="+etPerfPostulDNI.getText());
                hayCambios = true;
            }

            if( ! nacimientoNuevo.equals(nacimientoViejo) ){
                //Actualizar nacimiento
                AdministradorDeSesion.postulante.setNacimiento(AdministradorDeCargaDeInterfaces.stringToDate(nacimientoNuevo));
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("nacimiento='"+nacimientoNuevo+'\'');
                hayCambios = true;
            }

            if( ! ( (Sexo) spnPerfPostulSexo.getSelectedItem()).equals(AdministradorDeSesion.postulante.getSexo()) ){
                //Actualizar sexo
                AdministradorDeSesion.postulante.setSexo((Sexo) spnPerfPostulSexo.getSelectedItem());
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("sexo='"+((Sexo) spnPerfPostulSexo.getSelectedItem()).sexoAIdentificador()+'\'');
                hayCambios = true;
            }

            if( ! spnPerfPostulProvincia.getSelectedItem().toString().equals(AdministradorDeSesion.postulante.getProvincia()) ){
                //Actualizar provincia
                AdministradorDeSesion.postulante.setProvincia(spnPerfPostulProvincia.getSelectedItem().toString());
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("provincia='"+spnPerfPostulProvincia.getSelectedItem().toString()+'\'');
                hayCambios = true;
            }

            if( ! etPerfPostulCiudad.getText().toString().equals(AdministradorDeSesion.postulante.getCiudad()) ){
                //Actualizar ciudad
                AdministradorDeSesion.postulante.setCiudad(etPerfPostulCiudad.getText().toString());
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("ciudad='"+etPerfPostulCiudad.getText()+'\'');
                hayCambios = true;
            }

            if( ! etPerfPostulTelefono.getText().toString().equals(AdministradorDeSesion.postulante.getTelefono()) ){
                //Actualizar telefono
                AdministradorDeSesion.postulante.setTelefono(etPerfPostulTelefono.getText().toString());
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("telefono='"+etPerfPostulTelefono.getText()+'\'');
                hayCambios = true;
            }

            if( ! etPerfPostulEmail.getText().toString().equals(AdministradorDeSesion.postulante.getEmail()) ){
                //Actualizar email
                AdministradorDeSesion.postulante.setEmail(etPerfPostulEmail.getText().toString());
                if(sentenciaSet.length() > 0)
                    sentenciaSet.append(',');
                sentenciaSet.append("email='"+etPerfPostulEmail.getText()+'\'');
                hayCambios = true;
            }

            //Si hay cambios respecto a los datos guardados entonces actualiza las bases de datos en una única ocasión
            if(hayCambios){
                //Actualiza base de datos local
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
                SQLiteDatabase dbb = conn.getWritableDatabase();
                dbb.execSQL("UPDATE USUARIO SET "+sentenciaSet+" WHERE idPostulante="+AdministradorDeSesion.postulante.getIdPostulante());
                dbb.close();

                //Actualiza base de datos Firebase
                AdministradorDeSesion.actualizarUsuarioActualFirebase();

                //Mensaje de se han guardado los cambios
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(this.getString(R.string.title_dialog_cambios_guardados))
                        .setMessage(this.getString(R.string.dialogo_cambios_guardados))
                        .setNeutralButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //No es necesario hacer nada
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                //Mensaje de no hay modificaciones para guardar
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(this.getString(R.string.title_dialog_no_hay_cambios))
                        .setMessage(this.getString(R.string.dialogo_no_hay_cambios))
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
