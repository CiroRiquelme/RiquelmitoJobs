package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.utilidades.TrabajoDesdeEmpleadorAdapter;

public class TrabajosCreadosActivosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TrabajoDesdeEmpleadorAdapter adapter;
    private RecyclerView.LayoutManager manager;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Trabajo> listaTrabajos = new ArrayList<Trabajo>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_creados_activos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializarFirebase();

        recyclerView = (RecyclerView) findViewById(R.id.rvOfertasActivas);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        cargarTrabajos();

        //Seteo Adapter "vacio" por ahora mientras se cargan los demas desde la DB. Si no se carga aquí igual no pasa nada
        adapter = new TrabajoDesdeEmpleadorAdapter( this,  listaTrabajos);
        recyclerView.setAdapter(adapter);
    }


    private void cargarTrabajos(){
        Query query = databaseReference.child("Trabajo").orderByChild("idEmpleador").equalTo(AdministradorDeSesion.postulante.getIdPostulante());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("CREATION","************Inicio onDataChange");

                if(dataSnapshot.exists()){

                    Trabajo trabajo;
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        trabajo = ds.getValue(Trabajo.class);
                        listaTrabajos.add(trabajo);

                        Log.d("CREATION","************Id nro: " + trabajo.getIdTrabajo());
                    }
                }
                adapter = new TrabajoDesdeEmpleadorAdapter( AdministradorDeSesion.context,  listaTrabajos);
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToInfoTrabajoDesdeTrabajosCreados( adapter.getListaTrabajos().get(recyclerView.getChildAdapterPosition(view)) );
                    }
                });

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void goToInfoTrabajoDesdeTrabajosCreados(Trabajo trabajo){
        Intent intent = new Intent(this, InfoTrabajoDesdeEmpleadorActivity.class);

        //Pasa una instancia de Trabajo a la actividad
        Bundle bundle = new Bundle();
        bundle.putSerializable("trabajo",trabajo);
        intent.putExtras(bundle);

        startActivity(intent);
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
