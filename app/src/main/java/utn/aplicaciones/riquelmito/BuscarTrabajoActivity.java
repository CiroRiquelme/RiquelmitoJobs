package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
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

public class BuscarTrabajoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_trabajo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void buscarTrabajo(View view){
        Intent intent = new Intent(this, TrabajosCercanosAUsuarioActivity.class);

        DatosTrabajosCercanosAUsuarioDTO parametros = new DatosTrabajosCercanosAUsuarioDTO();
        //TODO obtener parámetros de los elementos de la pantalla
        parametros.setRadioDeBusqueda(5000.);

        //Pasa los parámetros de búsuqeda a siguiente actividad
        Bundle bundle = new Bundle();
        bundle.putSerializable("parametrosBusqueda",parametros);
        intent.putExtras(bundle);

        startActivity(intent);

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
