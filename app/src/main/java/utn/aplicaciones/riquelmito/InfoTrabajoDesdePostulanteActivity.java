package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import utn.aplicaciones.riquelmito.domain.DiasLaborales;
import utn.aplicaciones.riquelmito.domain.HorarioLaboral;
import utn.aplicaciones.riquelmito.domain.Rubro;
import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;

public class InfoTrabajoDesdePostulanteActivity extends AppCompatActivity {
    private ImageView ivInfoTrabPostulanteIconoTrabajo;
    private TextView tvInfoTrabPostulanteRubro;
    private TextView tvInfoTrabPostulanteCargo;
    private TextView tvInfoTrabPostulanteHorario;
    private TextView tvInfoTrabPostulanteSueldo;
    private TextView tvInfoTrabPostulanteDescripcion;
    private TextView tvInfoTrabPostulantePerfilEmpleado;
    private TextView tvInfoTrabPostulanteExperiencia;

    private Trabajo trabajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_trabajo_desde_postulante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Cargar views
        ivInfoTrabPostulanteIconoTrabajo = findViewById(R.id.ivInfoTrabPostulanteIconoTrabajo);
        tvInfoTrabPostulanteRubro = findViewById(R.id.tvInfoTrabPostulanteRubro);
        tvInfoTrabPostulanteCargo = findViewById(R.id.tvInfoTrabPostulanteCargo);
        tvInfoTrabPostulanteHorario = findViewById(R.id.tvInfoTrabPostulanteHorario);
        tvInfoTrabPostulanteSueldo = findViewById(R.id.tvInfoTrabPostulanteSueldo);
        tvInfoTrabPostulanteDescripcion = findViewById(R.id.tvInfoTrabPostulanteDescripcion);
        tvInfoTrabPostulantePerfilEmpleado = findViewById(R.id.tvInfoTrabPostulantePerfilEmpleado);
        tvInfoTrabPostulanteExperiencia = findViewById(R.id.tvInfoTrabPostulanteExperiencia);

        //Cargar detalles del trabajo
        Bundle bundleReseptor = getIntent().getExtras();
        if(bundleReseptor!=null)
            trabajo = (Trabajo) bundleReseptor.getSerializable("trabajo");

        if(trabajo!=null)
            cargarDatosVistaDesdeTrabajo();
    }


    private void cargarDatosVistaDesdeTrabajo(){
        ivInfoTrabPostulanteIconoTrabajo.setImageResource(AdministradorDeCargaDeInterfaces.getIdIconoRubro(this,trabajo));
        tvInfoTrabPostulanteRubro.setText(AdministradorDeCargaDeInterfaces.getFilaRubro(this, trabajo));
        if(trabajo.getCargo()!=null)
            tvInfoTrabPostulanteCargo.setText(trabajo.getCargo());
        tvInfoTrabPostulanteHorario.setText(AdministradorDeCargaDeInterfaces.getFilaHorario(this,trabajo));
        tvInfoTrabPostulanteSueldo.setText(AdministradorDeCargaDeInterfaces.getFilaSalario(this,trabajo));
        if(trabajo.getDescripcionCargo()!=null)
            tvInfoTrabPostulanteDescripcion.setText(trabajo.getDescripcionCargo());
        if(trabajo.getPerfilEmpleado()!=null)
            tvInfoTrabPostulantePerfilEmpleado.setText(trabajo.getPerfilEmpleado());
        if(trabajo.getExperienciaEmpleado()!=null)
            tvInfoTrabPostulanteExperiencia.setText(trabajo.getExperienciaEmpleado());
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
