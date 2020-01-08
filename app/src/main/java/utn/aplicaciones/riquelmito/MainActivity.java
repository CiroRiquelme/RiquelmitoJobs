package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inciar_sesion);
    }

    public void goToSingUp(View view) {
        Intent singUp = new Intent(this, CrearCuentaActivity.class);
        startActivity(singUp);
    }

    public void goToMenu(View view){
        Intent menu = new Intent(this, MenuPostulanteTemporal.class);
        startActivity(menu);
    }

}
