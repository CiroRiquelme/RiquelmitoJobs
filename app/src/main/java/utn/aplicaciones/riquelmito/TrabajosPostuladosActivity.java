package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.DiasLaborales;
import utn.aplicaciones.riquelmito.domain.HorarioLaboral;
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Suscripcion;
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.utilidades.TrabajoDesdePostulanteAdapter;

public class TrabajosPostuladosActivity extends AppCompatActivity {
    private ProgressBar pbTrabajosPostuladosWaitting;
    private RecyclerView recyclerView;
    private TrabajoDesdePostulanteAdapter adapter;
    private RecyclerView.LayoutManager manager;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ArrayList<Integer> idsTrabajosPostulados = new ArrayList<Integer>();
    private ArrayList<Trabajo> trabajosPostulados = new ArrayList<Trabajo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_postulados);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pbTrabajosPostuladosWaitting = findViewById(R.id.pbTrabajosPostuladosWaitting);
        recyclerView = (RecyclerView) findViewById(R.id.rvTrabajosPostulados);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        inicializarFirebase();

        cargarTrabajosDePrueba();
    }

    public void goToInfoTrabajoDesdeTrabajosPostulados(Trabajo trabajo){
        Intent intent = new Intent(this, InfoTrabajoDesdePostulanteActivity.class);

        //Pasa una instancia de Trabajo a la actividad
        Bundle bundle = new Bundle();
        bundle.putSerializable("trabajo",trabajo);
        intent.putExtras(bundle);

        startActivity(intent);
    }


    private void cargarTrabajosDePrueba() {
        Query query = databaseReference.child("Suscripcion").orderByChild("idPostulante").equalTo(AdministradorDeSesion.postulante.getIdPostulante());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    Suscripcion suscripcion;
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {

                        suscripcion = ds.getValue(Suscripcion.class);
                        idsTrabajosPostulados.add(suscripcion.getIdTrabajo());
                    }

                    cargarTrabajosDesdeListaIdsDeTrabajos();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void cargarTrabajosDesdeListaIdsDeTrabajos(){
        for(Integer id : idsTrabajosPostulados){
            Query query = databaseReference.child("Trabajo").orderByChild("idTrabajo").equalTo(id);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        Trabajo trabajoSuscripto;
                        for(DataSnapshot ds: dataSnapshot.getChildren()) {

                            trabajoSuscripto = ds.getValue(Trabajo.class);
                            trabajosPostulados.add(trabajoSuscripto);
                        }

                        actualizarRecyclerView();
                    }

                    if(idsTrabajosPostulados.size() == trabajosPostulados.size()){
                        pbTrabajosPostuladosWaitting.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void actualizarRecyclerView(){
        adapter = new TrabajoDesdePostulanteAdapter( this, trabajosPostulados );
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInfoTrabajoDesdeTrabajosPostulados( adapter.getListaTrabajos().get(recyclerView.getChildAdapterPosition(view)) );
            }
        });

        recyclerView.setAdapter(adapter);
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
