package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;

public class PerfilProfesionalActivity extends AppCompatActivity {
    private final int REQUEST_UPLOAD_CV = 1;

    private EditText mltPerfProfExperiencia;
    private EditText mltPerfProfFormacion;
    private EditText etPerfProfIdiomas;

    private String[] idiomasArray;
    private boolean[] idiomasSeleccionados;
    private boolean[] idiomasSeleccionadosTemp;

    private final String CHANNEL_ID = "casandra";
    private final int UPLOAD_CV_ID = 234;
    private NotificationCompat.Builder uploadNoification;

    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profesional);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mltPerfProfExperiencia = (EditText) findViewById(R.id.mltPerfProfExperiencia);
        mltPerfProfFormacion = (EditText) findViewById(R.id.mltPerfProfFormacion);
        etPerfProfIdiomas = (EditText) findViewById(R.id.etPerfProfIdiomas);
        etPerfProfIdiomas.setKeyListener(null);

        //Define listas necesarias para trabajar con la lista de idiomas
        idiomasArray = getResources().getStringArray(R.array.idiomas);
        idiomasSeleccionados = new boolean[idiomasArray.length];
        idiomasSeleccionadosTemp = new boolean[idiomasArray.length];

        llenarCampos();
    }

    private void llenarCampos(){
        //Verifica que la variable global de usuario actual no nula
        if(AdministradorDeSesion.postulante == null)
            return;

        //Verifica que los campos necesarios no sean nulos
        if(AdministradorDeSesion.postulante.getExperiencia() != null)
            mltPerfProfExperiencia.setText(AdministradorDeSesion.postulante.getExperiencia());
        if(AdministradorDeSesion.postulante.getFormacion() != null)
            mltPerfProfFormacion.setText(AdministradorDeSesion.postulante.getFormacion());
        if(AdministradorDeSesion.postulante.getIdiomas() != null)
            etPerfProfIdiomas.setText(AdministradorDeSesion.postulante.getIdiomas());
    }

    public void selectIdiomas(View view){
        for(int i=0; i<idiomasSeleccionados.length; i++){
            idiomasSeleccionadosTemp[i] = idiomasSeleccionados[i];
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_dialogo_seleccionar_idiomas)
                .setMultiChoiceItems(idiomasArray, idiomasSeleccionadosTemp, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        idiomasSeleccionadosTemp[i] = isChecked;
                    }
                })
                .setNegativeButton(R.string.opcion_cancelar,null)
                .setPositiveButton(R.string.opcion_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer listaIdiomas = new StringBuffer();
                        for(int j=0; j<idiomasSeleccionados.length; j++){
                            idiomasSeleccionados[j] = idiomasSeleccionadosTemp[j];
                            if(idiomasSeleccionadosTemp[j]){
                                if(listaIdiomas.length()!=0)
                                    listaIdiomas.append(';');
                                listaIdiomas.append(idiomasArray[j]);
                            }
                        }
                        etPerfProfIdiomas.setText(listaIdiomas);
                    }
                });
        builder.create().show();
    }

    //Se debe ejecutar cuando se presione el botón de subir CV
    public void goToSubirCV(View view){
        mStorage = FirebaseStorage.getInstance().getReference();

        //Intent intent = new Intent(Intent.ACTION_PICK);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_UPLOAD_CV);

    }


    @Override
    //Recibe los datos del explorador cuando se selecciona un PDF para subir como CV
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == this.REQUEST_UPLOAD_CV && resultCode==RESULT_OK){
            //Agrega una notificación indicando que se está subiendo el curriculum
            uploadNoification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_menu_upload)
                    .setContentTitle(this.getString(R.string.app_name))
                    .setContentText(this.getString(R.string.perfil_prof_subiendo_cv))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setAutoCancel(true);
            final NotificationManagerCompat man = NotificationManagerCompat.from(this);
            man.notify(UPLOAD_CV_ID, uploadNoification.build());

            //Subir CV
            Uri uri = data.getData();

            StorageReference filePath = mStorage.child("cv").child(AdministradorDeSesion.postulante.getIdPostulante().toString());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                    Toast.makeText(AdministradorDeSesion.context, "Se subió exitosamente el CV", Toast.LENGTH_SHORT).show();

                    uploadNoification.setContentTitle(PerfilProfesionalActivity.this.getString(R.string.perfil_prof_terminado_subir_cv));
                    man.notify(UPLOAD_CV_ID, uploadNoification.build());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PerfilProfesionalActivity.this,"No se ha podido conectar con el servidor", Toast.LENGTH_LONG).show();

                    uploadNoification.setContentTitle(PerfilProfesionalActivity.this.getString(R.string.perfil_prof_error_al_subir_cv));
                    man.notify(UPLOAD_CV_ID, uploadNoification.build());
                }
            });


        }

    }


    public void actualizarPerfilProfesional(View view){
        //TODO: actualizar el 'quién puede ver mi CV'

        //TODO: Actualizar los valores modificados (o en su defecto todos) de la DB interna, de firestore y de la variable global
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
