package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import utn.aplicaciones.riquelmito.domain.Notificacion;

public class MenuNotificacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_notificaciones);
    }

    public void botonSuscribirse (View view){
        FirebaseServicioDeMensajes fsm = new FirebaseServicioDeMensajes();
        fsm.suscribirATopico("futbol");
    }

    public void botonDesuscribirse (View view){
        FirebaseServicioDeMensajes fsm = new FirebaseServicioDeMensajes();
        fsm.desuscribirATopico("futbol");
    }

    public void botonEnviarNotificacion (View view){
        Notificacion notifs = new Notificacion();
        notifs.setImageUrl("asd");
        notifs.setEmail("test@email.es");
        notifs.setUsername("Pablito");
        notifs.setUid("futbol");
        notifs.setTopic("futbol");

        notifs.setText("The post wa liked");



        FirebaseDatabase.getInstance().getReference("futbol").push().setValue(notifs);
    }
}
