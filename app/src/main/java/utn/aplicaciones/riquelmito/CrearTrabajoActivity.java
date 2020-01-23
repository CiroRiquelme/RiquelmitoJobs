package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.DiasLaborales;
import utn.aplicaciones.riquelmito.domain.HorarioLaboral;
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Trabajo;

public class CrearTrabajoActivity extends AppCompatActivity {
    //Longitudes mínimas de los campos
    private final int LONG_MIN_CARGO = 3;
    private final int LONG_MIN_DESCRIPCION_CARGO = 3;
    private final int LONG_MIN_PERFIL_EMP = 3;
    private final int LONG_MIN_EXPERIENCI_EMP = 3;
    private final int LONG_MIN_SALARIO = 1;

    //
    public static int REQUEST_CODE_SELECCIONAR_UBICACION = 77;

    //TODO verificar si falta alguno
    private Spinner spnCrearTrabRubro;
    private EditText etCrearTrabCargo;
    private EditText mltCrearTrabDescripcion;
    private EditText mltCrearTrabPerfilEmpl;
    private EditText mltCrearTrabExperiencia;
    private Spinner spnCrearTrabHorario;
    private Spinner spnCrearTrabDiasSelecc;
    private EditText etCrearTrabSalario;

    private Double latTemporal = AdministradorDeSesion.postulante.getLat();
    private Double lngTemporal = AdministradorDeSesion.postulante.getLng();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private Integer id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_trabajo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spnCrearTrabRubro = (Spinner) findViewById(R.id.spnCrearTrabRubro);
        etCrearTrabCargo = (EditText) findViewById(R.id.etCrearTrabCargo);
        mltCrearTrabDescripcion = (EditText) findViewById(R.id.mltCrearTrabDescripcion);
        mltCrearTrabPerfilEmpl = (EditText) findViewById(R.id.mltCrearTrabPerfilEmpl);
        mltCrearTrabExperiencia = (EditText) findViewById(R.id.mltCrearTrabExperiencia);
        spnCrearTrabHorario = (Spinner) findViewById(R.id.spnCrearTrabHorario);
        spnCrearTrabDiasSelecc = (Spinner) findViewById(R.id.spnCrearTrabDiasSelecc);
        etCrearTrabSalario = (EditText) findViewById(R.id.etCrearTrabSalario);

        spnCrearTrabRubro.setAdapter(new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, Rubro.values() ));
        spnCrearTrabHorario.setAdapter(new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, HorarioLaboral.values() ));
        spnCrearTrabDiasSelecc.setAdapter(new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, DiasLaborales.values() ));

        inicializarFirebase();

        databaseReference.child("IdTrabajo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    id = Integer.parseInt( dataSnapshot.child("valor").getValue().toString() );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO
            }
        });
    }


    public void crearTrabajoClick(View view) {
        boolean operacionValida = true;
        StringBuffer mensajeError = new StringBuffer();

        if(spnCrearTrabRubro.getSelectedItem() == null){
            mensajeError.append(this.getString(R.string.dialogo_error_no_indico_rubro));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(etCrearTrabCargo.getText().length() < LONG_MIN_CARGO){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_1)).append(' ').append('"');
            mensajeError.append(this.getString(R.string.crear_traba_cargo)).append('"').append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_2)).append(' ');
            mensajeError.append(LONG_MIN_CARGO).append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_3));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(mltCrearTrabDescripcion.getText().length() < LONG_MIN_DESCRIPCION_CARGO){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_1)).append(' ').append('"');
            mensajeError.append(this.getString(R.string.crear_traba_descripcion)).append('"').append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_2)).append(' ');
            mensajeError.append(LONG_MIN_DESCRIPCION_CARGO).append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_3));
            mensajeError.append( '\n' );
            operacionValida = false;

        }

        if(mltCrearTrabPerfilEmpl.getText().length() < LONG_MIN_PERFIL_EMP){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_1)).append(' ').append('"');
            mensajeError.append(this.getString(R.string.crear_traba_perfil_empleado)).append('"').append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_2)).append(' ');
            mensajeError.append(LONG_MIN_PERFIL_EMP).append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_3));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(mltCrearTrabExperiencia.getText().length() < LONG_MIN_EXPERIENCI_EMP){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_1)).append(' ').append('"');
            mensajeError.append(this.getString(R.string.crear_traba_experiencia_empleado)).append('"').append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_2)).append(' ');
            mensajeError.append(LONG_MIN_EXPERIENCI_EMP).append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_3));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(spnCrearTrabDiasSelecc.getSelectedItem() == null){
            mensajeError.append(this.getString(R.string.dialogo_error_no_indico_dias_laborales));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(spnCrearTrabHorario.getSelectedItem() == null){
            mensajeError.append(this.getString(R.string.dialogo_error_no_indico_horario_laboral));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(etCrearTrabSalario.getText().length() < LONG_MIN_SALARIO){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_1)).append(' ').append('"');
            mensajeError.append(this.getString(R.string.crear_traba_salario)).append('"').append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_2)).append(' ');
            mensajeError.append(LONG_MIN_SALARIO).append(' ');
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_minima_campo_3));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(operacionValida){
            if(id==-1){
                Toast.makeText(this, "Espere hasta que la página inicie",Toast.LENGTH_LONG).show();
                return;
            }

            //TODO: Mostrar mensaje cuando se ha logrado guardar los cambios en la "base de datos"
            Trabajo trabajo = new Trabajo(id, (Rubro) spnCrearTrabRubro.getSelectedItem(), etCrearTrabCargo.getText().toString(), mltCrearTrabDescripcion.getText().toString(),
                    mltCrearTrabPerfilEmpl.getText().toString(),mltCrearTrabExperiencia.getText().toString(),(DiasLaborales) spnCrearTrabDiasSelecc.getSelectedItem(),
                    (HorarioLaboral) spnCrearTrabHorario.getSelectedItem(), Integer.parseInt(etCrearTrabSalario.getText().toString()), latTemporal, lngTemporal);
            trabajo.setIdEmpleador(AdministradorDeSesion.postulante.getIdPostulante());

            registrarTrabajo(trabajo);
            databaseReference.child("IdTrabajo").child("valor").setValue(id+1);
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

    //Registra un nuevo trabajo en la "base de datos"
    private void registrarTrabajo(Trabajo trabajo){
        databaseReference.child("Trabajo").child(trabajo.getIdTrabajo().toString()).setValue(trabajo);
    }

    //Se debe ejecutar al hacer click en el botón de seleccionar ubicación. Abre la actividad de seleccionar ubicación y queda a la espera de que termine y retorne un par de valor (de longitud y latitud)
    public void goToSeleccionarUbicacionTrabajoActivity(View view){
        Intent intent = new Intent(this, SeleccionarUbicacionTrabajoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SELECCIONAR_UBICACION);
    }

    @Override
    //Aquí se devuelve el valor de la actividad de seleccionar ubicación una vez que termina de seleccionarse o se cancela
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECCIONAR_UBICACION) {
            if(resultCode == RESULT_OK) {
                latTemporal = data.getDoubleExtra("latitudActual",latTemporal);
                lngTemporal = data.getDoubleExtra("longitudActual", lngTemporal);
            }
        }
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
