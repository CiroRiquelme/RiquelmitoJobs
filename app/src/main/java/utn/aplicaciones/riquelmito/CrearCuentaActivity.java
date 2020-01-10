package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCuentaActivity extends AppCompatActivity {
    private final int LONG_MIN_PASS = 2;    //Longitud mínima de la contraseña

    private EditText etSingUpEmail;
    private EditText etSingUpPassword;
    private EditText etSingUpPasswordRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSingUpEmail = (EditText) findViewById(R.id.etSingUpEmail);
        etSingUpPassword = (EditText) findViewById(R.id.etSingUpPassword);
        etSingUpPasswordRe = (EditText) findViewById(R.id.etSingUpPasswordRe);
    }

    public void crearCuentaClick(View view){
        boolean operacionValida = true;
        StringBuffer mensajeError = new StringBuffer();

        //Verifica que el formato de email sea valido
        if( !esEmailValido(etSingUpEmail.getText().toString()) ){
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
        if( userEmailYaRegistrado(etSingUpEmail.getText().toString()) ){
            mensajeError.append(this.getString(R.string.dialogo_error_email_ya_registrado));
            mensajeError.append( '\n' );
            operacionValida = false;
        }

        if(operacionValida){
            //Si no hubieron errores
            registrarUsuario();
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

    private boolean esEmailValido(String email){
        String expression = "^[a-zA-Z0-9]([\\w\\.-]?[a-zA-Z0-9])*@[a-zA-Z0-9]([-_]?[a-zA-Z0-9])+\\.[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean userEmailYaRegistrado(String email){
        //TODO verificar que el 'email' no esté siendo usado por un usuario ya registrado
        return false;
    }

    private void registrarUsuario(){
        //TODO Añadir el nuevo usuario a la base de datos
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
