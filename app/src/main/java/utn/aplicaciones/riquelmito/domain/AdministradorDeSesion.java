package utn.aplicaciones.riquelmito.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utn.aplicaciones.riquelmito.utilidades.ConexionSQLiteHelper;

public class AdministradorDeSesion {

    private static ConexionSQLiteHelper conn;

    public static Usuario postulante;
    public static Context context;


    public static void buscarUsuario() throws ParseException {

        conn = new ConexionSQLiteHelper(context, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        int longitud=0;
        Cursor cursor = db.rawQuery("SELECT * FROM USUARIO", null);

        if(cursor.moveToFirst()){
            //Toast.makeText(context,"Valor de BD: " + cursor.getString(0), Toast.LENGTH_LONG).show();
            postulante.setIdPostulante(cursor.getInt(0));
            postulante.setEmail(cursor.getString(1));
            postulante.setContraseña(cursor.getString(2));
            postulante.setNombre(cursor.getString(3));
            postulante.setApellido(cursor.getString(4));
            postulante.setDni(cursor.getInt(5));
            String nacimString = new String();
            nacimString = cursor.getString(6);
            Date nacim = new SimpleDateFormat("dd/MM/yyyy").parse(nacimString);
            postulante.setNacimiento(nacim);

            Sexo s;
            if(cursor.getString(7).equals("Femenino")){
                s = Sexo.FEMENINO;
                postulante.setSexo(s);
            }else if((cursor.getString(7).equals("Masculino"))){
                s = Sexo.MASCULINO;
                postulante.setSexo(s);
            }
            else{
                s = Sexo.OTRO;
                postulante.setSexo(s);
            }
            postulante.setProvincia(cursor.getString(8));
            postulante.setCiudad(cursor.getString(9));
            postulante.setTelefono(cursor.getString(10));
            postulante.setLat(cursor.getDouble(11));
            postulante.setLng(cursor.getDouble(12));
            postulante.setExperiencia(cursor.getString(13));
            postulante.setFormacion(cursor.getString(14));
            postulante.setIdiomas(cursor.getString(15));
        }
        else{
            Toast.makeText(context,"Perdedor!!!", Toast.LENGTH_LONG).show();
        }

        db.close();

    }


    public static void cerrarSesion() {
        //TODO: Este metodo debe borrar todos los datos de usuario guardados
    }

}
