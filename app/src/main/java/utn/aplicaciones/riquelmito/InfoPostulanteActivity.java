package utn.aplicaciones.riquelmito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import utn.aplicaciones.riquelmito.domain.QuienVeMiCV;
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.domain.Usuario;
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;

public class InfoPostulanteActivity extends AppCompatActivity {

    ImageView ivInfoPostulanteIconoSexo;
    TextView tvInfoPostulanteNombre;
    TextView tvInfoPostulanteResidencia;
    TextView tvInfoPostulanteSexoEdad;
    TextView tvInfoPostulanteExperiencia;
    TextView tvInfoPostulanteFormacion;
    TextView tvInfoPostulanteIdioma;
    TextView tvInfoPostulanteEmail;
    TextView tvInfoPostulanteContrasenia;
    TextView tvInfoPostulanteTelefono;
    TextView tvInfoPostulanteDni;
    Button btnInfoPostulanteDescargarCv;

    private Usuario postulante;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_postulante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Cargar elementos
        ivInfoPostulanteIconoSexo = findViewById(R.id.ivInfoPostulanteIconoSexo);
        tvInfoPostulanteNombre = findViewById(R.id.tvInfoPostulanteNombre);
        tvInfoPostulanteResidencia = findViewById(R.id.tvInfoPostulanteResidencia);
        tvInfoPostulanteSexoEdad = findViewById(R.id.tvInfoPostulanteSexoEdad);
        tvInfoPostulanteExperiencia = findViewById(R.id.tvInfoPostulanteExperiencia);
        tvInfoPostulanteFormacion = findViewById(R.id.tvInfoPostulanteFormacion);
        tvInfoPostulanteIdioma = findViewById(R.id.tvInfoPostulanteIdioma);
        tvInfoPostulanteEmail = findViewById(R.id.tvInfoPostulanteEmail);
        tvInfoPostulanteContrasenia = findViewById(R.id.tvInfoPostulanteContrasenia);
        tvInfoPostulanteTelefono = findViewById(R.id.tvInfoPostulanteTelefono);
        tvInfoPostulanteDni = findViewById(R.id.tvInfoPostulanteDni);
        btnInfoPostulanteDescargarCv = findViewById(R.id.btnInfoPostulanteDescargarCv);

        //Cargar detalles del trabajo
        Bundle bundleReseptor = getIntent().getExtras();
        if(bundleReseptor!=null)
            postulante = (Usuario) bundleReseptor.getSerializable("usuario");
        if(postulante!=null)
            cargarDatosVistaDesdePostulante();
    }

    private void cargarDatosVistaDesdePostulante() {

        ivInfoPostulanteIconoSexo.setImageResource(AdministradorDeCargaDeInterfaces.getIdIconoSexo(this, postulante));
        tvInfoPostulanteNombre.setText(AdministradorDeCargaDeInterfaces.getFilaNombreUsuario(this, postulante));
        tvInfoPostulanteResidencia.setText(AdministradorDeCargaDeInterfaces.getFilaRecidencia(this, postulante));
        tvInfoPostulanteSexoEdad.setText(AdministradorDeCargaDeInterfaces.getFilaSexoEdad(this, postulante));
        if(postulante.getExperiencia() != null)
            tvInfoPostulanteExperiencia.setText(postulante.getExperiencia());
        if(postulante.getFormacion() != null)
            tvInfoPostulanteFormacion.setText(postulante.getFormacion());
        if(postulante.getIdiomas() != null)
            tvInfoPostulanteIdioma.setText(postulante.getIdiomas());
        if(postulante.getEmail() != null)
            tvInfoPostulanteEmail.setText(postulante.getEmail());
        if(postulante.getContrasenia() != null)
            tvInfoPostulanteContrasenia.setText(postulante.getContrasenia());
        if(postulante.getTelefono() != null)
            tvInfoPostulanteTelefono.setText(postulante.getTelefono());
        if(postulante.getDni() != null && postulante.getDni() != 0)
            tvInfoPostulanteDni.setText(postulante.getDni().toString());

        //Solo se puede ver el botón de descagar CV si así lo decidió el usuario
        if(postulante.getQuienVeMiCV() != null){
            if(postulante.getQuienVeMiCV() == QuienVeMiCV.SOLO_YO){
                btnInfoPostulanteDescargarCv.setVisibility(View.GONE);
            }
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

    public  void goToConsultarUbicacionPostulante(View view){
        Intent intent = new Intent(this, ConsultarUbicacionActivity.class);

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", postulante.getLat());
        bundle.putDouble("lng", postulante.getLng());

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
