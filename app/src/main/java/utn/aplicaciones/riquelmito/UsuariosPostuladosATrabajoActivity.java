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
import java.util.List;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Suscripcion;
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.domain.Usuario;
import utn.aplicaciones.riquelmito.utilidades.PostulantesAdapter;

public class UsuariosPostuladosATrabajoActivity extends AppCompatActivity {
    private ProgressBar rvUsuariosPostuladosATrabajoWaitting;
    private RecyclerView recyclerView;
    private PostulantesAdapter adapter;
    private RecyclerView.LayoutManager manager;

    private Trabajo trabajo;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Usuario> listaPostulantes = new ArrayList<Usuario>();;
    private List<Integer> idsPostulantes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_postulados_a_trabajo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvUsuariosPostuladosATrabajoWaitting = findViewById(R.id.pbUsuariosPostuladosATrabajoWaitting);

        inicializarFirebase();

        //Cargar el trabajo del cual se obtendrá los id de usuarios suscriptos
        Bundle bundleReseptor = getIntent().getExtras();
        if(bundleReseptor!=null)
            trabajo = (Trabajo) bundleReseptor.getSerializable("trabajo");
        if(trabajo!=null)
            cargarIdsPostulantes();

        //Cargar RecyclerView, Manager y Adapter
        recyclerView = (RecyclerView) findViewById(R.id.rvUsuariosPostuladosATrabajo);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //Seteo Adapter "vacio" por ahora mientras se cargan los demas desde la DB. Si no se carga aquí igual no pasa nada
        adapter = new PostulantesAdapter( this, listaPostulantes );
        recyclerView.setAdapter(adapter);
    }

    private void cargarIdsPostulantes(){
        Query query = databaseReference.child("Suscripcion").orderByChild("idTrabajo").equalTo(trabajo.getIdTrabajo());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    Suscripcion suscripcion;
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {

                        suscripcion = ds.getValue(Suscripcion.class);
                        idsPostulantes.add(suscripcion.getIdPostulante());
                    }

                    cargarPostulantesDesdeListaIdsDePostulantes();
                }
                else{
                    rvUsuariosPostuladosATrabajoWaitting.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void cargarPostulantesDesdeListaIdsDePostulantes() {
        for(Integer idPostul : idsPostulantes){
            Query query = databaseReference.child("Usuario").orderByChild("idPostulante").equalTo(idPostul);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        Usuario postulante;
                        for(DataSnapshot ds: dataSnapshot.getChildren()) {

                            postulante = ds.getValue(Usuario.class);
                            listaPostulantes.add(postulante);

                            actualizarRecyclerView();
                        }
                    }

                    if(idsPostulantes.size() == listaPostulantes.size()){
                        rvUsuariosPostuladosATrabajoWaitting.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    private void actualizarRecyclerView(){
        adapter = new PostulantesAdapter( AdministradorDeSesion.context,  listaPostulantes);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInfoPostulante( adapter.getListaPostulantes().get(recyclerView.getChildAdapterPosition(view)) );
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void goToInfoPostulante(Usuario postulante){
        Intent intent = new Intent(this, InfoPostulanteActivity.class);

        //Pasa una instancia de Trabajo a la actividad
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", postulante);
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
