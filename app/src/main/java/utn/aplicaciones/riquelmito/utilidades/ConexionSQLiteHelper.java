package utn.aplicaciones.riquelmito.utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA_DISTANCIA = "CREATE TABLE DISTANCIA (id INTEGER, longitud INTEGER)";
    final String CREAR_TABLA_USUARIO = "CREATE TABLE USUARIO (idPostulante INTEGER, email TEXT, contrasenia TEXT," +
            "nombre TEXT, apellido TEXT, dni INTEGER, nacimiento TEXT, sexo TEXT, provincia TEXT, ciudad TEXT, " +
            "telefono TEXT, lat DOUBLE, lng DOUBLE, experiencia TEXT, formacion TEXT, idiomas TEXT," +
            " tipoUsuario TEXT, quienVeMiCv TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_DISTANCIA);
        db.execSQL(CREAR_TABLA_USUARIO);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(AdministradorDeSesion.context, "bd_usuarios", null,1);
        String insertarDistancia = "INSERT INTO DISTANCIA (id, longitud) VALUES (123, 70)";
        db.execSQL(insertarDistancia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS DISTANCIA");
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        onCreate(db);
    }
}
