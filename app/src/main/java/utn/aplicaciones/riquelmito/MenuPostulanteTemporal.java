package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import utn.aplicaciones.riquelmito.domain.DiasLaborales;
import utn.aplicaciones.riquelmito.domain.HorarioLaboral;
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Trabajo;

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

    public void goToInfoTrabajoDesdePostulante(View view){
        Intent intent = new Intent(this, InfoTrabajoDesdePostulanteActivity.class);

        //Pasa una instancia de Trabajo a la actividad
        Bundle bundle = new Bundle();
        Trabajo t = new Trabajo(
                Rubro.TRANSPORTE, "Chofer de colectivos",
                "Conducir colectivo de pasajeros de 1 y 2 pisos (de 35 y 50 pasajeros) de media distancia (50 km).",
                "Puntual, responsable, buen trato con personas", "Conductor de colectivos de media o larga distancia con al menos 1 año de experiencia. Sin accidentes graves.",
                DiasLaborales.A_TURNOS, HorarioLaboral.MEDIA_ROTATIVA, 35000, -34.6080556,-58.3702778);
        bundle.putSerializable("trabajo",t);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void goToIniciarSesion(View view) {
        Intent activity = new Intent(this, MainActivity.class);
        startActivity(activity);
        finish();
    }
}
