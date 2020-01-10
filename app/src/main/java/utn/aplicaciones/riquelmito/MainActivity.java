package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Postulante;
import utn.aplicaciones.riquelmito.domain.Sexo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inciar_sesion);
    }

    public void goToSingUp(View view) {
        Intent singUp = new Intent(this, CrearCuentaActivity.class);
        startActivity(singUp);
        finish();
    }

    public void goToMenu(View view){
        DateFormat nacimiento = new SimpleDateFormat("dd/mm/yyyy");
        try {
            AdministradorDeSesion.postulante = new Postulante(7, "prpitoracing@gmail.com", "racing", "José", "Argento", 777777, nacimiento.parse("21/03/1962"), Sexo.MASCULINO, "Buenos Aires", "Capital Federal", "123456789", -34.7702, -58.4327, "Vendedor de zapatos hace 30 años", "Secundario completo", "Español y Guaraní antiguo");
            Intent menu = new Intent(this, MenuPostulanteTemporal.class);
            startActivity(menu);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
