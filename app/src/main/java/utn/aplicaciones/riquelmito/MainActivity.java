package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private EditText etSignInEmail;
    private EditText etSignInPassword;
    private ProgressBar pbSignInWaitting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inciar_sesion);

        AdministradorDeSesion.context = getApplicationContext();

        etSignInEmail = findViewById(R.id.etSignInEmail);
        etSignInPassword = findViewById(R.id.etSignInPassword);
        pbSignInWaitting = findViewById(R.id.pbSignInWaitting);
        pbSignInWaitting.setVisibility(View.GONE);

        inicializarFirebase();

    }

    public void goToSingUp(View view) {
        Intent singUp = new Intent(this, CrearCuentaActivity.class);
        startActivity(singUp);
    }

    public void goToMenu(View view){
        startWaitting();

        DateFormat nacimiento = new SimpleDateFormat("dd/mm/yyyy");
        //AdministradorDeSesion.postulante = new Usuario(7, "prpitoracing@gmail.com", "racing", "José", "Argento", 777777, nacimiento.parse("21/03/1962"), Sexo.MASCULINO, "Buenos Aires", "Capital Federal", "123456789", -34.7702, -58.4327, "Vendedor de zapatos hace 30 años", "Secundario completo", "Español y Guaraní antiguo");
        //AdministradorDeSesion.postulante = new Usuario(7, "prpitoracing@gmail.com", "racing", "José", "Argento", 777777, nacimiento.parse("21/03/1962"), Sexo.MASCULINO, "Buenos Aires", "Capital Federal", "123456789", 0., 0., "Vendedor de zapatos hace 30 años", "Secundario completo", "Español y Guaraní antiguo");
        //guardarUsuario(AdministradorDeSesion.postulante);
        AdministradorDeSesion.postulante = null;
        buscarUsuario();
    }


    public void guardarUsuario(Usuario usuario){
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
            String insertarDistancia = "DELETE FROM USUARIO";
            db.execSQL(insertarDistancia);
            String insertarUsuario = "INSERT INTO USUARIO (" + insertInto + ") " + "VALUES ("+ values +")";
            db.execSQL(insertarUsuario);
        }
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void buscarUsuario(){

        Query query = databaseReference.child("Usuario").orderByChild("email").equalTo(etSignInEmail.getText().toString());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    Usuario user = new Usuario();

                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        user = ds.getValue(Usuario.class);
                    }

                    if(etSignInPassword.getText().toString().equals(user.getContrasenia())){
                        AdministradorDeSesion.postulante = user;

                        AceptarInicioSesion();


                    }
                    else{
                        stopWaitting();

                        //Mensaje de "La contraseña no es correcta"
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle(MainActivity.this.getString(R.string.title_dialog_contrasenia_incorrecta))
                                .setMessage(MainActivity.this.getString(R.string.dialogo_contrasenia_incorrecta))
                                .setNeutralButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //No es necesario hacer nada
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
                else {
                    stopWaitting();

                    //Mensaje de "El usuario indicado no se encuentra registrado"
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(MainActivity.this.getString(R.string.title_dialog_email_no_registrado))
                            .setMessage(MainActivity.this.getString(R.string.dialogo_email_no_registrado))
                            .setNeutralButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //No es necesario hacer nada
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        AdministradorDeSesion.postulante = null;

    }

    private void AceptarInicioSesion(){
        if(AdministradorDeSesion.postulante != null){
            guardarUsuario(AdministradorDeSesion.postulante);

            stopWaitting();

            Intent menu = new Intent(this, AdministradorDeSesion.getCurrentMenu());
            startActivity(menu);
            finish();
        }
        else{
            Toast.makeText(MainActivity.this,"Usuario nulo", Toast.LENGTH_LONG).show();
        }
    }

    //Pone el circulo de cargando en visible y evita que el usuario interaccione con la aplicación mientras se carga su solicitud
    private void startWaitting(){
        pbSignInWaitting.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //Quita el circulo de cargando en oculto y permite nuevamente que el usuario interaccione con la aplicación
    private void stopWaitting(){
        pbSignInWaitting.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}
