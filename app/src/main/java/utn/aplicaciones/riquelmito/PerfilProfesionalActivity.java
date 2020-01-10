package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class PerfilProfesionalActivity extends AppCompatActivity {
    private EditText etPerfProfIdiomas;
    private EditText etPerfProfCurriculum;

    private String[] idiomasArray;
    private boolean[] idiomasSeleccionados;
    private boolean[] idiomasSeleccionadosTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profesional);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPerfProfIdiomas = (EditText) findViewById(R.id.etPerfProfIdiomas);
        etPerfProfIdiomas.setKeyListener(null);
        etPerfProfCurriculum = (EditText) findViewById((R.id.etPerfProfCurriculum));
        etPerfProfCurriculum.setKeyListener(null);

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
