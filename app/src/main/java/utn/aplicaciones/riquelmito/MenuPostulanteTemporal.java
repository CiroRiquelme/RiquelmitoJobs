package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPostulanteTemporal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_postulante_temporal);
    }


    public void goToEditarPerfilUsuario (View view) {
        Intent activity = new Intent(this, PerfilUsuarioPostulanteActivity.class);
        startActivity(activity);
    }

    public void goToEditarPerfilProfesional(View view) {
        Intent activity = new Intent(this, PerfilProfesionalActivity.class);
        startActivity(activity);
    }

    public void goToBuscarTrabajo(View view) {
        Intent activity = new Intent(this, BuscarTrabajoActivity.class);
        startActivity(activity);
    }

    public void goToTrabajosPostulados (View view) {
        Intent activity = new Intent(this, TrabajosPostuladosActivity.class);
        startActivity(activity);
    }

    public void goToTrabajosDeInteres (View view) {
        Intent activity = new Intent(this, TrabajosDeInteresActivity.class);
        startActivity(activity);
    }

    public void goToConfigurarNotificaciones(View view) {
        Intent activity = new Intent(this, ConfiguracionNotificacionesPostulanteActivity.class);
        startActivity(activity);
    }

    public void goToSeleccionarUbicacionPostulante(View view) {
        Intent activity = new Intent(this, SeleccionarUbicacionPostulanteActivity.class);
        startActivity(activity);
    }

    public void goToIniciarSesion(View view) {
        Intent activity = new Intent(this, MainActivity.class);
        startActivity(activity);
    }
}
