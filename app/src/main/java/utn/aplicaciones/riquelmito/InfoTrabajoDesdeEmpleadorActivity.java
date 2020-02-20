package utn.aplicaciones.riquelmito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import utn.aplicaciones.riquelmito.domain.Trabajo;
import utn.aplicaciones.riquelmito.utilidades.AdministradorDeCargaDeInterfaces;

public class InfoTrabajoDesdeEmpleadorActivity extends AppCompatActivity {
    private ImageView ivInfoTrabEmpleadorIconoTrabajo;
    private TextView tvInfoTrabEmpleadorRubro;
    private TextView tvInfoTrabEmpleadorCargo;
    private TextView tvInfoTrabEmpleadorHorario;
    private TextView tvInfoTrabEmpleadorSueldo;
    private TextView tvInfoTrabEmpleadorDescripcion;
    private TextView tvInfoTrabEmpleadorPerfilEmpleado;
    private TextView tvInfoTrabEmpleadorExperiencia;

    private Trabajo trabajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_trabajo_desde_empleador);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Cargar views
        ivInfoTrabEmpleadorIconoTrabajo = findViewById(R.id.ivInfoTrabEmpleadorIconoTrabajo);
        tvInfoTrabEmpleadorRubro = findViewById(R.id.tvInfoTrabEmpleadorRubro);
        tvInfoTrabEmpleadorCargo = findViewById(R.id.tvInfoTrabEmpleadorCargo);
        tvInfoTrabEmpleadorHorario = findViewById(R.id.tvInfoTrabEmpleadorHorario);
        tvInfoTrabEmpleadorSueldo = findViewById(R.id.tvInfoTrabEmpleadorSueldo);
        tvInfoTrabEmpleadorDescripcion = findViewById(R.id.tvInfoTrabEmpleadorDescripcion);
        tvInfoTrabEmpleadorPerfilEmpleado = findViewById(R.id.tvInfoTrabEmpleadorPerfilEmpleado);
        tvInfoTrabEmpleadorExperiencia = findViewById(R.id.tvInfoTrabEmpleadorExperiencia);

        //Cargar detalles del trabajo
        Bundle bundleReseptor = getIntent().getExtras();
        if(bundleReseptor!=null)
            trabajo = (Trabajo) bundleReseptor.getSerializable("trabajo");

        if(trabajo!=null)
            cargarDatosVistaDesdeTrabajo();
    }


    private void cargarDatosVistaDesdeTrabajo(){
        ivInfoTrabEmpleadorIconoTrabajo.setImageResource(AdministradorDeCargaDeInterfaces.getIdIconoRubro(this,trabajo));
        tvInfoTrabEmpleadorRubro.setText(AdministradorDeCargaDeInterfaces.getFilaRubro(this, trabajo));
        if(trabajo.getCargo()!=null)
            tvInfoTrabEmpleadorCargo.setText(trabajo.getCargo());
        tvInfoTrabEmpleadorHorario.setText(AdministradorDeCargaDeInterfaces.getFilaHorario(this,trabajo));
        tvInfoTrabEmpleadorSueldo.setText(AdministradorDeCargaDeInterfaces.getFilaSalario(this,trabajo));
        if(trabajo.getDescripcionCargo()!=null)
            tvInfoTrabEmpleadorDescripcion.setText(trabajo.getDescripcionCargo());
        if(trabajo.getPerfilEmpleado()!=null)
            tvInfoTrabEmpleadorPerfilEmpleado.setText(trabajo.getPerfilEmpleado());
        if(trabajo.getExperienciaEmpleado()!=null)
            tvInfoTrabEmpleadorExperiencia.setText(trabajo.getExperienciaEmpleado());
    }

    public void goToUsuariosPostuladosAltrabajo(View view){
        Intent intent = new Intent(this, UsuariosPostuladosATrabajoActivity.class);

        //Pasa una instancia de Trabajo a la actividad
        Bundle bundle = new Bundle();
        bundle.putSerializable("trabajo",trabajo);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public  void goToConsultarUbicacionTrabDesdeEmpleador(View view){
        Intent intent = new Intent(this, ConsultarUbicacionActivity.class);

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", trabajo.getLat());
        bundle.putDouble("lng", trabajo.getLng());

        intent.putExtras(bundle);

        startActivity(intent);
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
