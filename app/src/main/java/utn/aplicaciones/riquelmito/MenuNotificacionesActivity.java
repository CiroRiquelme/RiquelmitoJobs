package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import utn.aplicaciones.riquelmito.domain.AdministradorDeSesion;
import utn.aplicaciones.riquelmito.domain.Notificacion;

public class MenuNotificacionesActivity extends AppCompatActivity {

    private StorageReference mStorage;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_notificaciones);
    }

    public void botonSuscribirse (View view){
        FirebaseServicioDeMensajes fsm = new FirebaseServicioDeMensajes();
        fsm.suscribirATopico("futbol");
    }

    public void botonDesuscribirse (View view){
        FirebaseServicioDeMensajes fsm = new FirebaseServicioDeMensajes();
        fsm.desuscribirATopico("futbol");
    }

    public void botonEnviarNotificacion (View view){
        Notificacion notifs = new Notificacion();
        notifs.setImageUrl("asd");
        notifs.setEmail("test@email.es");
        notifs.setUsername("Pablito");
        notifs.setUid("futbol");
        notifs.setTopic("futbol");

        notifs.setText("The post wa liked");



        FirebaseDatabase.getInstance().getReference("futbol").push().setValue(notifs);
    }

    public void botonSeleccionarDocumento(View view){
        mStorage = FirebaseStorage.getInstance().getReference();

        //Intent intent = new Intent(Intent.ACTION_PICK);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK){
            Uri uri = data.getData();

            //TODO en lugar de uri.getLasPathSegment poner id de usuarioq
            StorageReference filePath = mStorage.child("cv").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                    Toast.makeText(AdministradorDeSesion.context, "Se subió exitosamente el CV", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public void botonMostrarDocumento(View view){

        storageReference = firebaseStorage.getInstance().getReference();
        //TODO buscar "cv/"+ idUsuario.toString()
        StorageReference ref = storageReference.child("cv/1833");
        //final Context contextoActual = this;

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>(){
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                //TODO en fileName poner "cv-"+ idUsuario.toString()
                downloadFiles(MenuNotificacionesActivity.this, "Mobile", ".pdf", Environment.DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

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
