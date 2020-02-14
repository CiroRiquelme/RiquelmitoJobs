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
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;
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
            //guardarUsuario(AdministradorDeSesion.postulante);
            asda(AdministradorDeSesion.postulante);
            //Intent menu = new Intent(this, MenuNotificacionesActivity.class);
            /*Intent menu = new Intent(this, MenuPostulanteTemporal.class);
            startActivity(menu);
            finish();*/
            //TODO: desmarcar lo siguiente. Verificar tambien que se setee el tipo de usuario
            Intent menu = new Intent(this, AdministradorDeSesion.getCurrentMenu());
            startActivity(menu);
            finish();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //TODO borrar funcion
    public void guardarUsuario(Usuario usuario){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        int idPostulante = usuario.getIdPostulante();
        String email = usuario.getEmail();
        String contrasenia = usuario.getContrasenia();
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        int dni = usuario.getDni();
        String nacimiento = dateFormat.format(usuario.getNacimiento());
        String sexo = usuario.getSexo().sexoAIdentificador();
        String provincia = usuario.getProvincia();
        String ciudad = usuario.getCiudad();
        String telefono = usuario.getTelefono();
        String lat = usuario.getLat().toString();
        String lng = usuario.getLng().toString();
        String experiencia = usuario.getExperiencia();
        String formacion = usuario.getFormacion();
        String idiomas = usuario.getIdiomas();
        String tipoDeUsuario = usuario.getTipoUsuario().tipoUsuarioAIdentificador();
        String quienVeMiCv = usuario.getQuienVeMiCV().quienvemicvAIdentificador();

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String insertarUsuario = "INSERT INTO USUARIO (idPostulante, email, contrasenia," +
                "nombre, apellido, dni, nacimiento, sexo, provincia, ciudad," +
                "telefono, lat, lng, experiencia, formacion, idiomas, tipoUsuario) " +
                "VALUES ("+idPostulante+", '"+email+"', '"+contrasenia+"', '"+nombre+"', '"+apellido+"', "+
        dni+", '"+nacimiento+"', '"+sexo+"', '"+provincia+"', '"+ciudad+"', '"+telefono+"', "+
        lat+", "+lng+", '"+experiencia+"', '"+formacion+"', '"+idiomas+"', '"+tipoDeUsuario+"', '"+quienVeMiCv+"')";
        db.execSQL(insertarUsuario);
    }


    //TODO cambiar nombre de funcion a guardarUsuario
    public void asda(Usuario usuario){
        StringBuffer insertInto = new StringBuffer();
        StringBuffer values = new StringBuffer();

        if( usuario.getIdPostulante() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("idPostulante");
            values.append(usuario.getIdPostulante());
        }

        if( usuario.getEmail() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("email");
            values.append('\'');
            values.append(usuario.getEmail());
            values.append('\'');
        }

        if( usuario.getContrasenia() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("contrasenia");
            values.append('\'');
            values.append(usuario.getContrasenia());
            values.append('\'');
        }

        if( usuario.getNombre() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("nombre");
            values.append('\'');
            values.append(usuario.getNombre());
            values.append('\'');
        }

        if( usuario.getApellido() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("apellido");
            values.append('\'');
            values.append(usuario.getApellido());
            values.append('\'');
        }

        if( usuario.getDni() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("dni");
            values.append(usuario.getDni());
        }

        if( usuario.getNacimiento() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("nacimiento");
            values.append('\'');
            values.append(AdministradorDeCargaDeInterfaces.dateToString(usuario.getNacimiento()));
            values.append('\'');
        }

        if( usuario.getSexo() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("sexo");
            values.append('\'');
            values.append(usuario.getSexo().sexoAIdentificador());
            values.append('\'');
        }

        if( usuario.getProvincia() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("provincia");
            values.append('\'');
            values.append(usuario.getProvincia());
            values.append('\'');
        }

        if( usuario.getCiudad() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("ciudad");
            values.append('\'');
            values.append(usuario.getCiudad());
            values.append('\'');
        }

        if( usuario.getTelefono() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("telefono");
            values.append('\'');
            values.append(usuario.getTelefono());
            values.append('\'');
        }

        if( usuario.getLat() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("lat");
            values.append(usuario.getLat());
        }

        if( usuario.getLng()!= null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("lng");
            values.append(usuario.getLng());
        }

        if( usuario.getExperiencia() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("experiencia");
            values.append('\'');
            values.append(usuario.getExperiencia());
            values.append('\'');
        }

        if( usuario.getFormacion() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("formacion");
            values.append('\'');
            values.append(usuario.getFormacion());
            values.append('\'');
        }

        if( usuario.getIdiomas() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("idiomas");
            values.append('\'');
            values.append(usuario.getIdiomas());
            values.append('\'');
        }

        if( usuario.getTipoUsuario() != null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("tipoUsuario");
            values.append('\'');
            values.append(usuario.getTipoUsuario().tipoUsuarioAIdentificador());
            values.append('\'');
        }

        if( usuario.getQuienVeMiCV()!= null){
            if(insertInto.length() > 0){
                insertInto.append(',');
                values.append(',');
            }
            insertInto.append("quienVeMiCv");
            values.append('\'');
            values.append(usuario.getQuienVeMiCV().quienvemicvAIdentificador());
            values.append('\'');
        }

        if (insertInto.length() > 0) {
            conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();
            String insertarUsuario = "INSERT INTO USUARIO (" + insertInto + ") " + "VALUES ("+ values +")";
            db.execSQL(insertarUsuario);
        }
    }

}
