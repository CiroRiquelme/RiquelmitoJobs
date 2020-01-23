package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

    private EditText etPerfProfIdiomas;

    private String[] idiomasArray;
    private boolean[] idiomasSeleccionados;
    private boolean[] idiomasSeleccionadosTemp;

    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profesional);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPerfProfIdiomas = (EditText) findViewById(R.id.etPerfProfIdiomas);
        etPerfProfIdiomas.setKeyListener(null);

        //Define listas necesarias para trabajar con la lista de idiomas
        idiomasArray = getResources().getStringArray(R.array.idiomas);
        idiomasSeleccionados = new boolean[idiomasArray.length];
        idiomasSeleccionadosTemp = new boolean[idiomasArray.length];
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

    public void goToSubirCV(View view){
        mStorage = FirebaseStorage.getInstance().getReference();

        //Intent intent = new Intent(Intent.ACTION_PICK);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_UPLOAD_CV);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == this.REQUEST_UPLOAD_CV && resultCode==RESULT_OK){
            Uri uri = data.getData();

            StorageReference filePath = mStorage.child("cv").child(AdministradorDeSesion.postulante.getIdPostulante().toString());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                    Toast.makeText(AdministradorDeSesion.context, "Se subió exitosamente el CV", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PerfilProfesionalActivity.this,"No se ha podido conectar con el servidor", Toast.LENGTH_LONG).show();
                }
            });

        }

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
