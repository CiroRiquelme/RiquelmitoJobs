package utn.aplicaciones.riquelmito.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import utn.aplicaciones.riquelmito.MainActivity;
import utn.aplicaciones.riquelmito.MenuEmpleadorActivity;
import utn.aplicaciones.riquelmito.MenuPostulanteActivity;
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
            postulante.setContrasenia(cursor.getString(2));
            postulante.setNombre(cursor.getString(3));
            postulante.setApellido(cursor.getString(4));
            postulante.setDni(cursor.getInt(5));
            String nacimString = new String();
            nacimString = cursor.getString(6);
            if(nacimString != null){
                Date nacim = new SimpleDateFormat("dd/MM/yyyy").parse(nacimString);
                postulante.setNacimiento(nacim);
            }

            if(cursor.getString(7) != null)
                postulante.setSexo( Sexo.identificadorASexo(cursor.getString(7)) ); ;

            postulante.setProvincia(cursor.getString(8));
            postulante.setCiudad(cursor.getString(9));
            postulante.setTelefono(cursor.getString(10));
            postulante.setLat(cursor.getDouble(11));
            postulante.setLng(cursor.getDouble(12));
            postulante.setExperiencia(cursor.getString(13));
            postulante.setFormacion(cursor.getString(14));
            postulante.setIdiomas(cursor.getString(15));
            if(cursor.getString(16) != null)
                postulante.setTipoUsuario(TipoDeUsuario.identificadorATipoUsuario( cursor.getString(16) ));
            if(cursor.getString(17) != null)
                postulante.setQuienVeMiCV(QuienVeMiCV.identificadorAQuienvemicv(cursor.getString(17)));
        }
        else{
            Toast.makeText(context,"Perdedor!!!", Toast.LENGTH_LONG).show();
        }

        db.close();

    }

    public static void actualizarUsuarioActualFirebase(){
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        String key = databaseReference.child("Usuario").push().getKey();
        Usuario usr = AdministradorDeSesion.postulante;
        Map<String, Object> postValues = usr.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Usuario/" + AdministradorDeSesion.postulante.getIdPostulante() + "/", postValues);

        databaseReference.updateChildren(childUpdates);
    }


    public static void cerrarSesion() {
        //Borra el usuario de la variable global
        AdministradorDeSesion.postulante = null;

        //Borra elementos (usuarios) de la base de datos interna
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "bd_usuarios", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        String insertarDistancia = "DELETE FROM USUARIO";
        db.execSQL(insertarDistancia);
    }

    public static  Class getCurrentMenu(){
        if(postulante == null)
            return MainActivity.class;

        if(postulante.getTipoUsuario() == TipoDeUsuario.EMPLEADO)
            return MenuPostulanteActivity.class;
        if(postulante.getTipoUsuario() == TipoDeUsuario.EMPLEADOR)
            return MenuEmpleadorActivity.class;

        return MainActivity.class;
    }

}
