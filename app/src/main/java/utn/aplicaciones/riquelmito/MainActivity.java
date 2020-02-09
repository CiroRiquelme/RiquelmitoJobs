package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Usuario;
import utn.aplicaciones.riquelmito.domain.Sexo;
import utn.aplicaciones.riquelmito.utilidades.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inciar_sesion);

        AdministradorDeSesion.context = getApplicationContext();

    }

    public void goToSingUp(View view) {
        Intent singUp = new Intent(this, CrearCuentaActivity.class);
        startActivity(singUp);
        finish();
    }

    public void goToMenu(View view){
        DateFormat nacimiento = new SimpleDateFormat("dd/mm/yyyy");
        try {
            //AdministradorDeSesion.postulante = new Usuario(7, "prpitoracing@gmail.com", "racing", "José", "Argento", 777777, nacimiento.parse("21/03/1962"), Sexo.MASCULINO, "Buenos Aires", "Capital Federal", "123456789", -34.7702, -58.4327, "Vendedor de zapatos hace 30 años", "Secundario completo", "Español y Guaraní antiguo");
            AdministradorDeSesion.postulante = new Usuario(7, "prpitoracing@gmail.com", "racing", "José", "Argento", 777777, nacimiento.parse("21/03/1962"), Sexo.MASCULINO, "Buenos Aires", "Capital Federal", "123456789", 0., 0., "Vendedor de zapatos hace 30 años", "Secundario completo", "Español y Guaraní antiguo");
            guardarUsuario(AdministradorDeSesion.postulante);
            Intent menu = new Intent(this, MenuPostulanteTemporal.class);
            //Intent menu = new Intent(this, MenuNotificacionesActivity.class);
            startActivity(menu);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void guardarUsuario(Usuario usuario){

        int idPostulante = usuario.getIdPostulante();
        String email = usuario.getEmail();
        String contraseña = usuario.getContraseña();
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        int dni = usuario.getDni();
        String nacimiento = usuario.getNacimiento().toString();
        String sexo = usuario.getSexo().toString();
        String provincia = usuario.getProvincia();
        String ciudad = usuario.getCiudad();
        String telefono = usuario.getTelefono();
        String lat = usuario.getLat().toString();
        String lng = usuario.getLng().toString();
        String experiencia = usuario.getExperiencia();
        String formacion = usuario.getFormacion();
        String idiomas = usuario.getIdiomas();

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String insertarUsuario = "INSERT INTO USUARIO (idPostulante, email, contraseña," +
                "nombre, apellido, dni, nacimiento, sexo, provincia, ciudad," +
                "telefono, lat, lng, experiencia, formacion, idiomas) " +
                "VALUES ("+idPostulante+", '"+email+"', '"+contraseña+"', '"+nombre+"', '"+apellido+"', '"+
        dni+"', '"+nacimiento+"', '"+sexo+"', '"+provincia+"', '"+ciudad+"', '"+telefono+", "+
        lat+", "+lng+", '"+experiencia+"', '"+formacion+"', '"+idiomas+"')";
        db.execSQL(insertarUsuario);
    }

}
