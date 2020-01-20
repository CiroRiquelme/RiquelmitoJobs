package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import utn.aplicaciones.riquelmito.domain.DiasLaborales;
import utn.aplicaciones.riquelmito.domain.HorarioLaboral;
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Trabajo;

public class CrearTrabajoActivity extends AppCompatActivity {

    //TODO verificar si falta alguno
    private Spinner spnCrearTrabRubro;
    private EditText etCrearTrabCargo;
    private EditText mltCrearTrabDescripcion;
    private EditText mltCrearTrabPerfilEmpl;
    private EditText mltCrearTrabExperiencia;
    //private EditText etCrearTrabDias;
    private Spinner spnCrearTrabHorario;
    private EditText etCrearTrabSalario;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private Integer id = 99;

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
        //etCrearTrabDias;
        spnCrearTrabHorario = (Spinner) findViewById(R.id.spnCrearTrabHorario);
        etCrearTrabSalario = (EditText) findViewById(R.id.etCrearTrabSalario);

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

            }
        });
    }

    public void crearTrabajoClick(View view) {
        boolean operacionValida = true;
        StringBuffer mensajeError = new StringBuffer();

        //TODO verificar datos válidos

        if(operacionValida){
            //TODO verificar los campos que pudieran no ser null (dejé en null los días laborables, horario, rubro)
            Trabajo trabajo = new Trabajo(id, null, "cargo", null, null, 1700);
            //Trabajo trabajo = new Trabajo(id, null, etCrearTrabCargo.getText().toString(), null, null, Integer.parseInt (etCrearTrabSalario.getText().toString()));
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

    private void registrarTrabajo(Trabajo trabajo){
        databaseReference.child("Trabajo").child(trabajo.getIdTrabajo().toString()).setValue(trabajo);
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
