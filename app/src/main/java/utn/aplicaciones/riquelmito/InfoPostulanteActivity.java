package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.domain.Usuario;

public class InfoPostulanteActivity extends AppCompatActivity {

    private Usuario postulante;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_postulante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Cargar detalles del trabajo
        Bundle bundleReseptor = getIntent().getExtras();
        if(bundleReseptor!=null)
            postulante = (Usuario) bundleReseptor.getSerializable("usuario");
        if(postulante!=null)
            cargarDatosVistaDesdePostulante();
    }

    private void cargarDatosVistaDesdePostulante() {
        //TODO: cargar datos
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

    public void goToDescargarCV(View view){
        storageReference = firebaseStorage.getInstance().getReference();
        StorageReference ref = storageReference.child("cv/"+postulante.getIdPostulante());
        //final Context contextoActual = this;

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>(){
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(InfoPostulanteActivity.this, "cv-" + postulante.getApellido() + '_' + postulante.getNombre(), ".pdf", Environment.DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InfoPostulanteActivity.this,"El usuario aún no subió un CV", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);

    }
}
