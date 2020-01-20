package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

import utn.aplicaciones.riquelmito.DTO.DatosTrabajosCercanosAUsuarioDTO;
import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Trabajo;


public class TrabajosCercanosAUsuarioActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Trabajo> listaTrabajos = new ArrayList<Trabajo>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_cercanos_ausuario);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        inicializarFirebase();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //TODO posicionar cámara en otro lado
        LatLng posicionUsuario = new LatLng(AdministradorDeSesion.postulante.getLat(), AdministradorDeSesion.postulante.getLng());
        mMap.addMarker(new MarkerOptions().position(posicionUsuario).title("Posición de usuario"));
        ///////

        //Cargar parámetros
        Bundle bundleReseptor = getIntent().getExtras();

        DatosTrabajosCercanosAUsuarioDTO parametros = new DatosTrabajosCercanosAUsuarioDTO();
        if(bundleReseptor!=null) {
            parametros= (DatosTrabajosCercanosAUsuarioDTO) bundleReseptor.getSerializable("parametrosBusqueda");

            final double radioBusqueda = parametros.radioDeBusqueda;

            Double radioEnGrados = (radioBusqueda*1)/(111.12);

            Double latUsuario = AdministradorDeSesion.postulante.getLat();

            Query query = databaseReference.child("Trabajo").orderByChild("lat").startAt(latUsuario-radioEnGrados).endAt(latUsuario+radioEnGrados);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        Trabajo trabajo;
                        for(DataSnapshot ds: dataSnapshot.getChildren()) {

                            trabajo = ds.getValue(Trabajo.class);
                            Location locationTrabajo = new Location("Punto A");
                            locationTrabajo.setLatitude(trabajo.getLat());
                            locationTrabajo.setLongitude(trabajo.getLng());
                            Location locationUsuario = new Location("Punto B");
                            locationUsuario.setLatitude(AdministradorDeSesion.postulante.getLat());
                            locationUsuario.setLongitude(AdministradorDeSesion.postulante.getLng());

                            float distance = locationTrabajo.distanceTo(locationUsuario);

                            //TODO agregar al if "&& trabajo.getRubro=="RubroSeleccionadoPorUsuario""
                            if(distance < radioBusqueda*1000){
                                //TODO agregar trabajo a Arraylist y retornarlo
                                listaTrabajos.add(trabajo);
                            }
                        }
                    }

                    for(Trabajo trabajo: listaTrabajos){
                        LatLng posicion = new LatLng(trabajo.getLat(), trabajo.getLng());

                        Marker marcador = mMap.addMarker(new MarkerOptions().position(posicion).title(trabajo.getCargo()).snippet("id: "+trabajo.getIdTrabajo()+"\nSalario: "+trabajo.getSalario()+"\nDescripción del cargo: "+trabajo.getDescripcionCargo()+"\nExperiencia requerida"+trabajo.getExperienciaEmpleado()+"\nPerfil del empleado: "+trabajo.getPerfilEmpleado()));
                        //marcador.setTag(trabajo.getIdTrabajo());
                        marcador.setTag(trabajo);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicionUsuario));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.getTag() != null){
                    Trabajo trabajo = (Trabajo) marker.getTag();
                    goToInfoTrabajoDesdePostulanteActivity(trabajo);
                }
            }
        });

    }

    private void goToInfoTrabajoDesdePostulanteActivity(Trabajo trabajo){
        Intent intent = new Intent(this, InfoTrabajoDesdePostulanteActivity.class);
        //Pasa trabajo para próxima activity
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
}