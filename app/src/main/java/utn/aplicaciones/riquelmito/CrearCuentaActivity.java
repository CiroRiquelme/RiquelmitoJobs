package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utn.aplicaciones.riquelmito.domain.TipoDeUsuario;
import utn.aplicaciones.riquelmito.domain.Usuario;
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;

public class CrearCuentaActivity extends AppCompatActivity {
    private final int LONG_MIN_PASS = 2;    //Longitud mínima de la contraseña

    private EditText etSingUpEmail;
    private EditText etSingUpPassword;
    private EditText etSingUpPasswordRe;
    private Spinner spnSingUpTipoUser;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private List<Integer> listIdUsuario = new ArrayList<Integer>();
    ArrayAdapter<Integer> arrayAdapterIdUsuario;
    private Integer id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSingUpEmail = (EditText) findViewById(R.id.etSingUpEmail);
        etSingUpPassword = (EditText) findViewById(R.id.etSingUpPassword);
        etSingUpPasswordRe = (EditText) findViewById(R.id.etSingUpPasswordRe);
        spnSingUpTipoUser = (Spinner) findViewById(R.id.spnSingUpTipoUser);
        spnSingUpTipoUser.setAdapter(new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, TipoDeUsuario.values() ));

        inicializarFirebase();

        databaseReference.child("IdUsuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    id = Integer.parseInt( dataSnapshot.child("valor").getValue().toString() );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void crearCuentaClick(View view){
        boolean operacionValida = true;
        StringBuffer mensajeError = new StringBuffer();

        //Verifica que el formato de email sea valido
        if( !AdministradorDeCargaDeInterfaces.esEmailValido(etSingUpEmail.getText().toString()) ){
            mensajeError.append(this.getString(R.string.dialogo_error_formato_email_invalido));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        //Verifica que el campo de contraseña no tenga menos de cierto número de caracteres
        if(etSingUpPassword.getText().toString().length() < LONG_MIN_PASS){
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_contrasenia_insuficiente1));
            mensajeError.append( ' ' );
            mensajeError.append( LONG_MIN_PASS );
            mensajeError.append( ' ' );
            mensajeError.append(this.getString(R.string.dialogo_error_longitud_contrasenia_insuficiente2));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        //Verifica que las dos veces se haya ingresado la misma contraseña
        if( !etSingUpPassword.getText().toString().equals(etSingUpPasswordRe.getText().toString()) ){
            mensajeError.append(this.getString(R.string.dialogo_error_contrasenias_distintas));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        //Verifica que el email no esté siendo usado por un usuario ya registrado
        //TODO
        if( userEmailYaRegistrado(etSingUpEmail.getText().toString()) ){
            mensajeError.append(this.getString(R.string.dialogo_error_email_ya_registrado));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(operacionValida){
            if(id==-1){
                Toast.makeText(this, "Espere hasta que la página inicie",Toast.LENGTH_LONG).show();
                return;
            }

            //Si no hubieron errores
            final Context cont = this;

            Usuario usuario = new Usuario(id+1, etSingUpEmail.getText().toString(), etSingUpPassword.getText().toString(), null, null, null, null, null, null, null, null, null, null, null, null, null);
            registrarUsuario(usuario);
            databaseReference.child("IdUsuario").child("valor").setValue(id+1);
        }
        else{
            //Mostrar dialogo de advertencias
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(this.getString(R.string.title_dialogo_campos_invalidos))
                    .setMessage(mensajeError)
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

    private Integer getUltimoIdUsuarioDB(){
        return  null;
    }

    private boolean userEmailYaRegistrado(String email){
        //TODO verificar que el 'email' no esté siendo usado por un usuario ya registrado
        return false;
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void registrarUsuario(Usuario usuario){
        databaseReference.child("Usuario").child(usuario.getIdPostulante().toString()).setValue(usuario);
    }

    //Esta función permite que el botón de 'volver atrás' de la barra superior funcione
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
