package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class PerfilUsuarioPostulanteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario_postulante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void goToSeleccionarUbicacionPostulante(View view) {
        Intent activity = new Intent(this, SeleccionarUbicacionPostulanteActivity.class);
        startActivity(activity);
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
