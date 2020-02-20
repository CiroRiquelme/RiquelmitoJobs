package utn.aplicaciones.riquelmito;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ConsultarUbicacionActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double lat;
    private Double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ubicacion);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

        Bundle bundleReceptor = getIntent().getExtras();

        if(bundleReceptor!=null) {
            lat = bundleReceptor.getDouble("lat");
            lng = bundleReceptor.getDouble("lng");
        }

        // Add a marker in Sydney and move the camera
        LatLng actual = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(actual).title("Posición Actual").icon(BitmapDescriptorFactory.fromResource(R.drawable.wally)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(actual,10));
    }
}
