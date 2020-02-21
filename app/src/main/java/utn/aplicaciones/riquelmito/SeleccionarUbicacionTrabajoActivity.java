package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;

public class SeleccionarUbicacionTrabajoActivity extends AppCompatActivity implements GoogleMap.OnMarkerDragListener, OnMapReadyCallback {

    private GoogleMap mMap;
    public Double latActual;
    public Double lngActual;
    private Marker nuevaPosicion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_ubicacion_trabajo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.seleccUbicacionTrabaMap);
        mapFragment.getMapAsync(this);
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

        // Add a marker in posicionUsuario and move the camera
        LatLng posicionUsuario = new LatLng(AdministradorDeSesion.postulante.getLat(), AdministradorDeSesion.postulante.getLng());
        mMap.addMarker(new MarkerOptions().position(posicionUsuario).title("Posición inicial de Usuario").icon(BitmapDescriptorFactory.fromResource(R.drawable.riquelmito_quiet)));
        LatLng nuevaPosicionUsuario = new LatLng(AdministradorDeSesion.postulante.getLat(), AdministradorDeSesion.postulante.getLng()+0.005f);
        nuevaPosicion = mMap.addMarker(new MarkerOptions().position(nuevaPosicionUsuario).title("Nueva posición de Usuario").icon(BitmapDescriptorFactory.fromResource(R.drawable.riquelmito_running)).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicionUsuario,13.5f));
        this.latActual = AdministradorDeSesion.postulante.getLat();
        this.lngActual = AdministradorDeSesion.postulante.getLng();

        googleMap.setOnMarkerDragListener(this);

        mMap.getUiSettings().setRotateGesturesEnabled(false);   //Deshabilita el giro del mapa
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if(marker.equals(nuevaPosicion)){
            Toast.makeText(this,"Inicio de reposicionamiento", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if(marker.equals(nuevaPosicion)){
            String nuevoTitulo = String.format(Locale.getDefault(), getString(R.string.marker_detail_lating), marker.getPosition().latitude, marker.getPosition().longitude);

            setTitle(nuevoTitulo);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if(marker.equals(nuevaPosicion)){
            Toast.makeText(this,"Fin de reposicionamiento", Toast.LENGTH_LONG).show();
            this.latActual = this.nuevaPosicion.getPosition().latitude;
            this.lngActual = this.nuevaPosicion.getPosition().longitude;
        }
    }

    public void aceptarCambiosUbicacionCrearTrabajo(View view){
        Intent intent = new Intent();
        intent.putExtra("latitudActual", this.latActual);
        intent.putExtra("longitudActual", this.lngActual);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelarCambiosUbicacionCrearTrabajo(View view){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
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
